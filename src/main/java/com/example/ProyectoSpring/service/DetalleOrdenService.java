/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ProyectoSpring.service;

import com.example.ProyectoSpring.model.DetalleOrden;
import com.example.ProyectoSpring.model.Orden;
import java.util.List;
/**
 *
 * @author User
 */
public interface DetalleOrdenService {
    public void save(DetalleOrden detalleOrden);
    List<DetalleOrden> findByOrden(Orden orden);
    List<DetalleOrden> finByAll();
}
