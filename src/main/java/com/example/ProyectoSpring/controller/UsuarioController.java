/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.Orden;
import com.example.ProyectoSpring.model.Usuario;
import com.example.ProyectoSpring.service.DetalleOrdenService;
import com.example.ProyectoSpring.service.OrdenService;
import com.example.ProyectoSpring.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@RequestMapping("/usuario")
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    OrdenService ordenService;
    
    @Autowired
    DetalleOrdenService detalleService;

    @GetMapping("/registro")
    public String registro() {
        return "usuario/registro";
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario,HttpSession session) {
        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
            }
        }

        return "redirect:/usuario/login";
    }
    @GetMapping("/compras")
    public String compras(Model model,HttpSession sesion){
        model.addAttribute("sesion", sesion.getAttribute("idusuario"));
        Usuario usuario = usuarioService.findById(Integer.parseInt(sesion.getAttribute("idusuario").toString())).get();
        model.addAttribute("compras", ordenService.findByUsuario(usuario));
        return "usuario/compras";
    }
    @GetMapping("/detalles/{id}")
    public String detalles(HttpSession sesion,Model model,@PathVariable Integer id){
        Orden orden= ordenService.findById(id).get();
        detalleService.findByOrden(orden);
        model.addAttribute("sesion",sesion.getAttribute("idusuario"));
        model.addAttribute("detalles", detalleService.findByOrden(orden));
        return "usuario/detallecompra";
    }
}
