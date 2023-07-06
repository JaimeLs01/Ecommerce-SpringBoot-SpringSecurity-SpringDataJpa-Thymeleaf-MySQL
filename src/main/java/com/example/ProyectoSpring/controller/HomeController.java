/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.Producto;
import com.example.ProyectoSpring.service.ProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductoService productoService; 
    @GetMapping("")
    public String home(Model model){
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos",productos);
        return "usuario/home";
    }
    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id,Model model){
        Producto producto;
        Optional<Producto> optionalProducto= productoService.get(id);
        producto=optionalProducto.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
}
