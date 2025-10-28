package com.empresa.crudmixto.service;

import com.empresa.crudmixto.entity.proyecto;
import java.util.List;

public interface ProyectoService {
    List<proyecto> obtenerTodos();
    proyecto guardar(proyecto proyecto);
    proyecto obtenerPorId(String id);
    void eliminar(String id);
    List<proyecto> obtenerPorEmpleadoId(Long empleadoId);
}