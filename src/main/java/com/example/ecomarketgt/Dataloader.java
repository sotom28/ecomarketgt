package com.example.ecomarketgt;





import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ecomarketgt.Modelo.Perfil;
import com.example.ecomarketgt.Modelo.Usuarioo;
import com.example.ecomarketgt.Repository.PerfilRepository;
import com.example.ecomarketgt.Repository.UsuariooRepository;

import net.datafaker.Faker;

@Component
public class Dataloader implements CommandLineRunner {

    @Autowired
    private UsuariooRepository usuariooRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Buscar perfiles existentes
        Perfil adminPerfil = perfilRepository.findByDescripcion("Administrador");
        Perfil userPerfil = perfilRepository.findByDescripcion("USUARIO");

        // Si no existen, créalos
        if (adminPerfil == null) {
            adminPerfil = new Perfil(null, "Administrador", "FULL_ACCESS");
            adminPerfil = perfilRepository.save(adminPerfil);
        }
        if (userPerfil == null) {
            userPerfil = new Perfil(null, "USUARIO", "MEDIO_ACCESS");
            userPerfil = perfilRepository.save(userPerfil);
        }

        // Generar usuarios falsos
        List <Usuarioo> usuariosExistentes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Usuarioo usuario = new Usuarioo();
            usuario.setRut(faker.idNumber().valid());
            usuario.setNombres(faker.name().fullName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setTelefono(faker.phoneNumber().cellPhone());
            usuario.setDireccion(faker.address().fullAddress());
            usuario.setPassword(faker.internet().password());
            usuario.setPerfil(faker.bool().bool() ? adminPerfil : userPerfil);
            usuariosExistentes.add(usuario);

        }
        // Guardar los usuarios en la base de datos
        usuariooRepository.saveAll(usuariosExistentes);
        System.out.println("Datos de prueba cargados correctamente.");
    }
        

    }
    

    