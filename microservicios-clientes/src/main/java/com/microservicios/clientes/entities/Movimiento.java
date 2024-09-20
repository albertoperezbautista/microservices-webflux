//package com.microservicios.clientes.entities;
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "movimientos")
//@Getter
//@Setter
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Movimiento {
//
//	@Id
//	@SequenceGenerator(name = "movimientos_id_seq", sequenceName = "movimientos_id_seq", initialValue = 5, allocationSize = 1)
//	@GeneratedValue(generator = "movimientos_id_seq")
//	@Column(name = "id_movimiento")
//	private Long idMovimiento;
//
//	@Column(name = "fecha_movimiento")
//	private LocalDateTime fechaMovimiento;
//
//	@Column(name = "tipo_movimiento")
//	private String tipoMovimiento;
//
//	@Column(name = "valor")
//	private Long valor;
//
//	@Column(name = "saldo")
//	private Long saldo;
//
//	@Column(name = "estado_movimiento")
//	private String estadoMovimiento;
//
//	@Column(name = "estado")
//	private String estado;
//
//	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", nullable = false, updatable = false)
//	private Cuenta cuenta;
//
//}
