package com.microservicios.cuentas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CuentaDTO {

	private Long idCuenta;

	@NotNull
	@NotEmpty
	@NotBlank(message = "Identificacion requerida")
	@Size(min = 10, message = "Identificacion debe tener al menos 10 caracteres")
	private String identificacion;

	@NotNull
	private String tipoCuenta;

	@NotNull
	private Integer numeroCuenta;

	@NotNull
	private Long saldoInicial;

	@NotNull
	private Long saldoDisponible;

	@NotNull
	private Long limiteDiario;

	@NotNull
	@NotEmpty
	private String estadoCuenta;

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Long getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Long saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Long getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(Long saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public Long getLimiteDiario() {
		return limiteDiario;
	}

	public void setLimiteDiario(Long limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	public String getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

}
