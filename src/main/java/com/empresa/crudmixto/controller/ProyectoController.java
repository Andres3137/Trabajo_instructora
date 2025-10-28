package com.empresa.crudmixto.controller;

import com.empresa.crudmixto.entity.proyecto;
import com.empresa.crudmixto.entity.Tarea;
import com.empresa.crudmixto.service.ProyectoService;
import com.empresa.crudmixto.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private EmpleadoService empleadoService;

    // Listar proyectos
    @GetMapping
    public String listarProyectos(Model model) {
        List<proyecto> proyectos = proyectoService.obtenerTodos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos/lista";
    }

    // Mostrar formulario para crear proyecto
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
    proyecto nuevoProyecto = new proyecto();
    nuevoProyecto.setId(null); // Asegura que el id sea null
    model.addAttribute("proyecto", nuevoProyecto);
    model.addAttribute("empleados", empleadoService.obtenerTodos());
    return "proyectos/formulario";
    }

    // Guardar proyecto (crear o actualizar)
   @PostMapping("/guardar")
    public String guardarProyecto(@ModelAttribute proyecto proyecto, Model model) {
    if (proyecto.getTareas() == null) {
        proyecto.setTareas(new ArrayList<>());
    }
    proyecto proyectoGuardado = proyectoService.guardar(proyecto);
    System.out.println("ID generado: " + proyectoGuardado.getId());
    // Si el ID es null, redirige a la lista
    if (proyectoGuardado.getId() == null || proyectoGuardado.getId().isEmpty()) {
        return "redirect:/proyectos";
    }
    return "redirect:/proyectos/editar/" + proyectoGuardado.getId();
    }

    // Editar proyecto
    @GetMapping("/editar/{id}")
    public String editarProyecto(@PathVariable String id, Model model) {
        proyecto proyecto = proyectoService.obtenerPorId(id);
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("empleados", empleadoService.obtenerTodos());
        return "proyectos/formulario";
    }

    // Eliminar proyecto
    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable String id) {
        proyectoService.eliminar(id);
        return "redirect:/proyectos";
    }

    // Agregar tarea
    @PostMapping("/{id}/tarea/agregar")
    public String agregarTarea(@PathVariable String id, @RequestParam String descripcion) {
        proyecto proyecto = proyectoService.obtenerPorId(id);
        if (proyecto.getTareas() == null) {
            proyecto.setTareas(new java.util.ArrayList<>());
        }
        proyecto.getTareas().add(new Tarea(descripcion));
        proyectoService.guardar(proyecto);
        return "redirect:/proyectos/editar/" + id;
    }
    @PostMapping("/{id}/tarea/editar")
    public String editarTarea(@PathVariable String id, @RequestParam int tareaIndex, @RequestParam String descripcion, @RequestParam String estado) {
        proyecto proyecto = proyectoService.obtenerPorId(id);
        if (proyecto.getTareas() != null && tareaIndex < proyecto.getTareas().size()) {
            Tarea tarea = proyecto.getTareas().get(tareaIndex);
            tarea.setDescripcion(descripcion);
            tarea.setEstado(estado);
            proyectoService.guardar(proyecto);
        }
        return "redirect:/proyectos/editar/" + id;
    }

    // Completar tarea
    @PostMapping("/{id}/tarea/completar")
    public String completarTarea(@PathVariable String id, @RequestParam int tareaIndex) {
        proyecto proyecto = proyectoService.obtenerPorId(id);
        if (proyecto.getTareas() != null && tareaIndex < proyecto.getTareas().size()) {
            proyecto.getTareas().get(tareaIndex).setCompletada(true);
            proyectoService.guardar(proyecto);
        }
        return "redirect:/proyectos/editar/" + id;
    }

    // Eliminar tarea
    @PostMapping("/{id}/tarea/eliminar")
    public String eliminarTarea(@PathVariable String id, @RequestParam int tareaIndex) {
        proyecto proyecto = proyectoService.obtenerPorId(id);
        if (proyecto.getTareas() != null && tareaIndex < proyecto.getTareas().size()) {
            proyecto.getTareas().remove(tareaIndex);
            proyectoService.guardar(proyecto);
        }
        return "redirect:/proyectos/editar/" + id;
    }
    @PostMapping("/{id}/tarea/estado")
    public String cambiarEstadoTarea(@PathVariable String id, @RequestParam int tareaIndex, @RequestParam String estado) {
        proyecto proyecto = proyectoService.obtenerPorId(id);
        if (proyecto.getTareas() != null && tareaIndex < proyecto.getTareas().size()) {
            proyecto.getTareas().get(tareaIndex).setEstado(estado);
            proyectoService.guardar(proyecto);
        }
        return "redirect:/proyectos/editar/" + id;
    }
}