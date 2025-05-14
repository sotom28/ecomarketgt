package com.example.ecomarketgt.Modelo;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor // contructor con parametros
@NoArgsConstructor // contructor sin parametros
@Data // getters y setters 
@Entity // indica que es una entidad de la base de datos
@Table(name = "usuario")  //nombre de la tabla en la base de datos

public class Usuarioo {
    @Id // indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar el id automaticamente|
    private long id;


    @Column(nullable = false) // 
    private String rut;// rut del usuario
    
    @Column(nullable = false)
    private String nombres; // nombre del usuario

    @Column(nullable = false)
    private String apellidos; // apellido del usuario

    
    @Column(nullable = false )
    private String email; // email del usuario
        
    @Column(nullable = false)
    private String telefono; // telefono del usuario
    
    @Column(nullable = false)
    private String direccion;
    
    @Column(nullable = false)
    private String password;

    
   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPerfil", nullable = false)
    private Perfil perfil;

  
}
