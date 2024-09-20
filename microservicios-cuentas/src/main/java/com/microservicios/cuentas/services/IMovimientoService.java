package com.microservicios.cuentas.services;

import java.time.LocalDateTime;

import com.microservicios.cuentas.dto.EstadoCuentaDTO;
import com.microservicios.cuentas.dto.MovimientoDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovimientoService {

	Flux<MovimientoDTO> obtenerTodos();

	Mono<MovimientoDTO> obtenerPorId(Long id);

	Mono<Object> crearMovimiento(MovimientoDTO movimientoDto);

	Flux<EstadoCuentaDTO> obtenerEstadoCuenta(String identificacion, LocalDateTime fechaDesde,
			LocalDateTime fechaHasta);
}
