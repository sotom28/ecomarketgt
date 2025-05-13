package com.example.ecomarketgt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.example.ecomarketgt.Modelo.Usuarioo;
@Repository
// interface que extiende de JpaRepository para realizar 
public interface UsuariooRepository extends JpaRepository<Usuarioo,Long>{
    Usuarioo findByNombres (String nombres);
    Usuarioo findByEmail(String email);
    


    

  

    
}
   




