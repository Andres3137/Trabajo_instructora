package com.empresa.crudmixto.controller;

import com.empresa.crudmixto.entity.Usuario;
import com.empresa.crudmixto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    usuarioRepository.save(usuario);
    System.out.println("Usuario guardado: " + usuario.getUsername());
    model.addAttribute("mensaje", "Usuario registrado correctamente. Ahora puedes iniciar sesión.");
    return "registro";
    }
}