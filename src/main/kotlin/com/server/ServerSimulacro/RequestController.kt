package com.server.ServerSimulacro

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RequestController (private val mensajesRepository: MensajesRepository, private val usuariosRepository: UsuariosRepository) {

    @PostMapping("registrarUsuario")
    fun registrarUsuario(@RequestBody datos:Usuario):Any{
        val posibleUsuario = usuariosRepository.findById(datos.nombre)

        return if(posibleUsuario.isPresent){
            val usuario = posibleUsuario.get()
            if(usuario.pass == datos.pass){
                "Usuario ya registrado"
            }else {
                return Error(1, "Contraseña incorrecta")
            }
        }else{
            usuariosRepository.save(Usuario(datos.nombre,datos.pass))
            return "Usuario creado"
        }
    }

    @PostMapping("crearMensaje")
    fun crearMensaje(@RequestBody datos:Mensaje):Any{
        val posibleUsuario = usuariosRepository.findById(datos.usuario)
        return if(posibleUsuario.isPresent){
            mensajesRepository.save(Mensaje(null,datos.texto,0,datos.usuario))
            "Mensaje guardado"
        }else{
            Error(2, "Usuario inexistente")
        }

    }

    @GetMapping("verMensajes")
    fun verMensajes(@RequestBody datos:Usuario):Any{

        val posibleUsuario = usuariosRepository.findById(datos.nombre)

        return if(posibleUsuario.isPresent){
            val usuario = posibleUsuario.get()
            return if(usuario.pass == datos.pass){
                mensajesRepository.findAll()
            }else {
                Error(1, "Contraseña incorrecta")
            }
        }else{
            Error(2, "Usuario inexistente")
        }

    }

    @PostMapping("retuitearMensaje")
    fun retuitearMensaje(@PathVariable usuarioParametro:String,@PathVariable passParametro:String,@PathVariable id:Int){

        val usuario = usuariosRepository.getById(usuarioParametro)

        if(passParametro == usuario.pass){
            val mensaje = mensajesRepository.getById(id)
            mensajesRepository.save(Mensaje(null,mensaje.texto,0,usuario.nombre))
            mensajesRepository.save(Mensaje(mensaje.id,mensaje.texto,mensaje.retuit+1,mensaje.usuario))
        }
    }

    @PostMapping("borrarUsuario")
    fun borrarUsuario(@RequestBody datos:Usuario):Any{
        val posibleUsuario = usuariosRepository.findById(datos.nombre)
        return if(posibleUsuario.isPresent){
            val usuario = posibleUsuario.get()
            mensajesRepository.deleteAll(mensajesRepository.findAll().filter { it.usuario == datos.nombre })
            //usuariosRepository.delete(usuariosRepository.getById(datos.nombre))
            usuariosRepository.delete(datos)
            "Borrado correctamente"
        }else{
            Error(2, "Usuario inexistente")
        }
    }


}