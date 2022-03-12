package com.server.ServerSimulacro

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Mensaje(@Id
                   @GeneratedValue
                   val id:Int? = 0,
                   val texto:String,
                   var retuit:Int,
                   val usuario:String) {



    override fun toString():String{
        val gson = Gson()
        return gson.toJson(this)
    }
}
