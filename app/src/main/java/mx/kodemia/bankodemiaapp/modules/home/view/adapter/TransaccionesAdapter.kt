package mx.kodemia.bankodemiaapp.modules.home.view.adapter
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.formatos.darFormatoActivoOPasivo
import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.Transaccion
import mx.kodemia.bankodemiaapp.databinding.ItemCardviewHomeBinding
import mx.kodemia.bankodemiaapp.formatos.darFormatoHoraMinutos
import mx.kodemia.bankodemiaapp.modules.home.view.HomeDetailsTransactionActivity

class TransaccionesAdapter(val activity: Activity, val transacciones: MutableList<Transaccion>): RecyclerView.Adapter<TransaccionesAdapter.TransaccionHolder>() {

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransaccionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_home,parent,false)
        return TransaccionHolder(view)
    }

    override fun onBindViewHolder(holder: TransaccionHolder, position: Int) {

        //Invertir la posicion de la lista para ver las transacciones mas actuales
        val posiccionInversa = (transacciones.size-1)-position

        //Se declara una variable para obtener los datos de un solo elemento y trabajar sobre él
        val transaccion = transacciones.get(posiccionInversa)

        //Acciones a realizar con el Holder, es decir, cambios a realizar en las vistas del Item del CardView
        with(holder){

            binding.apply {

                /*
                Al presionar un elemento del RecyclerView nos mandara al activity de Detalles de Transaccion,
                ademas se guardan los datos actuales con SharedPreferences para mostrarlos en el activity
                sin necesidad de usar MVVM ni tener que volver a hacer de nuevo la peticion
                 */
                cardViewItemHome.setOnClickListener {
                    //Se guardan los datos del elemento seleccionado
                    shared = SharedPreferencesInstance.obtenerInstancia(activity)
                    shared.guardarElementoListaTransacciones(transacciones,posiccionInversa)

                    //Se lanza el activity de Detalles de Transaccion
                    val intent = Intent(activity,HomeDetailsTransactionActivity::class.java)
                    activity.startActivity(intent)
                }

                //Dar el concepto
                textViewConceptoMovimiento.text = transaccion.concept

                //Dar formato de hora
                textViewHoraConcepto.text = darFormatoHoraMinutos(transaccion.created_at)

                //Verificar si es un Activo o un Pasivo y dar el formato correspondiente
                textViewMontoMovimiento.text = darFormatoActivoOPasivo(transaccion.isIncome,transaccion.amount)
            }
        }
    }

    //El metodo getItemCount solamente determina el tamaño de la coleccion de datos que recibe el adaptador
    override fun getItemCount(): Int = transacciones.size

    /*
    La Clase Holder trae todas las referencias de cada una de las vistas de un Activity o Fragment.
    De esta forma pueden ser usadas en el adapter para cambiar sus estilos o ingresarles informacion
     */
    class TransaccionHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCardviewHomeBinding.bind(view)
    }

}