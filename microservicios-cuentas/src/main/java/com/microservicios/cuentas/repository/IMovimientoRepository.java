package com.microservicios.cuentas.repository;

import java.time.LocalDateTime;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.microservicios.cuentas.dto.EstadoCuentaDTO;
import com.microservicios.cuentas.entities.Movimiento;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IMovimientoRepository extends ReactiveCrudRepository<Movimiento, Long> {

	@Query("SELECT MAX(m.id_movimiento) FROM movimiento m WHERE m.cuenta_id = :cuentaId")
	Mono<Long> obtenerIdUltimoMovimiento(@Param("cuentaId") Long cuentaId);

	@Query("SELECT SUM(m.valor) FROM movimiento m WHERE m.tipo_movimiento = 'DEB' AND m.fecha_movimiento >= :fechaInicioDia")
	Mono<Long> obtenerValorCupoUtilizado(@Param("fechaInicioDia") LocalDateTime fechaInicioDia);

	@Query("SELECT mo.fecha_movimiento AS fechaMovimiento, " + "concat(c.nombre, ' ', c.apellido) AS nombreCliente, "
			+ "cu.numero_cuenta AS numeroCuenta, " + "cu.tipo_cuenta AS tipoCuenta, "
			+ "cu.saldo_inicial AS saldoInicial, " + "cu.estado AS estado, " + "mo.valor AS valor, "
			+ "mo.saldo AS saldo " + "FROM movimiento mo " + "JOIN cuenta cu ON mo.cuenta_id = cu.id_cuenta "
			+ "JOIN cliente c ON cu.cliente_id = c.id_cliente "
			+ "WHERE mo.fecha_movimiento BETWEEN :fechaDesde AND :fechaHasta "
			+ "AND c.identificacion = :identificacion")
	Flux<EstadoCuentaDTO> obtenerEstadoCuenta(@Param("fechaDesde") LocalDateTime fechaDesde,
			@Param("fechaHasta") LocalDateTime fechaHasta, @Param("identificacion") String identificacion);
}
