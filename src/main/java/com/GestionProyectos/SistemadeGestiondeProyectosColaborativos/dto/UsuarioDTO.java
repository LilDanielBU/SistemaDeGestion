package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.dto;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Rol;

public class UsuarioDTO {

    private Integer idUsuario;
    private String nombreCompleto;
    private String email;
    private Rol rol;

    // --- Constructores, Getters y Setters ---

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}