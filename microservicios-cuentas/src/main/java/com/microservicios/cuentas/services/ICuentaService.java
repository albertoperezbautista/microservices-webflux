package com.microservicios.cuentas.services;

import com.microservicios.cuentas.dto.CuentaDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICuentaService {

    Flux<CuentaDTO> obtenerTodos();

    Mono<CuentaDTO> obtenerPorId(Long id);

    Mono<Object> crearCuenta(CuentaDTO cuentaDto);

    Mono<CuentaDTO> actualizarCuenta(Integer numeroCuenta, CuentaDTO cuentaDTO);

    Mono<Void> eliminarCuenta(Long id);

    Mono<CuentaDTO> obtenerPorNumeroCuenta(Integer numeroCuenta);
}