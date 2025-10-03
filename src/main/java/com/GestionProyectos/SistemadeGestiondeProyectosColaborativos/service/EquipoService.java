package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service;



import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Equipo;
import java.util.List;
import java.util.Optional;

public interface EquipoService {
    List<Equipo> findAll();
    Optional<Equipo> findById(Integer id);
    Equipo save(Equipo equipo);
    void deleteById(Integer id);
    Equipo addUserToEquipo(Integer equipoId, Integer usuarioId);
    Equipo removeUserFromEquipo(Integer equipoId, Integer usuarioId);
}