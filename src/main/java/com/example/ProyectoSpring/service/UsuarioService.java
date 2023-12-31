/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ProyectoSpring.service;

import com.example.ProyectoSpring.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
public interface UsuarioService{
    public Optional<Usuario> findById(Integer id);
    public void save(Usuario usuario);
    public Optional<Usuario> findByEmail(String email);
    public List<Usuario> findAll();
}
