package com.example.ecomarketgt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import org.springframework.test.web.servlet.MockMvc;


import com.example.ecomarketgt.Modelo.Usuarioo;

import com.example.ecomarketgt.Service.UsuariooService;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(UsuariooController.class)

public class UsuariooControllerTest {

        @MockBean
        private UsuariooService usuarioService; // Mock del servicio UsuarioService para pruebas unitarias

        
        

        @Autowired
        private MockMvc mockMvc; // MockMvc para simular peticiones HTTP al controlador


        private Usuarioo usuario; // Instancia de Usuarioo para pruebas
        private ObjectMapper objectMapper = new ObjectMapper(); // Instancia de ObjectMapper para convertir objetos a JSON



        // Configuración inicial de las pruebas

        @BeforeEach
        void setUp() {
            // Si necesitas limpiar la base de datos, debes mockear o inyectar el repositorio real.
            // Aquí se elimina el uso de usuarioRepository para evitar el error de compilación.
            usuario = new Usuarioo();
            usuario.setId(1L);
            usuario.setRut("12345678-9");
            usuario.setNombres("Juan");
            // No se guarda el usuario porque el repositorio no está disponible en este contexto de prueba.
        }

        @Test
        public void testGuardarUsuario() throws Exception{
            when (usuarioService.Guardar(any(Usuarioo.class))).thenReturn(usuario);
            
            mockMvc.perform(post("/api/usuario/guardar")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated());
                
                

        }

            @Test
            public void testGuardarUsuarioInvalido() throws Exception {
                Usuarioo usuarioInvalido = new Usuarioo();
                usuarioInvalido.setId(2L);
                usuarioInvalido.setRut(""); // Rut vacío, inválido
                usuarioInvalido.setNombres(""); // Nombre vacío, inválido

                mockMvc.perform(post("/api/usuario/guardar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuarioInvalido)))
                        .andExpect(status().isBadRequest());

    }

            @Test // Test para obtener todos los usuarios
            public void testObtenerUsuarioPorId() throws Exception {
                when(usuarioService.findById(1L)).thenReturn(usuario);

                mockMvc.perform(get("/api/usuario/1"))
                        .andExpect(status().isOk());
            }
        }