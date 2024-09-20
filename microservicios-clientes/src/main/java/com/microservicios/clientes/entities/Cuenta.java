//package com.microservicios.clientes.entities;
//
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "cuentas")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Cuenta {
//
//	@Id
//	@SequenceGenerator(name = "cuentas_id_seq", sequenceName = "cuentas_id_seq", initialValue = 5, allocationSize = 1)
//	@GeneratedValue(generator = "cuentas_id_seq")
//	@Column(name = "id_cuenta")
//	private Long idCuenta;
//
//	@Column(name = "tipo_cuenta")
//	private String tipoCuenta;
//
//	@Column(name = "numero_cuenta", unique = true)
//	private Integer numeroCuenta;
//
//	@Column(name = "saldo_inicial")
//	private Long saldoInicial;
//
//	@Column(name = "saldo_disponible")
//	private Long saldoDisponible;
//
//	@Column(name = "limite_diario")
//	private Long limiteDiario;
//
//	@Column(name = "estado_cuenta")
//	private String estadoCuenta;
//
//	@Column(name = "estado")
//	private String estado;
//
//	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
//	private Cliente cliente;
//
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta", orphanRemoval = true, fetch = FetchType.LAZY)
//	private List<Movimiento> listaMovimientos;
//
//}
