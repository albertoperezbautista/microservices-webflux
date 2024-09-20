package com.microservicios.cuentas.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.cuentas.dto.EstadoCuentaDTO;
import com.microservicios.cuentas.dto.MovimientoDTO;
import com.microservicios.cuentas.services.IMovimientoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

	private final IMovimientoService movimientoService;

	@GetMapping()
	public Flux<MovimientoDTO> obtenerTodos() {
		return movimientoService.obtenerTodos();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<MovimientoDTO>> obtenerPorId(@PathVariable(value = "id") Long id) {
		return movimientoService.obtenerPorId(id).map(movimientoDto -> ResponseEntity.ok().body(movimientoDto))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<Object> crearMovimiento(@Valid @RequestBody MovimientoDTO movimientoDto) {
		return movimientoService.crearMovimiento(movimientoDto);
	}

	@GetMapping("/reportes/{identificacion}/{fechaDesde}/{fechaHasta}")
	public Flux<EstadoCuentaDTO> obtenerEstadoCuenta(@PathVariable String identificacion,
			@PathVariable String fechaDesde, @PathVariable String fechaHasta) {
		LocalDateTime fechaDesdeDateTime = LocalDate.parse(fechaDesde).atStartOfDay();
		LocalDateTime fechaHastaDateTime = LocalDate.parse(fechaHasta).atStartOfDay();
		return movimientoService.obtenerEstadoCuenta(identificacion, fechaDesdeDateTime, fechaHastaDateTime);
	}
}
