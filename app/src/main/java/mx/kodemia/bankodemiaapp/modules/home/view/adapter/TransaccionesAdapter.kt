package mx.kodemia.bankodemiaapp.modules.home.view.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.databinding.ItemCardviewHomeBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeDetailsTransactionActivity
import mx.kodemia.bankodemiaapp.modules.home.view.fragments.InicioFragment

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

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: TransaccionHolder, position: Int) {
        val transaccion = transacciones.get(position)
        with(holder){
            cardView.setOnClickListener {
                val intent = Intent(activity,HomeDetailsTransactionActivity::class.java)
                shared = SharedPreferencesInstance.obtenerInstancia(activity)
                shared.guardarElementoListaTransacciones(transacciones,position)
                activity.startActivity(intent)
            }
            //Dar el concepto
            tv_concepto_movimiento.text = transaccion.concept

            //Dar formato de hora
            val hora = transaccion.created_at.substring(11,13).toInt()
            if(hora > 12){
                val reformato = transaccion.created_at.substring(11,13).toInt() - 12
                tv_hora_concepto.text = reformato.toString() +  transaccion.created_at.substring(13,16) + " p.m."
            }else{
                if(hora > 9){
                    tv_hora_concepto.text = transaccion.created_at.substring(11,16) + " a.m."
                }else{
                    tv_hora_concepto.text = transaccion.created_at.substring(12,16) + " a.m."
                }
            }

            //Verificar si es un Activo o un Pasivo
            if(transaccion.isIncome){
                tv_monto_movimiento.text = "+ $" + transaccion.amount.toString()
            }else{
                tv_monto_movimiento.text = "- $" + transaccion.amount.toString()
                tv_monto_movimiento.setTextColor(Color.RED)
            }

        }
    }

    override fun getItemCount(): Int = transacciones.size

    class TransaccionHolder(view: View): RecyclerView.ViewHolder(view){
        val cardView: MaterialCardView = view.findViewById(R.id.cardView_item_home)
        val tv_concepto_movimiento: TextView = view.findViewById(R.id.textView_concepto_movimiento)
        val tv_hora_concepto: TextView = view.findViewById(R.id.textView_hora_concepto)
        val tv_monto_movimiento: TextView = view.findViewById(R.id.textView_monto_movimiento)
    }

}