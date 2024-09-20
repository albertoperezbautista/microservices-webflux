package com.microservicios.cuentas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.cuentas.dto.CuentaDTO;
import com.microservicios.cuentas.services.CuentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor

public class CuentaController {

	private final CuentaService cuentaService;

	@GetMapping
	public Flux<CuentaDTO> obtenerTodos() {
		return cuentaService.obtenerTodos();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<CuentaDTO>> obtenerPorId(@PathVariable("id") Long id) {
		return cuentaService.obtenerPorId(id).map(cuentaDTO -> ResponseEntity.ok(cuentaDTO))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/numero/{numeroCuenta}")
	public Mono<ResponseEntity<CuentaDTO>> obtenerPorNumeroCuenta(@PathVariable("numeroCuenta") Integer numeroCuenta) {
		return cuentaService.obtenerPorNumeroCuenta(numeroCuenta).map(cuentaDTO -> ResponseEntity.ok(cuentaDTO))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<Object>> crearCuenta(@Valid @RequestBody CuentaDTO cuentaDTO) {
		return cuentaService.crearCuenta(cuentaDTO)
				.map(cuentaDTOCreado -> ResponseEntity.status(HttpStatus.CREATED).body(cuentaDTOCreado))
				.onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
	}

	@PutMapping("/{numeroCuenta}")
	public Mono<ResponseEntity<CuentaDTO>> actualizarCuenta(@PathVariable("numeroCuenta") Integer numeroCuenta,
			@RequestBody CuentaDTO cuentaDTO) {
		return cuentaService.actualizarCuenta(numeroCuenta, cuentaDTO)
				.map(cuentaDTOActualizado -> ResponseEntity.ok(cuentaDTOActualizado))
				.onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Object>> eliminarCuenta(@PathVariable("id") Long id) {
		return cuentaService.eliminarCuenta(id).then(Mono.just(ResponseEntity.noContent().build()))
				.onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
	}
}
