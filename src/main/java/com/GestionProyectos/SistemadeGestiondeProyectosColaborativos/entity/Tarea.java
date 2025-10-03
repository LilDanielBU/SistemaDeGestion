package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;

    @Enumerated(EnumType.STRING)
    private PrioridadTarea prioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario_asignado")
    private Usuario usuarioAsignado;

    // En Tarea.java
    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // <-- Añadir EAGER
    private List<Comentario> comentarios;
    // --- GETTERS Y SETTERS ---

    public Integer getIdTarea() { return idTarea; }
    public void setIdTarea(Integer idTarea) { this.idTarea = idTarea; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public EstadoTarea getEstado() { return estado; }
    public void setEstado(EstadoTarea estado) { this.estado = estado; }
    public PrioridadTarea getPrioridad() { return prioridad; }
    public void setPrioridad(PrioridadTarea prioridad) { this.prioridad = prioridad; }
    public Proyecto getProyecto() { return proyecto; } // <-- El método que faltaba
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
    public Usuario getUsuarioAsignado() { return usuarioAsignado; }
    public void setUsuarioAsignado(Usuario usuarioAsignado) { this.usuarioAsignado = usuarioAsignado; }
    public List<Comentario> getComentarios() { return comentarios; }
    public void setComentarios(List<Comentario> comentarios) { this.comentarios = comentarios; }
}