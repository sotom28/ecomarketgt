package com.example.ecomarketgt.Repository;

import java.beans.BeanProperty;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.ecomarketgt.Modelo.Usuarioo;
@Repository
// interface que extiende de JpaRepository para realizar 
public interface UsuariooRepository extends JpaRepository<Usuarioo,Long>{
    
    @Query("SELECT p FROM Usuarioo p WHERE LOWER(email) LIKE LOWER(CONCAT('%',:email,'%'))")
    List<Usuarioo> findByEmail(String email);
    

    @Query("SELECT p FROM Usuarioo p WHERE LOWER(nombres) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Usuarioo> findByNombres(String nombres);




    

  

    
}
   




