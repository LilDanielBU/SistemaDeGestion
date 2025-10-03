package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository;

import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // <-- Make sure this import exists
import org.springframework.data.repository.query.Param; // <-- And this one
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u NOT IN (SELECT m FROM Equipo e JOIN e.miembros m WHERE e.idEquipo = :equipoId)")
    List<Usuario> findUsuariosNoEnEquipo(@Param("equipoId") Integer equipoId);
}