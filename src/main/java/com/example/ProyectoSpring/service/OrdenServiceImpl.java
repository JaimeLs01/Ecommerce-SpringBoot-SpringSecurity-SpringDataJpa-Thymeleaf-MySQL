/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.service;

import com.example.ProyectoSpring.model.Orden;
import com.example.ProyectoSpring.repository.OrdenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImpl implements OrdenService{
    @Autowired
    private OrdenRepository ordenRepository;
    @Override
    public void save(Orden orden){
        ordenRepository.save(orden);
    }
    @Override
    public List<Orden> findAll(){
        return ordenRepository.findAll();
    }
    public String generarNumeroOrden(){
        int numero;
        String ultimo;
        List<Orden> ordenes = findAll();
        if(ordenes.isEmpty()){
            numero=1;
        }else{
            numero=Integer.parseInt(ordenes.get(ordenes.size()-1).getNumero())+1;
        }
        ultimo=String.format("%08d", numero);
        return ultimo;
    }
}
