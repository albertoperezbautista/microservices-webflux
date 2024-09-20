package com.microservicios.cuentas;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.microservicios.cuentas")
public class MicroserviciosCuentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCuentasApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
