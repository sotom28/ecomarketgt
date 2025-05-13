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
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("/api/usuario") // ruta base para el controlador api/usuarioo
public class UsuariooController {

 

    @Autowired
    private UsuariooService usuarioService;

    @GetMapping("/metodos")
    public ResponseEntity<List<String>> metodosdisponibles(){
    List<String> metodos = List.of(
        "GET /api/usuario/listar - listar todos los usuarios",
        "POST /api/usuario/guardar - guardar un usaurio",
        "GET /api/usuario/obtenerporid - buscar un usuario por id",
        "DELETE /api/usuario/eliminarporid - eliminar un usuario por id"
    );

    return ResponseEntity.ok(metodos);
    
    }

    // ok
    // listar todos los usuarios 
    @GetMapping("/listar") /// solamente su puede ver en la lista de usuarios 
    public ResponseEntity<List<Usuarioo>> listar(){
        List<Usuarioo> usuarioos = usuarioService.findAll();
        if(usuarioos.isEmpty()){
            return ResponseEntity.noContent().build();
         
        }
        return ResponseEntity.ok(usuarioos);
    }

    //ok 
    //guardar un usuario en la base de datos
    @PostMapping("/guardar")
    public ResponseEntity<Usuarioo> guardar(@RequestBody Usuarioo usuarioo) {
        Usuarioo nuevoUsuarioo = usuarioService.guardar(usuarioo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioo);
            
    }
    

    ///ok 
    //// metodos para eliminar un usuario por id
    @DeleteMapping("/eliminarporid/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id){
        try{
        usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
             
        }
    
    }
    //ok
    //metodos para buscar un usuario por id 
    @GetMapping("/obtenerporid/{id}")
    public ResponseEntity<Usuarioo> obtenerPorId(@PathVariable long id){
        Usuarioo usuarioo = usuarioService.findById(id);
        if(usuarioo == null){
            return ResponseEntity.notFound().build(); // Devuelve un 404 si no se encuentra el usuario
        }
        return ResponseEntity.ok(usuarioo); // Devuelve el usuario encontrado 200 OK
    }

    




   
       
       // espera
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuarioo> actualizarUsuario(@PathVariable long id, @RequestBody Usuarioo usuarioo) {
    try {
        usuarioo.setId(id);
        Usuarioo usuarioActualizado = usuarioService.actualizarUsuario(usuarioo);
        return ResponseEntity.ok(usuarioActualizado);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    }

    // espera
    // buscar usuario por nombres
    @GetMapping("/buscarnombres/{nombres}")
    public ResponseEntity<List<Usuarioo>> buscarPorNombres(@PathVariable String nombres) {
        List<Usuarioo> usuarios;
        try {
            usuarios = usuarioService.findByNombres(nombres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (usuarios == null || usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }


}



    
   


      



