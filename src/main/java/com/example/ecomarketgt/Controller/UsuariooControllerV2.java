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

import org.springframework.web.bind.annotation.DeleteMapping;
/// heteoas
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v2/usuario") // nueva ruta base para la versión 2 del controlador
public class UsuariooControllerV2 {

@Autowired    
private UsuariooService usuarioService;








 // GET /api/v2/usuario/{id}
// Aquí puedes agregar los métodos específicos para la versión 2 del controlador
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
@PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
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
@PutMapping(value = "/actualizar/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<Usuarioo>> actualizarUsuario (@PathVariable Long id, @RequestBody Usuarioo usuarioo){
    usuarioo.setId(id);
    Usuarioo usuarioActualizado = usuarioService.Guardar(usuarioo);
    EntityModel<Usuarioo> resource = EntityModel.of(usuarioActualizado);
    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
        .obtenerPorId(usuarioActualizado.getId())).withSelfRel());
    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuariooControllerV2.class)
        .listarUsuarios()).withRel("usuarios"));
    return ResponseEntity.ok(resource);
}
        // DELETE /api/v2/usuario/{id}
        @DeleteMapping(value = "eliminar/{id}", produces = MediaTypes.HAL_JSON_VALUE)
        public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }



}