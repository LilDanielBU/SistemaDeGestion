package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.config;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Rol;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // correo del admin nuevo
        String adminEmail = "admin@proyecto.com";


        if (usuarioRepository.findByEmail(adminEmail).isEmpty()) {

            System.out.println("Creando usuario administrador por defecto...");


            Usuario admin = new Usuario();
            admin.setNombreCompleto("Administrador del Sistema");
            admin.setEmail(adminEmail);


            admin.setPassword(passwordEncoder.encode("admin123"));

            admin.setRol(Rol.Administrador);

            usuarioRepository.save(admin);

            System.out.println("Usuario administrador creado con éxito.");
        } else {
            System.out.println("El usuario administrador ya existe. No se realizaron cambios.");
        }
    }
}