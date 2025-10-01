package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String titulo ;

    @Column(name = "descripcion")
    private String descripcion ;

    @Column(name = "estado")
    private String estado ;

    @Column(name = "prioridad")
    private String prioridad ;


}
