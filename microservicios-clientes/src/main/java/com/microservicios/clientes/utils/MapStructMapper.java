package com.microservicios.clientes.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.microservicios.clientes.dto.ClienteDTO;
import com.microservicios.clientes.entities.Cliente;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateClienteFromDto(ClienteDTO dto, @MappingTarget Cliente entity);

	
}
