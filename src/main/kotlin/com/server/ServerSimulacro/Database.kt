package com.server.ServerSimulacro

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Database {

    @Bean
    fun initDatabase(usuariosRepository:UsuariosRepository,mensajesRepository:MensajesRepository):CommandLineRunner{
        return CommandLineRunner{

        }
    }

}