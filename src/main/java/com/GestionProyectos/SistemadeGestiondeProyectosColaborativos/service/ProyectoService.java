package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Proyecto;

import java.util.List;
import java.util.Optional;

public interface ProyectoService {
    List<Proyecto> findAll();
    Optional<Proyecto> findById(Integer id);
    Proyecto save(Proyecto proyecto);
    void deleteById(Integer id);
}