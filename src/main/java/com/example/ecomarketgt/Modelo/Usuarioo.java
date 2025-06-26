package com.example.ecomarketgt.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor // contructor con parametros
@NoArgsConstructor // contructor sin parametros
@Data // getters y setters 
@Entity // indica que es una entidad de la base de datos
@Table(name = "usuario")  //nombre de la tabla en la base de datos

public class Usuarioo {
    @Schema(description = "ID único del usuario", example = "1")
    @Id // indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar el id automaticamente|
    private long id;

    @Schema(description = "RUT del usuario", example = "12345678-9")
    @NotBlank(message = "El RUT no puede estar vacío") // valida que el campo no este vacio
    @Column(nullable = false) // 
    private String rut;// rut del usuario
    

    @Schema(description = "Nombres del usuario", example = "Juan Perez")
    @NotBlank(message = "El nombre no puede estar vacío") // valida que el campo no este vacio
    @Column(nullable = false)
    private String nombres; // nombre del usuario

    
    @Schema(description = "Apellidos del usuario", example = "Perez")
    @Column(nullable = false)
    private String apellidos; // apellido del usuario

    @Schema(description = "Email del usuario", example = "juan.perez@example.com")
    @Column(nullable = false )
    private String email; // email del usuario
    
    @Schema(description = "Teléfono del usuario", example = "123456789")
    @Column(nullable = false)
    private long telefono; // telefono del usuario
    
    @Schema(description = "Dirección del usuario", example = "Calle Falsa 123")
    @Column(nullable = false)
    private String direccion; // direccion del usuario
    
    @Schema(description = "Contraseña del usuario", example = "password123")
    @Column(nullable = false)
    private String password; // contraseña del usuario

    
    @Schema(description = "Perfil del usuario")
    @ManyToOne(fetch = FetchType.EAGER) // relacion muchos a uno
    // EAGER: carga los datos de la tabla relacionada al momento de cargar el usuario
    @JoinColumn(name = "idPerfil", nullable = false) // llave foranea
    private Perfil perfil; // perfil del usuario

  
}
