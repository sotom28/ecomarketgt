package com.example.ecomarketgt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomarketgt.Modelo.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {


    Perfil findByDescripcion(String descripcion);
    
    // Aquí puedes agregar métodos específicos para manejar la entidad Perfil si es necesario

    // Métodos personalizados pueden ser declarados aquí, por ejemplo:
    // Optional<Perfil> findByDescripcion(String descripcion);

}
