package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

// --- Imports necesarios ---
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

    // --- Inyecta el repositorio de Equipo aquí ---
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

    // --- MÉTODO CORREGIDO Y COMPLETO ---
    @GetMapping("/ver/{id}")
    public String verProyectoDetalle(@PathVariable("id") Integer id, Model model) {
        Proyecto proyecto = proyectoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Proyecto inválido:" + id));

        // Pasa los datos del proyecto y sus tareas (esto ya lo tenías)
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("tareas", proyecto.getTareas());

        // Pasa un objeto vacío para el formulario de nueva tarea
        model.addAttribute("nuevaTarea", new Tarea());

        // --- LÓGICA QUE FALTABA, AHORA EN EL LUGAR CORRECTO ---
        // Pasa los enums para los menús desplegables del formulario de tareas
        model.addAttribute("estados", EstadoTarea.values());
        model.addAttribute("prioridades", PrioridadTarea.values());

        // Busca el equipo del proyecto y pasa la lista de sus miembros
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