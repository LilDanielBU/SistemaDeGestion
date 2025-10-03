package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository;



import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.EstadoTarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Tarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    long countByEstado(EstadoTarea estado);
    List<Tarea> findByProyecto_IdProyecto(Integer proyectoId);
    long countByEstadoIsNot(EstadoTarea estado);

    List<Tarea> findByUsuarioAsignado(Usuario usuario);
}
