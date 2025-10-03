package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.*;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.EquipoRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping
    public String listarProyectos(Model model) {
        model.addAttribute("listaProyectos", proyectoService.findAll());
        return "proyectos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProyecto(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyectos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute("proyecto") Proyecto proyecto) {
        proyectoService.save(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/ver/{id}")
    public String verProyectoDetalle(@PathVariable("id") Integer id, Model model) {
        Proyecto proyecto = proyectoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Proyecto inválido:" + id));


        model.addAttribute("proyecto", proyecto);
        model.addAttribute("tareas", proyecto.getTareas());

        model.addAttribute("nuevaTarea", new Tarea());

        model.addAttribute("estados", EstadoTarea.values());
        model.addAttribute("prioridades", PrioridadTarea.values());


        Equipo equipo = equipoRepository.findByProyecto_IdProyecto(id);
        if (equipo != null) {
            model.addAttribute("miembrosDelEquipo", equipo.getMiembros());
        } else {
            model.addAttribute("miembrosDelEquipo", Collections.emptyList());
        }

        return "proyectos/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable("id") Integer id) {
        proyectoService.deleteById(id);
        return "redirect:/proyectos";
    }
}