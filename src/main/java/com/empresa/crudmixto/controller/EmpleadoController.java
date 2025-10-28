package com.empresa.crudmixto.controller;

import com.empresa.crudmixto.entity.Empleado;
import com.empresa.crudmixto.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService EmpleadoService;

    // Listar empleados
    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = EmpleadoService.obtenerTodos();
        model.addAttribute("empleados", empleados);
        return "empleados/lista";
    }

    // Mostrar formulario para crear empleado
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleados/formulario";
    }

    // Guardar empleado (crear o actualizar)
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado, Model model) {
        try {
            EmpleadoService.guardar(empleado);
            return "redirect:/empleados";
        } catch (Exception e) {
            model.addAttribute("empleado", empleado);
            model.addAttribute("error", "El email ya está registrado.");
            return "empleados/formulario";
        }
    }

    // Editar empleado
    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable Long id, Model model) {
        Empleado empleado = EmpleadoService.obtenerPorId(id);
        model.addAttribute("empleado", empleado);
        return "empleados/formulario";
    }

    // Eliminar empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        EmpleadoService.eliminar(id);
        return "redirect:/empleados";
    }

    // Búsqueda por nombre o cargo
    @GetMapping("/buscar")
    public String buscarEmpleados(@RequestParam String query, Model model) {
        List<Empleado> empleados = EmpleadoService.buscarPorNombreOCargo(query);
        model.addAttribute("empleados", empleados);
        return "empleados/lista";
    }
}