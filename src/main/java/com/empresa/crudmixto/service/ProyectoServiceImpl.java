package com.empresa.crudmixto.service;

import com.empresa.crudmixto.entity.proyecto;
import com.empresa.crudmixto.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public List<proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    public proyecto guardar(proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public proyecto obtenerPorId(String id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(String id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public List<proyecto> obtenerPorEmpleadoId(Long empleadoId) {
        return proyectoRepository.findByEmpleadoId(empleadoId);
    }
}