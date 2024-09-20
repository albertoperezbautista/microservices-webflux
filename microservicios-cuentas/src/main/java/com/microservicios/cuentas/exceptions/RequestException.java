package com.microservicios.cuentas.exceptions;

import lombok.Data;

@Data
public class RequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -992444185458843192L;
	private String code;

	public RequestException(String code, String message) {
		super(message);
		this.code = code;
	}

}
