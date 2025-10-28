package com.empresa.crudmixto.repository;

import com.empresa.crudmixto.entity.proyecto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProyectoRepository extends MongoRepository<proyecto, String> {
    List<proyecto> findByEmpleadoId(Long empleadoId);
}