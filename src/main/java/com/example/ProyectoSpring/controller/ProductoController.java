/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.Producto;
import com.example.ProyectoSpring.model.Usuario;
import com.example.ProyectoSpring.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String show(){
        return "/productos/show";
    }
    @GetMapping("/create")
    public String create(){
        return "/productos/create";
    }
    @PostMapping("/save")
    public String save(Producto producto){
        Usuario usuario = new Usuario(1,"","","","","","","");
        producto.setUsuario(usuario);
        productoService.save(producto);
        LOGGER.info("Este es el objeto producto {}",producto);
        return "redirect:/productos";
    }
}
