package com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.impl;


import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.entity.Proyecto;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.repository.ProyectoRepository;
import com.GestionProyectos.SistemadeGestiondeProyectosColaborativos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    @Override
    public Optional<Proyecto> findById(Integer id) {
        return proyectoRepository.findById(id);
    }

    @Override
    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void deleteById(Integer id) {
        proyectoRepository.deleteById(id);
    }
}