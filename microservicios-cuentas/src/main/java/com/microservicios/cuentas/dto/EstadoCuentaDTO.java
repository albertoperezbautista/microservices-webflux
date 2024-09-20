package com.microservicios.cuentas.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EstadoCuentaDTO {

	private LocalDateTime fecha;
	private String cliente;
	private Integer numeroCuenta;
	private String tipo;
	private Long saldoInicial;
	private String estado;
	private Long valor;
	private Long saldo;
}
