package com.github.marcelooo616;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Development
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            // Lógica a ser executada quando o aplicativo for iniciado
            System.out.println("Aplicativo iniciado com sucesso!");
            // Outras ações...
        };
    }

}