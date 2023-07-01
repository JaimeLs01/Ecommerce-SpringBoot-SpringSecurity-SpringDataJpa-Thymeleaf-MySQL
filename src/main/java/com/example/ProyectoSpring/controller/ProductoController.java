/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.Producto;
import com.example.ProyectoSpring.model.Usuario;
import com.example.ProyectoSpring.service.ProductoService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos",productoService.findAll());
        return "/productos/show";
    }
    @GetMapping("/create")
    public String create(){
        return "/productos/create";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        Producto producto;
        Optional<Producto> optionalProducto= productoService.get(id);
        producto = optionalProducto.get();
        model.addAttribute("producto",producto);
        LOGGER.info("Este es el producto {}", producto);
        return "/productos/edit";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,Model model){
        productoService.delete(id);
        return "redirect:/productos";
    }
    
    @PostMapping("/save")
    public String save(Producto producto){
        Usuario usuario = new Usuario(1,"","","","","","","");
        producto.setUsuario(usuario);
        productoService.save(producto);
        LOGGER.info("Este es el objeto producto {}",producto);
        return "redirect:/productos";
    }
    
    @PostMapping("/update")
    public String update(Producto producto){
        productoService.save(producto);
        return "redirect:/productos";
    }

}
