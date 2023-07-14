/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoSpring.service;

import com.example.ProyectoSpring.model.DetalleOrden;
import com.example.ProyectoSpring.model.Orden;
import com.example.ProyectoSpring.repository.DetalleOrdenRepository;
import java.util.List;
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

    @Override
    public List<DetalleOrden> findByOrden(Orden orden) {
        return detalleOrdenRepository.findByOrden(orden);
    }

    @Override
    public List<DetalleOrden> finByAll() {
        return detalleOrdenRepository.findAll();
    }
}
