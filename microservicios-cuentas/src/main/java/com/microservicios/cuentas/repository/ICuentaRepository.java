package com.microservicios.cuentas.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.microservicios.cuentas.entities.Cuenta;

import reactor.core.publisher.Mono;

@Repository
public interface ICuentaRepository extends ReactiveCrudRepository<Cuenta, Long> {
    Mono<Cuenta> findByNumeroCuenta(Integer numeroCuenta);
}