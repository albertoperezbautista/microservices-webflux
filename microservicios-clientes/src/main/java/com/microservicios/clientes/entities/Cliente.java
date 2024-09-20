package com.microservicios.clientes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Cliente extends Persona {

	@Id
	@SequenceGenerator(name = "clientes_id_seq", sequenceName = "clientes_id_seq", initialValue = 5, allocationSize = 1)
	@GeneratedValue(generator = "clientes_id_seq")
	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "numero_cliente", unique = true)
	private Long numeroCliente;

	@Column(name = "contrasena")
	private String contrasena;

	@Column(name = "estado")
	private String estado;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.LAZY)
//	private List<Cuenta> listaCuentas;

}
