package com.microservicios.clientes.dto;

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

}
