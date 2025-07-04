package com.example.ecomarketgt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomarketgt.Modelo.Usuarioo;
import com.example.ecomarketgt.Service.UsuariooService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
/// heteoas
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@Tag(name = "Usuario API v2 (HATEOAS)", description = "Operaciones CRUD para la gestión de usuarios con HATEOAS - Versión 2")
@RestController
@RequestMapping("/api/v2/usuario") // nueva ruta base para la versión 2 del controlador
public class UsuariooControllerV2 {

@Autowired    
private UsuariooService usuarioService;

@Operation(summary = "Métodos disponibles v2", description = "Lista todos los endpoints disponibles en la API v2 con HATEOAS")
@GetMapping("/metodos")
public ResponseEntity<List<String>> metodosDisponibles() {
    List<String> metodos = List.of(
        "GET /api/v2/usuario/listar           - Listar todos los usuarios",
        "POST /api/v2/usuario/crear          - Crear un usuario",
        "GET /api/v2/usuario/{id}          - Buscar un usuario por id",
        "PUT /api/v2/usuario/actualizar/{id}      - Actualizar un usuario por id",
        "DELETE /api/v2/usuario/eliminar/{id}   - Eliminar un usuario por id"
    );
    return ResponseEntity.ok(metodos);
}




 // GET /api/v2/usuario/{id}
// Aquí puedes agregar los métodos específicos para la versión 2 del controlador
@Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario por su ID en la versión 2 del controlador")
@GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Usuarioo>> obtenerPorId(@PathVariable long id) {
    Usuarioo usuario = usuarioService.findById(id);
    if (usuario == null) {
        return ResponseEntity.notFound().build();
    }
    EntityModel<Usuarioo> resource = EntityModel.of(usuario);
    resource.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class).obtenerPorId(id)).withSelfRel());
    resource.add(WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class).listarUsuarios()).withRel("usuarios"));
    return ResponseEntity.ok(resource);
}


   // GET /api/v2/usuario 
///// // listar todos los usuarios en la version 2
/// 
@Operation (summary = "Listar usuarios", description = "Obtiene una lista de todos los usuarios en la versión 2 del controlador")
@GetMapping(value = "/listar", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<CollectionModel<EntityModel<Usuarioo>>> listarUsuarios() {
    List<Usuarioo> usuarios = usuarioService.findAll();
    List<EntityModel<Usuarioo>> usuarioResources = usuarios.stream()
        .map(usuario -> {
            EntityModel<Usuarioo> resource = EntityModel.of(usuario);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
                .obtenerPorId(usuario.getId())).withSelfRel());
            return resource;
        })
        .toList();
    CollectionModel<EntityModel<Usuarioo>> collectionModel = CollectionModel.of(usuarioResources);
    collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
        .listarUsuarios()).withSelfRel());
    return ResponseEntity.ok(collectionModel);
        }

   // POST /api/v2/usuario
////////// crear un nuevo usuario en la version 2
@Operation(summary = "Crear usuario", description = "Crea un nuevo usuario en la versión 2 del controlador")
@PostMapping(value = "/crear",produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Usuarioo>> crearUsuario (@RequestBody  Usuarioo usuarioo){
    Usuarioo nuevoUsuario = usuarioService.Guardar(usuarioo);
    EntityModel<Usuarioo> resource = EntityModel.of(nuevoUsuario);
    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
        .obtenerPorId(nuevoUsuario.getId())).withSelfRel());
    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
        .listarUsuarios()).withRel("usuarios"));
    return ResponseEntity.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
        .obtenerPorId(nuevoUsuario.getId())).toUri())
        .body(resource);

    }
// PUT /api/v2/usuario/{id}
//////// // actualizar un usuario en la version 2
@Operation(summary = "Actualizar usuario", description = "Actualiza un usuario existente por su ID en la versión 2 del controlador")
@PutMapping(value = "/actualizar/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Usuarioo>> actualizarUsuario(@PathVariable Long id, @RequestBody Usuarioo usuarioo) {
    try {
        usuarioo.setId(id);
        Usuarioo usuarioActualizado = usuarioService.Guardar(usuarioo);
        EntityModel<Usuarioo> resource = EntityModel.of(usuarioActualizado);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
            .obtenerPorId(usuarioActualizado.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
            .listarUsuarios()).withRel("usuarios"));
        return ResponseEntity.ok(resource);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(EntityModel.of(new Usuarioo()).add(
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class).listarUsuarios()).withRel("usuarios")));
    }
}
        

// DELETE /api/v2/usuario/{id}
        @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID en la versión 2 del controlador")
        @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
        public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }


    }

