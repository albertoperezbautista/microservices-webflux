package com.microservicios.clientes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Data
public class ResourceNoFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3218398543233577900L;
	private String nombreRecurso;
	private String nombreCampo;
	private Long valorCampo;

	public ResourceNoFoundException(String nombreRecurso, String nombreCampo, Long valorCampo) {
		super(String.format("%s No encontrado : %s :  %s ", nombreRecurso, nombreCampo, valorCampo));
		this.nombreRecurso = nombreRecurso;
		this.nombreCampo = nombreCampo;
		this.valorCampo = valorCampo;
	}

}
