package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProyectoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_final;
}
