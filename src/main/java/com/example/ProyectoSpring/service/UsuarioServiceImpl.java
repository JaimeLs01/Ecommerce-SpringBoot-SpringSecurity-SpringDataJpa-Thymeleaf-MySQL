/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.service;

import com.example.ProyectoSpring.model.Usuario;
import com.example.ProyectoSpring.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public Optional<Usuario> findById(Integer id){
        return usuarioRepository.findById(id);
    }
    @Override
    public void save(Usuario usuario){
       usuarioRepository.save(usuario);
    }
}
