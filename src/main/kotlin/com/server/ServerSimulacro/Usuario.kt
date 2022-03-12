package com.server.ServerSimulacro

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Usuario(@Id val nombre:String,val pass:String) {
    override fun toString():String{
        val gson = Gson()
        return gson.toJson(this)
    }
}
