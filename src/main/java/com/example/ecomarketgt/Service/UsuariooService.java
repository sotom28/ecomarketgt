package com.example.ecomarketgt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarketgt.Modelo.Usuarioo;
import com.example.ecomarketgt.Repository.UsuariooRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UsuariooService {

    @Autowired
    private UsuariooRepository usuariooRepository;
    /// revista lista de usuarios
    public List<Usuarioo> findAll(){
        return usuariooRepository.findAll();
    }
    // crear usuario en la base de datos
    public Usuarioo guardar(Usuarioo usuarioo) {
        return usuariooRepository.save(usuarioo);
    }
    /// metodo para eliminar un suario por id
    public void delete(long id){
        usuariooRepository.deleteById(id);
    }
    
    
    public Usuarioo save(Usuarioo usuarioo) {
        // implementar la logica para guardar el usuario en la base de datos
        return usuarioo;
    
    
    }
    public Usuarioo findById(long id) {
        return usuariooRepository.findById(id).orElse(null);
    }

    
    




}