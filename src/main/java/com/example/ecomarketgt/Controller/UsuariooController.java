package com.example.ecomarketgt.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecomarketgt.Modelo.Usuarioo;
import com.example.ecomarketgt.Service.UsuariooService;







@RestController
@RequestMapping("/api/usuarioo")
public class UsuariooController {

    @Autowired
    private UsuariooService usuariooService;
    
    // listar todos los usuarios 
    @GetMapping("/listar") /// solamente su puede ver en la lista de usuarios 
    public ResponseEntity<List<Usuarioo>> listar(){
        List<Usuarioo> usuarioos = usuariooService.findAll();
        if(usuarioos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarioos);
    }


    @PostMapping /// guardar un usuario en la base de datos
    public ResponseEntity<Usuarioo> guardar(@RequestBody Usuarioo usuarioo){
        Usuarioo nuevoUsuarioo = usuariooService.save(usuarioo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioo);
            
    }
    
   
    
    @GetMapping("/{id}") /// buscar un usuario por id
    public ResponseEntity<Usuarioo> buscar(@PathVariable long id){
        try{
            Usuarioo usuarioo = usuariooService.findById(id);
            return ResponseEntity.ok(usuarioo); 
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<Void> eliminar(@PathVariable long id){
        try{
            usuariooService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        }
 }
        



