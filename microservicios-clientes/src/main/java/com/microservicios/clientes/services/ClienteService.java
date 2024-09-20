package com.microservicios.clientes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.clientes.constants.Constantes;
import com.microservicios.clientes.dto.ClienteDTO;
import com.microservicios.clientes.entities.Cliente;
import com.microservicios.clientes.exceptions.RequestException;
import com.microservicios.clientes.repository.IClienteRepository;
import com.microservicios.clientes.utils.MapStructMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MapStructMapper mapper;

	private final IClienteRepository clienteRepo;

	@Override
	public List<ClienteDTO> obtenerTodos() {
		List<Cliente> listaClientes = clienteRepo.findAll();
		List<ClienteDTO> respuesta = listaClientes.stream().map(cliente -> mapearDTO(cliente))
				.collect(Collectors.toList());

		return respuesta;
	}

	@Override
	public ClienteDTO obtenerPoId(Long id) {

		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow(() -> new RequestException("P-401", "Cliente no encontrado " + id));

		ClienteDTO clienteDto = modelMapper.map(cliente, ClienteDTO.class);

		return clienteDto;

	}

	@Override
	public ClienteDTO obtenerPorNumeroCliente(Long numeroCliente) {

		Cliente cliente = clienteRepo.findByNumeroCliente(numeroCliente).orElseThrow(
				() -> new RequestException("P-401", "Cliente no encontrado con numeroCliente " + numeroCliente));
		ClienteDTO clienteDto = modelMapper.map(cliente, ClienteDTO.class);

		return clienteDto;

	}

	@Override
	public ClienteDTO findByIdentificacion(String identificacion) {
		Cliente cliente = clienteRepo.findByIdentificacion(identificacion).orElseThrow(
				() -> new RequestException("P-401", "Cliente no encontrado con identificacion " + identificacion));
		ClienteDTO clienteDto = modelMapper.map(cliente, ClienteDTO.class);

		return clienteDto;

	}

	@Override
	public ClienteDTO crearCliente(ClienteDTO clienteDto) {

		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
		cliente.setEstado(Constantes.ACTIVO);

		clienteRepo.save(cliente);
		ClienteDTO clienteDtoCreado = modelMapper.map(cliente, ClienteDTO.class);
		return clienteDtoCreado;

	}

	@Override
	public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {

		Cliente clienteExistente = clienteRepo.findById(id)
				.orElseThrow(() -> new RequestException("P-401", Constantes.ERROR_REGISTRO_NO_ENCONTRADO + id)

				);

		mapper.updateClienteFromDto(clienteDTO, clienteExistente);
		clienteRepo.save(clienteExistente);

		ClienteDTO clienteDtoActualizado = modelMapper.map(clienteExistente, ClienteDTO.class);
		return clienteDtoActualizado;

	}

	@Override
	public void eliminarCliente(Long id) {
		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow(() -> new RequestException("P-401", Constantes.ERROR_REGISTRO_NO_ENCONTRADO + id)

				);
		clienteRepo.delete(cliente);

	}

	private ClienteDTO mapearDTO(Cliente cliente) {
		System.out.println("mapearDTO cliente.identificacion::::: " + cliente.getIdentificacion());
		System.out.println("mapearDTO cliente.identificacion::::: " + cliente.getNombre());

		System.out.println("mapearDTO cliente.identificacion::::: " + cliente.getApellido());
//		ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);

		return ClienteDTO.builder().identificacion(cliente.getIdentificacion()).nombre(cliente.getNombre())
				.apellido(cliente.getApellido()).build();
	}

}
