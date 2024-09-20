package com.microservicios.clientes.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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

	public Long getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Long idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public LocalDateTime getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public String getEstadoMovimiento() {
		return estadoMovimiento;
	}

	public void setEstadoMovimiento(String estadoMovimiento) {
		this.estadoMovimiento = estadoMovimiento;
	}

}
