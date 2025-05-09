package com.example.ecomarketgt.Modelo;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "usuario")  //nombre de la tabla en la base de datos

@Entity // indica que es una entidad de la base de datos
public class Usuarioo {
    @Id // indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String telefono;
    
    @Column(nullable = false)
    private String direccion;
    
    @Column(nullable = false)
    private String password;

    


    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

  
}
