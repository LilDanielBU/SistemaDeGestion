package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF deshabilitado para simplificar
                .authorizeHttpRequests(authorize -> authorize
                        // Rutas públicas que cualquiera puede visitar
                        .requestMatchers("/login", "/register", "/css/**", "/api/auth/register").permitAll()

                        // --- LÍNEA CLAVE AÑADIDA ---
                        // Permite el acceso a todos los módulos si el usuario está autenticado.
                        .requestMatchers(
                                "/dashboard",
                                "/comentarios/**",
                                "/equipo/**",
                                "/proyecto/**",
                                "/tarea/**",
                                "/usuario/**"
                        ).authenticated()

                        // Para cualquier otra ruta no especificada, también se requiere autenticación.
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/dashboard", true) // Redirige al dashboard tras login exitoso
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redirige a la página de login al cerrar sesión
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}