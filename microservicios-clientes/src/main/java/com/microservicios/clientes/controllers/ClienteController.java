package com.microservicios.clientes.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.clientes.dto.ClienteDTO;
import com.microservicios.clientes.services.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;
	


	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> obtenerTodos() {

		List<ClienteDTO> listaClientesDto = clienteService.obtenerTodos();
		return ResponseEntity.ok(listaClientesDto);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obtenerPoId(@PathVariable(value = "id") Long id) {

		ClienteDTO clienteDto = clienteService.obtenerPoId(id);

		return ResponseEntity.ok().body(clienteDto);

	}

	@GetMapping("numeroCliente/{numeroCliente}")
	public ResponseEntity<ClienteDTO> obtenerPorNumeroCliente(
			@PathVariable(value = "numeroCliente") Long numeroCliente) {

		ClienteDTO clienteDto = clienteService.obtenerPorNumeroCliente(numeroCliente);

		return ResponseEntity.ok().body(clienteDto);

	}

	@GetMapping("findByIdentificacion/{identificacion}")
	public ResponseEntity<ClienteDTO> findByIdentificacion(
			@PathVariable(value = "identificacion") String identificacion) {

		ClienteDTO clienteDto = clienteService.findByIdentificacion(identificacion);

		return ResponseEntity.ok().body(clienteDto);

	}

	@PostMapping
	public ClienteDTO crearCliente(@Valid @RequestBody ClienteDTO clienteDto) {
		ClienteDTO clienteDtoCreado = clienteService.crearCliente(clienteDto);
		return clienteDtoCreado;
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable(value = "id") Long id,
			@RequestBody ClienteDTO clienteDTO) {
		ClienteDTO clienteResp = clienteService.actualizarCliente(id, clienteDTO);
		return ResponseEntity.ok().body(clienteResp);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable(value = "id") Long id) {
		clienteService.eliminarCliente(id);
		return ResponseEntity.ok().build();
	}
}
