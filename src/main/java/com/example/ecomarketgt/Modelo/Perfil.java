package com.example.ecomarketgt.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor // Constructor con todos los atributos
@NoArgsConstructor // Constructor sin atributos
@Data   // Getters y Setters
@Entity // indica que  es una entidad en la base de datos
@Table(name = "perfil")
public class Perfil {   // Nombre de la tabla en la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el id automáticamente
    private Long idPerfil; // Identificador único del perfil
    
    @Column(nullable = false)   // No puede ser nulo
    private String descripcion; // Descripción del perfil
    
    
    private String permiso; // Permisos asociados al perfil

}
