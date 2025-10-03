package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.controller;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Rol;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller; // <-- Asegúrate de tener este import
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios") // <-- URL base para toda la clase
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping // <-- Anotación CRÍTICA
    public String listarUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.findAll());
        return "usuarios/lista"; // Devuelve la vista de la lista
    }


    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Rol.values());
        return "usuarios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", Rol.values());
        return "usuarios/formulario";
    }


    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        if (usuario.getIdUsuario() == null && usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }
}