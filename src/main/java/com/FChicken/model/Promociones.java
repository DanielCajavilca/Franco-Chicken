package com.FChicken.model;

import java.sql.Date;

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
public class Promociones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPromociones;
	private String nombre;
	private String descripcion;
	private Date fecha_ini;
	private Date fecha_fin;
	private String tipo;
	private Boolean estado;
}
