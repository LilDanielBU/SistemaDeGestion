package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.impl;


import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Tarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.TareaRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // <-- ¡IMPORT MUY IMPORTANTE!

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<Tarea> findByProyectoId(Integer proyectoId) {
        return tareaRepository.findByProyecto_IdProyecto(proyectoId);
    }

    @Override
    public List<Tarea> findByUsuarioAsignado(Usuario usuario) {
        return tareaRepository.findByUsuarioAsignado(usuario);
    }

    @Override
    public Tarea save(Tarea tarea) {

        return tareaRepository.save(tarea);
    }

    @Override
    public Optional<Tarea> findById(Integer id) {
        return tareaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        tareaRepository.deleteById(id);
    }
}