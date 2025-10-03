package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service;


import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Comentario;

public interface ComentarioService {
    Comentario save(Comentario comentario);
    void deleteById(Integer id);
}