package com.microservicios.cuentas.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservicios.cuentas.constants.Constantes;
import com.microservicios.cuentas.dto.ErrorDTO;
import com.microservicios.cuentas.exceptions.BusinessException;
import com.microservicios.cuentas.exceptions.RequestException;

@RestControllerAdvice
@RestController
public class ControllerAdvice {

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex) {

	
		ErrorDTO error = ErrorDTO.builder().code("P-505").message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDTO> constraintViolationExceptionHandler(ConstraintViolationException ex) {
		ErrorDTO error = ErrorDTO.builder().code("P-409 - " + ex.getErrorCode())
				.message(Constantes.ERROR_INTEGRIDAD_DATOS).causa(ex.getConstraintName()).build();
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(value = RequestException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex) {
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex) {
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
		return new ResponseEntity<>(error, ex.getStatus());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		StringBuilder cadenaError = new StringBuilder();
		errors.forEach(e -> {
			cadenaError.append(e);
			cadenaError.append(" - ");
		});

		ErrorDTO error = ErrorDTO.builder().code("P-409").message(errors.toString()).causa(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);

	}

}
