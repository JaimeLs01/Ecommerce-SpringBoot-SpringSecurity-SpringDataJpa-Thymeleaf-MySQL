/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.controller;

import com.example.ProyectoSpring.model.Producto;
import com.example.ProyectoSpring.model.Usuario;
import com.example.ProyectoSpring.service.ProductoService;
import com.example.ProyectoSpring.service.UploadFileService;
import com.example.ProyectoSpring.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UploadFileService upload;
    
    @Autowired
    private UsuarioService usuarioService;
    
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
        Producto p;
        p=productoService.get(id).get();
        
        if(p.getImagen().equals("default.jpg")){
            upload.deleteImage(p.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }
    
    @PostMapping("/save")//test
    public String save(Producto producto,@RequestParam("img") MultipartFile file,HttpSession session) throws IOException{
        Usuario usuario = usuarioService.findById((Integer.parseInt(session.getAttribute("idusuario").toString()))).get();
        producto.setUsuario(usuario);
        if(producto.getId()==null){
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoService.save(producto);
        LOGGER.info("Este es el objeto producto {}",producto);
        return "redirect:/productos";
    }
    
    @PostMapping("/update")//test
    public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException{
        Producto p;
        p=productoService.get(producto.getId()).get();
        if(file.isEmpty()){
                producto.setImagen(p.getImagen());
            }else{

            if(p.getImagen().equals("default.jpg")){
                upload.deleteImage(p.getImagen());
            }
                String nombreImagen = upload.saveImage(file);
                producto.setImagen(nombreImagen);
            }
        producto.setUsuario(p.getUsuario());
        productoService.save(producto);
        return "redirect:/productos";
    }

}
