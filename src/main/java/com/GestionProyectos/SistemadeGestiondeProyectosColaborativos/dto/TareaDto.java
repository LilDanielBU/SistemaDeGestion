package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TareaDto {

    private Long id;

    private String titulo ;

    private String descripcion ;

    private String estado ;

    private String prioridad ;

}
