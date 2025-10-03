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

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private TareaService tareaService;

    @GetMapping("/")
    public String mostrarDashboard(Model model, @AuthenticationPrincipal Usuario usuario) {


        if (usuario.getRol() == Rol.Administrador) {

            DashboardStatsDTO stats = dashboardService.getDashboardStats();
            model.addAttribute("stats", stats);
            return "index";

        } else {


            model.addAttribute("misTareas", tareaService.findByUsuarioAsignado(usuario));
            return "colaborador/dashboard";

        }
    }
}