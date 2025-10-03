package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.dto.DashboardStatsDTO;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Rol; // <-- IMPORT AÑADIDO
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.DashboardService;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // --- CORRECCIÓN 1: INYECCIÓN DE DEPENDENCIAS ---
    // Estas líneas le dicen a Spring que necesita proporcionar los servicios.
    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private TareaService tareaService;

    @GetMapping("/")
    public String mostrarDashboard(Model model, @AuthenticationPrincipal Usuario usuario) {

        // --- CORRECCIÓN 2: COMPARACIÓN DE ROL ---
        // Se compara directamente con el enum, que es más seguro y correcto.
        if (usuario.getRol() == Rol.Administrador) {

            // Si es Admin, muestra el dashboard de siempre
            DashboardStatsDTO stats = dashboardService.getDashboardStats();
            model.addAttribute("stats", stats);
            return "index";

        } else {

            // Si es Colaborador, busca solo sus tareas y lo envía a su propio dashboard
            model.addAttribute("misTareas", tareaService.findByUsuarioAsignado(usuario));
            return "colaborador/dashboard";

        }
    }
}