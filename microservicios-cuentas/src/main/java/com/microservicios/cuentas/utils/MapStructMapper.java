package com.microservicios.cuentas.utils;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.microservicios.cuentas.dto.CuentaDTO;
import com.microservicios.cuentas.dto.MovimientoDTO;
import com.microservicios.cuentas.entities.Cuenta;
import com.microservicios.cuentas.entities.Movimiento;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateCuentaFromDto(CuentaDTO dto, @MappingTarget Cuenta entity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateMovimientoFromDto(MovimientoDTO dto, @MappingTarget Movimiento entity);

}
