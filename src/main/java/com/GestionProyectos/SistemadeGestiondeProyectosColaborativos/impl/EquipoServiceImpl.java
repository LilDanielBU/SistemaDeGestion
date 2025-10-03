package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.impl;




import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Equipo;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Usuario;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.EquipoRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.UsuarioRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Equipo> findAll() { return equipoRepository.findAll(); }

    @Override
    public Optional<Equipo> findById(Integer id) { return equipoRepository.findById(id); }

    @Override
    public Equipo save(Equipo equipo) { return equipoRepository.save(equipo); }

    @Override
    public void deleteById(Integer id) { equipoRepository.deleteById(id); }

    @Override
    public Equipo addUserToEquipo(Integer equipoId, Integer usuarioId) {
        Equipo equipo = findById(equipoId).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        equipo.getMiembros().add(usuario);
        return equipoRepository.save(equipo);
    }

    @Override
    public Equipo removeUserFromEquipo(Integer equipoId, Integer usuarioId) {
        Equipo equipo = findById(equipoId).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        equipo.getMiembros().remove(usuario);
        return equipoRepository.save(equipo);
    }
}