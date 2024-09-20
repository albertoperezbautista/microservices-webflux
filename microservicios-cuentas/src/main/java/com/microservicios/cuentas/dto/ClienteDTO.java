package com.microservicios.cuentas.dto;

import java.util.Date;

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
public class ClienteDTO {

	private Long idCliente;

	@NotNull
	@NotEmpty
	@NotBlank(message = "Identificacion requerida")
	@Size(min = 10, message = "Identificacion debe tener al menos 10 caracteres")
	private String identificacion;

	@NotNull
	@NotEmpty
	@Size(min = 2, message = "Nombre debe tener al menos 2 caracteres")
	private String nombre;

	@NotNull
	@NotEmpty
	@Size(min = 2, message = "Apellido debe tener al menos 2 caracteres")
	private String apellido;

	@NotNull
	@NotEmpty
	@Size(min = 2, message = "Direccion debe tener al menos 2 caracteres")
	private String direccion;

	@NotNull
	@NotEmpty
	@Size(min = 10, message = "Telefono debe tener al menos 10 caracteres")
	private String telefono;

	@NotNull
	private Date fechaNacimiento;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 1, message = "Genero debe tener 1 caracter")
	private String genero;

	@NotNull
	private Long numeroCliente;

	@NotNull
	@NotEmpty
	@Size(min = 5, message = "Contrasena debe tener al menos 5 caracteres")
	private String contrasena;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(Long numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
