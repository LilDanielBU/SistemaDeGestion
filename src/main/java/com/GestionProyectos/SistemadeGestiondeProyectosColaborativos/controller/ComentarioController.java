package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Comentario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Rol; // <-- Asegúrate de tener este import
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Tarea;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.ComentarioService;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private TareaService tareaService;

    @PostMapping("/tareas/{tareaId}/comentarios")
    public String guardarComentario(@PathVariable Integer tareaId,
                                    @RequestParam String contenido,
                                    @AuthenticationPrincipal Usuario autor) {

        Tarea tarea = tareaService.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setContenido(contenido);
        nuevoComentario.setTarea(tarea);
        nuevoComentario.setAutor(autor);

        comentarioService.save(nuevoComentario);


        if (autor.getRol() == Rol.Administrador) {

            Integer proyectoId = tarea.getProyecto().getIdProyecto();
            return "redirect:/proyectos/ver/" + proyectoId;
        } else {

            return "redirect:/mis-tareas/" + tareaId;
        }
    }
}