package com.empresa.crudmixto.service;

import com.empresa.crudmixto.entity.Empleado;
import com.empresa.crudmixto.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public void guardar(Empleado empleado) {
    System.out.println("Intentando guardar empleado con email: " + empleado.getEmail());
    Empleado existente = empleadoRepository.findByEmail(empleado.getEmail());
    System.out.println("Empleado encontrado: " + existente);
    if (existente != null && (empleado.getId() == null || !existente.getId().equals(empleado.getId()))) {
        throw new RuntimeException("El email ya está registrado.");
    }
    empleadoRepository.save(empleado);
}
    @Override
    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public List<Empleado> buscarPorNombreOCargo(String query) {
        return empleadoRepository.findByNombreContainingIgnoreCaseOrCargoContainingIgnoreCase(query, query);
    }
}