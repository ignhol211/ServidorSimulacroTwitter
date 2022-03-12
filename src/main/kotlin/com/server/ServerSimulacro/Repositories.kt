package com.server.ServerSimulacro

import org.springframework.data.jpa.repository.JpaRepository

interface MensajesRepository : JpaRepository<Mensaje, Int>
interface UsuariosRepository : JpaRepository<Usuario,String> {

}