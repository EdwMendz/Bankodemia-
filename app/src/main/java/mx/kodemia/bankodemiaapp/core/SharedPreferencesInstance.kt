package mx.kodemia.bankodemiaapp.core

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transacciones
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
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

    fun guardarConceptoDeTransaccion(transaccion: MakeTransactionResponse){
        with(editor){
            putString("concepto",transaccion.data.transaction.concept)
            putString("exitoso",transaccion.success.toString())
            apply()
        }
    }

    fun guardarListaTransacciones(listTransacciones: ListaTransaccionesResponse){
        with(editor){
            putString("hora1",listTransacciones.data.transactions[0].created_at)
            putString("amount1",listTransacciones.data.transactions[0].amount.toString())
            putString("concept1",listTransacciones.data.transactions[0].concept)
            apply()
        }
    }

    fun guardarElementoListaTransacciones(transacciones: ListaTransaccionesResponse, posicion: Int){
        with(editor){
            putString("hora",transacciones.data.transactions[posicion].created_at)
            putString("amount",transacciones.data.transactions[posicion].amount.toString())
            putString("concept",transacciones.data.transactions[posicion].concept)
            apply()
        }
    }

    /*
    fun obtenerElementoListaTransacciones(): Transaccion {
        return Transaccion(
            sharedPreferences.getString("hora",null),
            sharedPreferences.getString("amount",null),

        )
    }*/


    fun obtenerSesion(): LoginResponse{
        return LoginResponse(
            sharedPreferences.getString("token",null),
            sharedPreferences.getString("expira",null)
        )
    }

}