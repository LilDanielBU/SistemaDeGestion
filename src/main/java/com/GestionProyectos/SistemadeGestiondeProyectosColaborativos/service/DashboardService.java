package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.dto.DashboardStatsDTO;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.EstadoTarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.ProyectoRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.TareaRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DashboardStatsDTO getDashboardStats() {

        long totalProyectos = proyectoRepository.count();
        long tareasActivas = tareaRepository.countByEstadoIsNot(EstadoTarea.Completada);
        long totalUsuarios = usuarioRepository.count();

        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setTotalProyectos(totalProyectos);
        stats.setTareasActivas(tareasActivas);
        stats.setTotalUsuarios(totalUsuarios);

        return stats;
    }
}