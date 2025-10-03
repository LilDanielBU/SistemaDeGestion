package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.impl;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Rol;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.UsuarioRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // <-- Importante añadir

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrarNuevoUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalStateException("El email ya está registrado.");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(Rol.Colaborador);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findUsuariosNoEnEquipo(Integer equipoId) {
        return usuarioRepository.findUsuariosNoEnEquipo(equipoId);
    }

    // --- MÉTODOS QUE FALTABAN ---

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        // Este método sirve tanto para crear como para actualizar usuarios
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}