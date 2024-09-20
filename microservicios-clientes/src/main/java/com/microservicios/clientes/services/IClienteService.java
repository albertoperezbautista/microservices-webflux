package com.microservicios.clientes.services;

import java.util.List;

import com.microservicios.clientes.dto.ClienteDTO;

public interface IClienteService {

	public List<ClienteDTO> obtenerTodos();

	public ClienteDTO obtenerPoId(Long id);

	public ClienteDTO obtenerPorNumeroCliente(Long numeroCliente);

	public ClienteDTO crearCliente(ClienteDTO clienteDto);

	public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);

	public void eliminarCliente(Long id);
	
	
	public ClienteDTO findByIdentificacion(String identificacion) ;

}
