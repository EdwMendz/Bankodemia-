package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.User
import mx.kodemia.bankodemiaapp.databinding.ActivityCrearCuentaBinding
import mx.kodemia.bankodemiaapp.formatos.darFormatoHoraMinutos
import mx.kodemia.bankodemiaapp.modules.home.view.HomeDetailsTransactionActivity
import mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel.CrearCuentaViewModel


class CrearCuentaView : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityCrearCuentaBinding
    //SharedPreferences
    private lateinit var sharedPreferences: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializa el binding
        inicializarBinding()
        //vamos a hacer validacion de correo y si pasa lanzar continuar
        lanzarActivities()
    }
//
//
//
//
//    //SharedPreferences
//    lateinit var shared : SharedPreferencesInstance
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): TransaccionHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_home,parent,false)
//        return TransaccionHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: TransaccionHolder, position: Int) {
//        //Se declara una variable para obtener los datos de un solo elemento y trabajar sobre él
//        val transaccion = transacciones.get(position)
//
//        //Acciones a realizar con el Holder, es decir, cambios a realizar en las vistas del Item del CardView
//        with(holder){
//
//            binding.apply {
//
//                /*
//                Al presionar un elemento del RecyclerView nos mandara al activity de Detalles de Transaccion,
//                ademas se guardan los datos actuales con SharedPreferences para mostrarlos en el activity
//                sin necesidad de usar MVVM ni tener que volver a hacer de nuevo la peticion
//                 */
//                cardViewItemHome.setOnClickListener {
//                    //Se guardan los datos del elemento seleccionado
//                    shared = SharedPreferencesInstance.obtenerInstancia(activity)
//                    shared.guardarElementoListaTransacciones(transacciones,position)
//
//                    //Se lanza el activity de Detalles de Transaccion
//                    val intent = Intent(activity, HomeDetailsTransactionActivity::class.java)
//                    activity.startActivity(intent)
//                }
//
//                //Dar el concepto
//                textViewConceptoMovimiento.text = transaccion.concept
//
//                //Dar formato de hora
//                textViewHoraConcepto.text = darFormatoHoraMinutos(transaccion.created_at)
//
//                //Verificar si es un Activo o un Pasivo y dar el formato correspondiente
//                textViewMontoMovimiento.text = darFormatoActivoOPasivo(transaccion.isIncome,transaccion.amount,textViewMontoMovimiento)
//
//                //Dar color de fondo alternado
//                darFormatoColorAlternado(activity,cardViewItemHome,position)
//            }
//        }
//    }
//
//    //El metodo getItemCount solamente determina el tamaño de la coleccion de datos que recibe el adaptador
//    override fun getItemCount(): Int = transacciones.size
//
//    /*
//    La Clase Holder trae todas las referencias de cada una de las vistas de un Activity o Fragment.
//    De esta forma pueden ser usadas en el adapter para cambiar sus estilos o ingresarles informacion
//     */
//    class TransaccionHolder(view: View): RecyclerView.ViewHolder(view){
//        val binding = ItemCardviewHomeBinding.bind(view)
//    }

    //mandamos las activities
    private fun lanzarActivities() {
        binding.apply {
            btnCrearCuentaContinuar.setOnClickListener {
                if (validarCorreo()) {
                    binding.apply {
                        val usuario: User
                        //guardarCoreoShared(usuario)

                        lanzarDatos()
                    }
                }
            }
        }
    }
    private fun guardarCoreoShared(user:User){
        sharedPreferences = SharedPreferencesInstance.obtenerInstancia(this)
        sharedPreferences.registroUsuarioemail(user)
    }



    private fun lanzarDatos() {
        val intent = Intent(this@CrearCuentaView, DatosView::class.java)
        startActivity(intent)
    }

    //Validar Correo
    private fun validarCorreo(): Boolean {
        binding.apply {

            return if (tietCrearcuentaCorreo.text.toString().isEmpty()) {
                tilCrearcuentaCorreo.error = getString(R.string.campo_vacio)
                false
            } else {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(tietCrearcuentaCorreo.text.toString())
                        .matches()
                ) {
                    tilCrearcuentaCorreo.isErrorEnabled = false
                    true
                } else {
                    tilCrearcuentaCorreo.error = getString(R.string.correo_no_valido)
                    false
                }
            }
        }
    }


    //   Infla el view Binding
    private fun inicializarBinding() {
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}