/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.DetalleOrden;
import com.example.ProyectoSpring.model.Orden;
import com.example.ProyectoSpring.model.Producto;
import com.example.ProyectoSpring.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductoService productoService; 
    
    List<DetalleOrden> detalles = new ArrayList<>();
    Orden orden=new Orden();
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
    @PostMapping("/addCart")
    public String addCart(@RequestParam("id") Integer id,@RequestParam("cantidad") Integer cantidad){
        Producto producto;
        double sumaTotal=0;
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        DetalleOrden detalleOrden= new DetalleOrden();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setTotal(cantidad*producto.getPrecio());
        detalleOrden.setProducto(producto);
        detalles.add(detalleOrden);
        for(int i =0;i<detalles.size();i++){
            sumaTotal+=detalles.get(i).getTotal();
        }
        orden.setTotal(sumaTotal);
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("detalles", detalles);
        model.addAttribute("total", orden.getTotal());
        return "usuario/carrito";
    }
}
