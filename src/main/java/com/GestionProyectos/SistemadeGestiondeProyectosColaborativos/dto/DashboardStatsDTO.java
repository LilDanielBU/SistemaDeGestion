package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.dto;

public class DashboardStatsDTO {
    private long totalProyectos;
    private long tareasActivas;
    private long totalUsuarios;

    // --- Getters y Setters ---

    public long getTotalProyectos() { return totalProyectos; }
    public void setTotalProyectos(long totalProyectos) { this.totalProyectos = totalProyectos; }
    public long getTareasActivas() { return tareasActivas; }
    public void setTareasActivas(long tareasActivas) { this.tareasActivas = tareasActivas; }
    public long getTotalUsuarios() { return totalUsuarios; }
    public void setTotalUsuarios(long totalUsuarios) { this.totalUsuarios = totalUsuarios; }
}