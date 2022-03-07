package mx.kodemia.bankodemiaapp.core

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.TextView
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transacciones
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse

object SharedPreferencesInstance {

    //Se crea la instancia
    val sharedPref = SharedPreferencesInstance

    //Se crea la variable para obtener las preferencias
    lateinit var sharedPreferences: SharedPreferences

    //Se crea el editor
    lateinit var editor: SharedPreferences.Editor

    //Obtenemos la instancia de nuestro objeto
    fun obtenerInstancia(context: Context): SharedPreferencesInstance{
        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return sharedPref
    }

    //Limpiar los registros de SharedPreferences
    fun limpiarPreferencias(){
        editor.clear()
        editor.apply()
    }

    //Se guarda el token y el tiempo en el que expira el token con ayuda del editor
    fun guardarSesionLogin(sesion: LoginResponse){
        with(editor){
            putString("token",sesion.token)
            putString("expira",sesion.expiresIn)
            apply()
        }
    }

    //Se guarda la respuesta "Boolean" para saber si se realizo el registro
    fun guardarRespuestaSigUp(sesion: SignUpResponse){
        with(editor){
            putString("successSignUp",sesion.success.toString())
            Log.e("successSignUp",sesion.success.toString())
            apply()
        }
    }

    //Se guarda el estatus de la transaccion
    fun guardarConceptoDeTransaccion(transaccion: MakeTransactionResponse){
        with(editor){
            putString("concepto",transaccion.data.transaction.concept)
            putString("exitoso",transaccion.success.toString())
            apply()
        }
    }

    // Se guarda el elemento individual de una transaccion
    fun guardarElementoListaTransacciones(transacciones: MutableList<Transaccion>, posicion: Int){
        with(editor){
            putString("fecha",transacciones[posicion].created_at)
            putString("amount",transacciones[posicion].amount.toString())
            putString("concept",transacciones[posicion].concept)
            putString("id_transaccion",transacciones[posicion]._id)
            putString("cuenta_emisora",transacciones[posicion].issuer._id)
            apply()
        }
    }

    // Se llenan los textView de la Activity "HomeDetailsTransactionActivity"
    fun obtenerElementoListaTransacciones(
        tv_monto: TextView,
        tv_concepto: TextView,
        tv_concepto2: TextView,
        tv_fecha: TextView,
        tv_cuenta_emisora: TextView,
        tv_id_transaccion: TextView
    ){

        tv_monto.text = sharedPreferences.getString("amount",null)
        tv_concepto.text = sharedPreferences.getString("concept",null)
        tv_concepto2.text = sharedPreferences.getString("concept",null)
        tv_fecha.text = sharedPreferences.getString("fecha",null)
        tv_cuenta_emisora.text = sharedPreferences.getString("cuenta_emisora",null)
        tv_id_transaccion.text = sharedPreferences.getString("id_transaccion",null)
    }

    //Se obtiene el token para utilizarlo en otras peticiones
    fun obtenerSesion(): LoginResponse{
        return LoginResponse(
            sharedPreferences.getString("token",null),
            sharedPreferences.getString("expira",null)
        )
    }

}