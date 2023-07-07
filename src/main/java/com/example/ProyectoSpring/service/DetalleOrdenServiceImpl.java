/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.service;

import com.example.ProyectoSpring.model.DetalleOrden;
import com.example.ProyectoSpring.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;
    @Override
    public void save(DetalleOrden detalleOrden){
        detalleOrdenRepository.save(detalleOrden);
    }
}