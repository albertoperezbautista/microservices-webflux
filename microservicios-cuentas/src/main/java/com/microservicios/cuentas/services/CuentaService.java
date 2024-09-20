package com.microservicios.cuentas.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservicios.cuentas.constants.Constantes;
import com.microservicios.cuentas.dto.ClienteDTO;
import com.microservicios.cuentas.dto.CuentaDTO;
import com.microservicios.cuentas.entities.Cliente;
import com.microservicios.cuentas.entities.Cuenta;
import com.microservicios.cuentas.exceptions.BusinessException;
import com.microservicios.cuentas.exceptions.RequestException;
import com.microservicios.cuentas.repository.ICuentaRepository;
import com.microservicios.cuentas.utils.MapStructMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CuentaService implements ICuentaService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MapStructMapper mapper;

	private final ICuentaRepository cuentaRepo;

	private final WebClient.Builder webClientBuilder;

	@Override
	public Flux<CuentaDTO> obtenerTodos() {
		return cuentaRepo.findAll().map(cuenta -> {
			CuentaDTO dto = modelMapper.map(cuenta, CuentaDTO.class);
			dto.setIdentificacion(cuenta.getCliente().getIdentificacion());
			return dto;
		});
	}

	@Override
	public Mono<CuentaDTO> obtenerPorId(Long id) {
		return cuentaRepo.findById(id).map(cuenta -> {
			CuentaDTO cuentaDTO = modelMapper.map(cuenta, CuentaDTO.class);
			cuentaDTO.setIdentificacion(cuenta.getCliente().getIdentificacion());
			return cuentaDTO;
		}).switchIfEmpty(Mono.error(new RequestException("P-401", "Cuenta no encontrada " + id)));
	}

	@Override
	public Mono<Object> crearCuenta(CuentaDTO cuentaDTO) {
		return cuentaRepo.findByNumeroCuenta(cuentaDTO.getNumeroCuenta())
				.flatMap(existingCuenta -> Mono
						.error(new BusinessException("501", HttpStatus.CONFLICT, "Número de cuenta ya registrado.")))
				.switchIfEmpty(webClientBuilder.build().get()
						.uri("http://localhost:8082/clientes/findByIdentificacion/{identificacion}",
								cuentaDTO.getIdentificacion())
						.retrieve().bodyToMono(ClienteDTO.class)
						.switchIfEmpty(Mono.error(new BusinessException("P-401", HttpStatus.BAD_REQUEST,
								"Cliente no encontrado con identificación " + cuentaDTO.getIdentificacion())))
						.flatMap(clienteDTO -> {
							Cliente cliente = new Cliente();
							cliente.setIdCliente(clienteDTO.getIdCliente());

							Cuenta cuenta = modelMapper.map(cuentaDTO, Cuenta.class);
							cuenta.setCliente(cliente);
							cuenta.setEstado(Constantes.ACTIVO);

							return cuentaRepo.save(cuenta).map(savedCuenta -> {
								CuentaDTO cuentaDTOCreado = modelMapper.map(savedCuenta, CuentaDTO.class);
								cuentaDTOCreado.setIdentificacion(cliente.getIdentificacion());
								return cuentaDTOCreado;
							});
						}));
	}

	@Override
	public Mono<CuentaDTO> actualizarCuenta(Integer numeroCuenta, CuentaDTO cuentaDTO) {
		return cuentaRepo.findByNumeroCuenta(numeroCuenta).flatMap(cuentaExistente -> {
			mapper.updateCuentaFromDto(cuentaDTO, cuentaExistente);
			return cuentaRepo.save(cuentaExistente);
		}).map(cuentaActualizada -> {
			CuentaDTO cuentaDTOActualizado = modelMapper.map(cuentaActualizada, CuentaDTO.class);
			cuentaDTOActualizado.setIdentificacion(cuentaActualizada.getCliente().getIdentificacion());
			return cuentaDTOActualizado;
		}).switchIfEmpty(
				Mono.error(new RequestException("P-401", Constantes.ERROR_REGISTRO_NO_ENCONTRADO + numeroCuenta)));
	}

	@Override
	public Mono<Void> eliminarCuenta(Long id) {
		return cuentaRepo.findById(id).flatMap(cuenta -> cuentaRepo.delete(cuenta))
				.switchIfEmpty(Mono.error(new RequestException("P-401", Constantes.ERROR_REGISTRO_NO_ENCONTRADO + id)));
	}

	@Override
	public Mono<CuentaDTO> obtenerPorNumeroCuenta(Integer numeroCuenta) {
		return cuentaRepo.findByNumeroCuenta(numeroCuenta).map(cuenta -> {
			CuentaDTO cuentaDTO = modelMapper.map(cuenta, CuentaDTO.class);
			cuentaDTO.setIdentificacion(cuenta.getCliente().getIdentificacion());
			return cuentaDTO;
		}).switchIfEmpty(
				Mono.error(new RequestException("P-401", "Cuenta con número " + numeroCuenta + " no encontrada")));
	}
}
