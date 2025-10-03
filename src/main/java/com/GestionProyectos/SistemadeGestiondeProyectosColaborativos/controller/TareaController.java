package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.*;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.ProyectoService;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/proyectos/{proyectoId}/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping("/guardar")
    public String guardarTarea(@PathVariable("proyectoId") Integer proyectoId, @ModelAttribute("tarea") Tarea tarea) {
        Proyecto proyecto = proyectoService.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("ID de Proyecto inválido:" + proyectoId));
        tarea.setProyecto(proyecto);
        tareaService.save(tarea);
        return "redirect:/proyectos/ver/" + proyectoId;
    }

    @GetMapping("/eliminar/{tareaId}")
    public String eliminarTarea(@PathVariable("proyectoId") Integer proyectoId, @PathVariable("tareaId") Integer tareaId) {
        tareaService.deleteById(tareaId);
        return "redirect:/proyectos/ver/" + proyectoId;
    }
}