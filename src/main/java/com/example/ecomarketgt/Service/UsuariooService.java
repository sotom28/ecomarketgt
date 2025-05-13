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
    
    // guardar usuario en la base de datos
    public Usuarioo guardar(Usuarioo usuarioo) {
        return usuariooRepository.save(usuarioo);
    }


    /// eliminar usuario por id
    public void delete(long id) {
        if (usuariooRepository.existsById(id)) {
            usuariooRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El usuario con la ID " + id + " no existe");
        }
    }
    // buscar usuario por id
    public Usuarioo findById(long id) {
        return usuariooRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("El usuario con la ID " + id + " no existe"));
    }

    ///// actualizar usuario
    public Usuarioo actualizarUsuario(Usuarioo usuarioo){
        return usuariooRepository.save(usuarioo);
    }

        
    //buscar usuario por nombres
    public List<Usuarioo> findByNombres(String nombres){
        return usuariooRepository.findByNombres(nombres);
    }


}


     

