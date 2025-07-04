package com.example.ecomarketgt.Service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



import com.example.ecomarketgt.Modelo.Usuarioo;
import com.example.ecomarketgt.Repository.UsuariooRepository;


@ExtendWith(MockitoExtension.class)
public class UsuariooServiceTest {

    @InjectMocks
    private UsuariooService usuariooService;

    @Mock
    private UsuariooRepository usuariooRepository;

    @Test // test para guardar un usuario
    public void testGuardarUsuario() {
        Usuarioo usuarioo = new Usuarioo();
        usuarioo.setRut("12345678-9");
        usuarioo.setNombres("Juan");
        usuarioo.setApellidos("Pérez");
        when(usuariooRepository.save(any(Usuarioo.class))).thenReturn(usuarioo);
        Usuarioo resultado = usuariooService.Guardar(usuarioo);
        assertNotNull(resultado);
        assertEquals("12345678-9", resultado.getRut());
        assertEquals("Juan", resultado.getNombres());
        assertEquals("Pérez", resultado.getApellidos());
    }

    @Test // test para obtener todos los usuarios
    public void testFindAll(){
        Usuarioo usuarioo = new Usuarioo();
        usuarioo.setRut("12345678-9");
        usuarioo.setNombres("Juan");
        usuarioo.setApellidos("Pérez");
        List<Usuarioo> usuarios = java.util.Arrays.asList(usuarioo);
        when(usuariooRepository.findAll()).thenReturn(usuarios);
        List<Usuarioo> resultado = usuariooService.findAll();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }
    
    @Test // test eliminar usuario
    public void testDeleteUsuario() {
    Usuarioo usuarioExistente = new Usuarioo();
    usuarioExistente.setId(1L);
    usuarioExistente.setNombres("Juan");
    usuarioExistente.setApellidos("Pérez");
    usuarioExistente.setRut("12345678-9");
    // Simula que el usuario existe en la base de datos
    when(usuariooRepository.existsById(1L)).thenReturn(true);
    doNothing().when(usuariooRepository).deleteById(1L);
    usuariooService.delete(1L);
    verify(usuariooRepository, times(1)).deleteById(1L);
}

    @Test // test buscar por NOMBRES
    public void testFindByNombres(){
            Usuarioo usuarioo = new Usuarioo();
            usuarioo.setNombres("Juan");
            List<Usuarioo> usuarios = java.util.Arrays.asList(usuarioo);
            when(usuariooRepository.findByNombres("juan")).thenReturn(usuarios);
            List<Usuarioo> resultado = usuariooService.findByNombres("juan");
            assertFalse(resultado.isEmpty());
            assertEquals("Juan", resultado.get(0).getNombres());

    }
        @Test // test buscar por rut
        public void testFindByRut(){
            Usuarioo usuarioo = new Usuarioo();
            usuarioo.setRut("12345678-9");
            List <Usuarioo> usuarioos= java.util.Arrays.asList(usuarioo);
            when(usuariooRepository.findByRut("12345678-9")).thenReturn(usuarioos);   
            List<Usuarioo> resultado = usuariooService.findByRut("12345678-9");
            assertFalse(resultado.isEmpty());
            assertEquals("12345678-9", resultado.get(0).getRut());
        }

            
            @Test // test para actualizar un usuario
            public void testActualizarUsuario() {
            Usuarioo usuarioaActualizado = new Usuarioo();
            usuarioaActualizado.setId(1L); 
            usuarioaActualizado.setNombres("juan Carlos");
            usuarioaActualizado.setApellidos("Pérez");
            usuarioaActualizado.setRut("12345678-9"); 
            when(usuariooRepository.existsById(1L)).thenReturn(true);
            when(usuariooRepository.save(any(Usuarioo.class))).thenReturn(usuarioaActualizado);
            Usuarioo resultado = usuariooService.update(usuarioaActualizado);
            assertNotNull(resultado);
            assertEquals("juan Carlos", resultado.getNombres());
            assertEquals("Pérez", resultado.getApellidos());
            assertEquals("12345678-9", resultado.getRut());
        }
}