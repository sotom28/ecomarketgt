package com.example.ecomarketgt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomarketgt.Modelo.Usuarioo;
// interface que extiende de JpaRepository para realizar 
public interface UsuariooRepository extends JpaRepository<Usuarioo,Long>{
    Usuarioo findByNombre (String nombre);
    Usuarioo findByEmail(String email);

    
}
   




