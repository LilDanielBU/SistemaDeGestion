package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.EstadoTarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Tarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ColaboradorController {

    @Autowired
    private TareaService tareaService;

    // Muestra la página de detalle/actualización de una tarea
    @GetMapping("/mis-tareas/{id}")
    public String verTarea(@PathVariable Integer id, Model model) {
        Tarea tarea = tareaService.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        model.addAttribute("tarea", tarea);
        model.addAttribute("estados", EstadoTarea.values()); // Para el dropdown de estados
        return "colaborador/tarea-detalle";
    }

    // Procesa la actualización del estado de la tarea
    @PostMapping("/mis-tareas/actualizar-estado")
    public String actualizarEstado(@RequestParam("tareaId") Integer tareaId, @RequestParam("estado") EstadoTarea estado) {
        Tarea tarea = tareaService.findById(tareaId).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setEstado(estado);
        tareaService.save(tarea);
        return "redirect:/"; // Redirige al dashboard del colaborador
    }
}