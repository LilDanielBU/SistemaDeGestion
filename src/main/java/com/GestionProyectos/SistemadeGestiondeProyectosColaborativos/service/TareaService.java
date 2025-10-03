package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service;


import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Tarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface TareaService {


    List<Tarea> findByProyectoId(Integer proyectoId);

    Tarea save(Tarea tarea);


    Optional<Tarea> findById(Integer id);

    List<Tarea> findByUsuarioAsignado(Usuario usuario);

    void deleteById(Integer id);

}
