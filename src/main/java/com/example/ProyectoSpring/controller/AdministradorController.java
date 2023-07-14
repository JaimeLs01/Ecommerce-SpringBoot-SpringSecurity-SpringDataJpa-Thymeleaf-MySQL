/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.Orden;
import com.example.ProyectoSpring.model.Producto;
import com.example.ProyectoSpring.service.DetalleOrdenService;
import com.example.ProyectoSpring.service.OrdenService;
import com.example.ProyectoSpring.service.ProductoService;
import com.example.ProyectoSpring.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private DetalleOrdenService detalleService;
    @GetMapping("")
    public String home(Model model){
        List<Producto> productos=productoService.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }
    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios",usuarioService.findAll());
        return "/administrador/usuarios";
    }
    @GetMapping("/ordenes")
    public String ordenes(Model model){
        model.addAttribute("ordenes",ordenService.findAll());
        return "administrador/ordenes";
    }
    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Integer id,Model model){
        Orden orden = ordenService.findById(id).get();
        model.addAttribute("detalles",detalleService.findByOrden(orden));
        return "administrador/detallecompra";
    }
}
