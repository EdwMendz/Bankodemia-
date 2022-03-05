package mx.kodemia.bankodemiaapp.core

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse

object SharedPreferencesInstance {

    val sharedPref = SharedPreferencesInstance
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    fun obtenerInstancia(context: Context): SharedPreferencesInstance{
        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return sharedPref
    }

    fun limpiarPreferencias(){
        editor.clear()
        editor.apply()
    }

    fun guardarSesionLogin(sesion: LoginResponse){
        with(editor){
            putString("token",sesion.token)
            putString("expira",sesion.expiresIn)
            apply()
        }
    }

    fun guardarSesionSigUp(sesion: SignUpResponse){
        with(editor){
            putString("token",sesion.success.toString())
            Log.e("SIGNUP",sesion.success.toString())
            apply()
        }
    }

    fun obtenerSesion(): LoginResponse{
        return LoginResponse(
            sharedPreferences.getString("token",null),
            sharedPreferences.getString("expira",null)
        )
    }

}