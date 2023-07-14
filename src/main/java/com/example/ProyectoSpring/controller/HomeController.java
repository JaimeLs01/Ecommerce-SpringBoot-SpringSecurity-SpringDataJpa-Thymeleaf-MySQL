/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.DetalleOrden;
import com.example.ProyectoSpring.model.Orden;
import com.example.ProyectoSpring.model.Producto;
import com.example.ProyectoSpring.model.Usuario;
import com.example.ProyectoSpring.service.DetalleOrdenService;
import com.example.ProyectoSpring.service.OrdenService;
import com.example.ProyectoSpring.service.ProductoService;
import com.example.ProyectoSpring.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private DetalleOrdenService detalleService;

    List<DetalleOrden> detalles = new ArrayList<>();
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        Producto producto;
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }

    @PostMapping("/addCart")
    public String addCart(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad) {
        Producto producto;
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        DetalleOrden detalleOrden = new DetalleOrden();
        if (ValidarProductoExiste(producto.getId()) == null) {
            detalleOrden.setCantidad(cantidad);
            detalleOrden.setNombre(producto.getNombre());
            detalleOrden.setPrecio(producto.getPrecio());
            detalleOrden.setTotal(cantidad * producto.getPrecio());
            detalleOrden.setProducto(producto);
            detalles.add(detalleOrden);
            orden.setTotal(ObtenerTotal());
        }
        return "redirect:/cart";
    }

    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable Integer id) {
        for (int i = 0; i < detalles.size(); i++) {
            if (detalles.get(i).getProducto().getId().equals(id)) {
                detalles.remove(i);
            }
        }
        orden.setTotal(ObtenerTotal());
        return "redirect:/cart";
    }

    double ObtenerTotal() {
        double sumaTotal = 0;
        for (int i = 0; i < detalles.size(); i++) {
            sumaTotal += detalles.get(i).getTotal();
        }
        return sumaTotal;
    }

    Integer ValidarProductoExiste(Integer id) {
        Integer posicion = null;
        for (int i = 0; i < detalles.size(); i++) {
            if (detalles.get(i).getProducto().getId().equals(id)) {
                posicion = i;
            }
        }
        return posicion;
    }

    @GetMapping("/generarOrden")
    public String generarOrden(HttpSession session) {
        Date fechaCreacio = new Date();
        Optional<Usuario> usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
        Usuario us = usuario.get();
        orden.setFechaCreacion(fechaCreacio);
        orden.setNumero(ordenService.generarNumeroOrden());
        orden.setUsuario(us);
        ordenService.save(orden);
        for (int i = 0; i < detalles.size(); i++) {
            detalles.get(i).setOrden(orden);
            detalleService.save(detalles.get(i));
        }
        orden = new Orden();
        detalles.clear();
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("detalles", detalles);
        model.addAttribute("total", orden.getTotal());
        return "usuario/carrito";
    }

    @GetMapping("/resumenOrden")
    public String detalle(Model model,HttpSession session) {
        Optional<Usuario> usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
        Usuario us = usuario.get();
        model.addAttribute("detalles", detalles);
        model.addAttribute("total", orden.getTotal());
        model.addAttribute("usuario", us);
        return "usuario/resumenorden";
    }
    @PostMapping("/search")
    public String search(@RequestParam("nombre") String nombre,Model model){
        List<Producto> productos = productoService.findAll();
        List<Producto> producto = new ArrayList<>();
        productos.stream().filter( p -> p.getNombre().contains(nombre));
        for(int i=0;i<productos.size();i++){
            if(productos.get(i).getNombre().toLowerCase().contains(nombre.toLowerCase())){
                producto.add(productos.get(i));
            }
        }
        model.addAttribute("productos", producto);
        return "usuario/home";
    }
}
