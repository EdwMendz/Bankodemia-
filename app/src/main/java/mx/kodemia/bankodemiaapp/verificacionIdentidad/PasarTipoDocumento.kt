package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import mx.kodemia.bankodemiaapp.verificacionIdentidad.confirmacionDocumento.ConfirmarTipoDocumento

//Funcion para pasar datos de un Activity a Otro (En este caso para pasar el tipo de documento seleccionado)
fun pasarDatos(activity: Activity,tipoDocumento: String){

    //Declaramos una variable y le asignamos las propiedades de la clase Bundle()
    val bundle = Bundle()

    //Preparamos la variable que lleva el intent para lanzar el Activity que recibira los datos
    val lanzarActivity = Intent(activity, ConfirmarTipoDocumento::class.java)

    //Guardamos los datos en el Bundle
    bundle.putString("tipoDocumento",tipoDocumento)

    //Desde el intent de nuestra Activity receptora le pasamos el dato
    lanzarActivity.putExtras(bundle)

    //Lanzamos el Activity receptora con sus datos cargados
    activity.startActivity(lanzarActivity)
}