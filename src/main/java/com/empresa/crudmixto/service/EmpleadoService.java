package com.empresa.crudmixto.service;

import com.empresa.crudmixto.entity.Empleado;
import java.util.List;

public interface EmpleadoService {
    List<Empleado> obtenerTodos();
    void guardar(Empleado empleado);
    Empleado obtenerPorId(Long id);
    void eliminar(Long id);
    List<Empleado> buscarPorNombreOCargo(String query);
}