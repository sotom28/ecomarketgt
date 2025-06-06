package com.example.ecomarketgt;





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

    Perfil adminPerfil = null;
    Perfil userPerfil = null;
    

    @Autowired
    private PerfilRepository PerfilRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Initialize perfiles after PerfilRepository is injected
        Perfil adminPerfil = PerfilRepository.findByDescripcion("Administrador");
        Perfil userPerfil = PerfilRepository.findByDescripcion("USUARIO");

        /// GENERAR USUARIOS
        for (int i = 0; i < 300; i++) {
            Usuarioo usuario = new Usuarioo();
            
            usuario.setRut(faker.idNumber().valid());
            usuario.setNombres(faker.name().fullName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setTelefono(faker.phoneNumber().cellPhone());
            usuario.setDireccion(faker.address().fullAddress());
            usuario.setPassword(faker.internet().password());
            Perfil perfil = faker.bool().bool() ? adminPerfil : userPerfil;
            usuario.setPerfil(perfil);
            usuariooRepository.save(usuario);

        }
    }
}