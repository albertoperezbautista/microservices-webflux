package com.microservicios.cuentas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.microservicios.cuentas.dto.EstadoCuentaDTO;
import com.microservicios.cuentas.dto.MovimientoDTO;
import com.microservicios.cuentas.entities.Movimiento;
import com.microservicios.cuentas.exceptions.BusinessException;
import com.microservicios.cuentas.repository.ICuentaRepository;
import com.microservicios.cuentas.repository.IMovimientoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovimientoService implements IMovimientoService {

	private final ModelMapper modelMapper;
	private final IMovimientoRepository movimientoRepo;
	private final ICuentaRepository cuentaRepo;

	@Override
	public Flux<MovimientoDTO> obtenerTodos() {
		return movimientoRepo.findAll()
				.flatMap(movimiento -> cuentaRepo.findById(movimiento.getCuenta().getIdCuenta()).map(cuenta -> {
					MovimientoDTO movimientoDTO = mapearDTO(movimiento);
					movimientoDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
					return movimientoDTO;
				}));
	}

	@Override
	public Mono<MovimientoDTO> obtenerPorId(Long id) {
		return movimientoRepo.findById(id)
				.flatMap(movimiento -> cuentaRepo.findById(movimiento.getCuenta().getIdCuenta()).map(cuenta -> {
					MovimientoDTO movimientoDTO = mapearDTO(movimiento);
					movimientoDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
					return movimientoDTO;
				})).switchIfEmpty(Mono
						.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimiento no encontrado " + id)));
	}

	@Override
	public Mono<Object> crearMovimiento(MovimientoDTO movimientoDTO) {
		return cuentaRepo.findByNumeroCuenta(movimientoDTO.getNumeroCuenta())
				.switchIfEmpty(Mono.error(new BusinessException("P-401", HttpStatus.BAD_REQUEST,
						"Cuenta no encontrada con número de cuenta " + movimientoDTO.getNumeroCuenta())))
				.flatMap(cuentaVerificacion -> {
					return movimientoRepo.obtenerIdUltimoMovimiento(cuentaVerificacion.getIdCuenta())
							.flatMap(idUltimoMovimiento -> movimientoRepo.findById(idUltimoMovimiento)
									.defaultIfEmpty(new Movimiento())
									.flatMap(ultimoMovimiento -> {
										LocalDate fechaHoy = LocalDate.now();
										Movimiento movimiento = modelMapper.map(movimientoDTO, Movimiento.class);
										movimiento.setCuenta(cuentaVerificacion);
										movimiento.setFechaMovimiento(LocalDateTime.now());

										if ("DEB".equals(movimientoDTO.getTipoMovimiento())) {
											return movimientoRepo.obtenerValorCupoUtilizado(fechaHoy.atStartOfDay())
													.defaultIfEmpty(0L)
													.flatMap(cupoUtilizado -> {
														if ((Math.abs(cupoUtilizado)
																+ movimientoDTO.getValor()) > cuentaVerificacion
																		.getLimiteDiario()) {
															return Mono.error(new BusinessException("E500",
																	HttpStatus.INTERNAL_SERVER_ERROR,
																	"Cupo diario excedido"));
														}

														if (Math.abs(movimientoDTO.getValor()) > Math
																.abs(ultimoMovimiento.getSaldo())) {
															return Mono.error(new BusinessException("E500",
																	HttpStatus.INTERNAL_SERVER_ERROR,
																	"Saldo insuficiente"));
														}

														if (ultimoMovimiento.getSaldo() == 0) {
															return Mono.error(new BusinessException("E500",
																	HttpStatus.INTERNAL_SERVER_ERROR,
																	"Saldo no disponible"));
														}

														movimiento.setValor(movimiento.getValor() * -1);
														movimiento.setSaldo(
																ultimoMovimiento.getSaldo() + movimiento.getValor());
														movimiento.setEstadoMovimiento("PROCESADO");
														movimiento.setEstado("ACT");

														return movimientoRepo.save(movimiento)
																.map(savedMovimiento -> {
																	MovimientoDTO movimientoDTOCreado = modelMapper
																			.map(savedMovimiento, MovimientoDTO.class);
																	movimientoDTOCreado.setIdCuenta(
																			cuentaVerificacion.getIdCuenta());
																	movimientoDTOCreado.setNumeroCuenta(
																			cuentaVerificacion.getNumeroCuenta());
																	movimientoDTOCreado.setValor(
																			Math.abs(movimientoDTOCreado.getValor()));
																	return movimientoDTOCreado;
																});
													});
										} else {
											movimiento.setSaldo(ultimoMovimiento.getSaldo() + movimiento.getValor());
											movimiento.setEstadoMovimiento("PROCESADO");
											movimiento.setEstado("ACT");

											return movimientoRepo.save(movimiento)
													.map(savedMovimiento -> {
														MovimientoDTO movimientoDTOCreado = modelMapper
																.map(savedMovimiento, MovimientoDTO.class);
														movimientoDTOCreado
																.setIdCuenta(cuentaVerificacion.getIdCuenta());
														movimientoDTOCreado
																.setNumeroCuenta(cuentaVerificacion.getNumeroCuenta());
														return movimientoDTOCreado;
													});
										}
									}));
				});
	}

	@Override
	public Flux<EstadoCuentaDTO> obtenerEstadoCuenta(String identificacion, LocalDateTime fechaDesde,
			LocalDateTime fechaHasta) {
		return movimientoRepo.obtenerEstadoCuenta(fechaDesde, fechaHasta, identificacion);
	}

	private MovimientoDTO mapearDTO(Movimiento movimiento) {
		return modelMapper.map(movimiento, MovimientoDTO.class);
	}
}
