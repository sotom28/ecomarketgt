package com.example.ecomarketgt.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.ecomarketgt.Modelo.Usuarioo;// importando la clase Usuarioo
@Repository
// interface que extiende de JpaRepository para realizar 
public interface UsuariooRepository extends JpaRepository<Usuarioo,Long>{
    
    @Query("SELECT p FROM Usuarioo p WHERE LOWER(email) LIKE LOWER(CONCAT('%',:email,'%'))")  // consulta para buscar por email
    List<Usuarioo> findByEmail(String email);


    @Query("SELECT p FROM Usuarioo p WHERE LOWER(nombres) LIKE LOWER(CONCAT('%',:nombres,'%'))") // consulta para buscar por nombres
    List<Usuarioo> findByNombres(String nombres);

    @Query("SELECT p FROM Usuarioo p WHERE LOWER(rut) LIKE LOWER(CONCAT('%',:rut,'%'))") // consulta para buscar por rut
    List<Usuarioo> findByRut(String rut);

    

    

  

    
}
   




