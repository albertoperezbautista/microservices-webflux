package com.microservicios.cuentas.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class MovimientoDTO {

	private Long idMovimiento;

	private Long idCuenta;
	@NotNull
	private Integer numeroCuenta;

	private LocalDateTime fechaMovimiento;
	@NotNull
	@NotBlank
	@NotEmpty
	private String tipoMovimiento;

	@NotNull
	private Long valor;

	private Long saldo;

	private String estadoMovimiento;

}
