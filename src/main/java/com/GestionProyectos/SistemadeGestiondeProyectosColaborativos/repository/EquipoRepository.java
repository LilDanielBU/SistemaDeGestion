package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {


    Equipo findByProyecto_IdProyecto(Integer proyectoId);
}