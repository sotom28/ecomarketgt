package com.example.ecomarketgt.Controller;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;







@Tag(name = "Usuario API v1", description = "Operaciones CRUD para la gestión de usuarios - Versión 1")
@RestController
@RequestMapping("/api/usuario") // ruta base para el controlador api/usuarioo
public class UsuariooController {

 

    @Autowired
    private UsuariooService usuarioService;

    @Operation(summary = "Métodos disponibles", description = "Lista todos los endpoints disponibles en esta API")
    @GetMapping("/metodos")
    public ResponseEntity<List<String>> metodosdisponibles(){
    List<String> metodos = List.of(
        "GET /api/usuario/listar - listar todos los usuarios",
        "POST /api/usuario/guardar - guardar un usaurio",
        "GET /api/usuario/{id} - buscar un usuario por id",
        "DELETE /api/usuario/eliminarporid - eliminar un usuario por id",
        "GET /api/usuario/buscarnombre - buscar un usuario por nombre",
        "GET /api/usuario/buscaremail - buscar un usuario por email",
        "GET /api/usuario/buscarrut - buscar un usuario por rut",
        "PUT /api/usuario/actualizar - actualizar un usuario por id"

    );

    return ResponseEntity.ok(metodos);
    
    }

    // ok
    // listar todos los usuarios
    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados") 
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
    @Operation(summary = "Guardar un usuario", description = "Guarda un nuevo usuario en la base de datos")
    @PostMapping("/guardar")
    public ResponseEntity<Usuarioo> guardar(@Valid @RequestBody Usuarioo usuarioo) {
        try {
            Usuarioo nuevoUsuarioo = usuarioService.Guardar(usuarioo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
       
    

    ///ok 
    //// metodos para eliminar un usuario por id
    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario de la base de datos por su ID")
    @DeleteMapping("/eliminarid/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id){
        try{
        usuarioService.delete(id);
            return ResponseEntity.ok().body("usuario sea eliminado exitosamente") ;
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
             
        }
    
    }
    //ok
    //metodos para buscar un usuario por id 
    @Operation(summary = "Obtener un usuario por ID", description = "Busca un usuario en la base de datos por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuarioo> obtenerPorId(@PathVariable long id){
        Usuarioo usuarioo = usuarioService.findById(id);
        if(usuarioo == null){
            return ResponseEntity.notFound().build(); // Devuelve un 404 si no se encuentra el usuario
        }
        return ResponseEntity.ok(usuarioo); // Devuelve el usuario encontrado 200 OK
    }

    




   
       // ok    // actualizar un usuario por id 
       @Operation(summary = "Actualizar un usuario por ID", description = "Actualiza un usuario existente en la base de datos por su ID")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuarioo> actualizarUsuario(@PathVariable long id, @RequestBody Usuarioo usuarioo) {
    try {
        
        usuarioo.setId(id); // Establece el ID del usuario a actualizar
        // Verifica si el usuario existe
        Usuarioo usuarioActualizado = usuarioService.update(usuarioo);
        return ResponseEntity.ok(usuarioActualizado);
    }catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    }

    // ok 
    // buscar usuario por nombres
    @Operation
   @GetMapping("/buscarnombre/{nombres}")
    public ResponseEntity<?> buscarUsuarioNombre(@Valid @PathVariable String nombres) {
    // Validación: Verifica si el nombre está vacío o en blanco
    if (nombres.isBlank()) {
        return ResponseEntity.badRequest().body("Ingrese un nombre válido");
    }

    // Llama al servicio para buscar usuarios por nombre
    List<Usuarioo> usuarios = usuarioService.findByNombres(nombres);

    // Verifica si no se encontraron usuarios
    if (usuarios.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se han encontrado usuarios que coincidan con el nombre");
    
        
    }

    // Devuelve la lista de usuarios encontrados
    return ResponseEntity.ok(usuarios);
    }




    // ok 
    // buscar usuario por email
    @Operation(summary = "Buscar usuario por email", description = "Busca un usuario en la base de datos por su email")
    @GetMapping("/buscaremail/{email}")
    public ResponseEntity<Usuarioo> buscarPorEmail( @PathVariable String email) {
        List<Usuarioo> usuarios = usuarioService.findByEmail(email);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios.get(0));
    }

    // ok 
    @Operation(summary = "Buscar usuario por RUT", description = "Busca un usuario en la base de datos por su RUT")
    @GetMapping("/buscarrut/{rut}")
    public  ResponseEntity<Usuarioo> buscarPorRut(@PathVariable String rut) {
        List<Usuarioo> usuarios = usuarioService.findByRut(rut);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios.get(0));
        
    }


    
}



    
   


      



