package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Equipo;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.EquipoService;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.ProyectoService;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoService.findAll());
        return "equipos/lista";
    }


    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoEquipo(Model model) {
        model.addAttribute("equipo", new Equipo());
        // Pasamos la lista de todos los proyectos para poder seleccionarlos en un dropdown
        model.addAttribute("listaProyectos", proyectoService.findAll());
        return "equipos/formulario";
    }

    // --- NUEVO MÉTODO PARA GUARDAR EL EQUIPO ---
    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute("equipo") Equipo equipo) {
        equipoService.save(equipo);
        return "redirect:/equipos";
    }

    @GetMapping("/{id}")
    public String verEquipo(@PathVariable Integer id, Model model) {
        Equipo equipo = equipoService.findById(id).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        model.addAttribute("equipo", equipo);
        model.addAttribute("miembros", equipo.getMiembros());
        model.addAttribute("usuariosNoMiembros", usuarioService.findUsuariosNoEnEquipo(id));
        return "equipos/detalle";
    }

    @PostMapping("/{equipoId}/agregar-miembro")
    public String agregarMiembro(@PathVariable Integer equipoId, @RequestParam Integer usuarioId) {
        equipoService.addUserToEquipo(equipoId, usuarioId);
        return "redirect:/equipos/" + equipoId;
    }

    @GetMapping("/{equipoId}/remover-miembro/{usuarioId}")
    public String removerMiembro(@PathVariable Integer equipoId, @PathVariable Integer usuarioId) {
        equipoService.removeUserFromEquipo(equipoId, usuarioId);
        return "redirect:/equipos/" + equipoId;
    }
}