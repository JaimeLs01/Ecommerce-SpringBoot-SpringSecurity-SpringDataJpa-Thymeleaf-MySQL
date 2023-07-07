/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ProyectoSpring.service;

import com.example.ProyectoSpring.model.Orden;
import java.util.List;

/**
 *
 * @author User
 */
public interface OrdenService {
    public List<Orden> findAll();
    public void save(Orden orden);
    public String generarNumeroOrden();
}
