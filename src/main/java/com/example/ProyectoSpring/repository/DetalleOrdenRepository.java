/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ProyectoSpring.repository;

import com.example.ProyectoSpring.model.DetalleOrden;
import com.example.ProyectoSpring.model.Orden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author User
 */
@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden,Integer>{
    List<DetalleOrden> findByOrden(Orden orden);
}
