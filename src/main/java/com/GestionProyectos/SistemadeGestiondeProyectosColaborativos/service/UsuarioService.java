package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario registrarNuevoUsuario(Usuario usuario);

    List<Usuario> findUsuariosNoEnEquipo(Integer equipoId);

    List<Usuario> findAll();

    Optional<Usuario> findById(Integer id); // Debe devolver Optional<Usuario>

    Usuario save(Usuario usuario);

    void deleteById(Integer id);
}