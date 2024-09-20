package com.microservicios.clientes.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
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
