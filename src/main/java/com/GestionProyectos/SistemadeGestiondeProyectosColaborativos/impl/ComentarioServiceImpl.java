package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.impl;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Comentario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.ComentarioRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public void deleteById(Integer id) {
        comentarioRepository.deleteById(id);
    }
}