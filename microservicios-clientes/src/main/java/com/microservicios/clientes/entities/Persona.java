package com.microservicios.clientes.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@Data
public class Persona {

	@Column(name = "identificacion", unique = true)
	protected String identificacion;

	@Column(name = "nombre")
	protected String nombre;

	@Column(name = "apellido")
	protected String apellido;

	@Column(name = "direccion")
	protected String direccion;

	@Column(name = "telefono")
	protected String telefono;

	@Column(name = "fecha_nacimiento")
	protected Date fechaNacimiento;

	@Column(name = "genero")
	protected String genero;

}
