package com.microservicios.clientes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicios.clientes.entities.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByNumeroCliente(Long numeroCliente);

	Optional<Cliente> findByIdentificacion(String identificacion);

}
