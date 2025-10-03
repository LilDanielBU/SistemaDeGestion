package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository;


import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}