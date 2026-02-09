package com.FChicken.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;
	private String infoPedido;
	private int cantidad;
	private Double precio;
	private String direccion;
	private String observaciones;	
}