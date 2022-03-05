package mx.kodemia.bankodemiaapp.modules.home.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.modules.home.view.HomeDetailsTransactionActivity
import mx.kodemia.bankodemiaapp.modules.home.view.fragments.InicioFragment

class TransaccionesAdapter(val activity: Activity, val transacciones: MutableList<Transaccion>): RecyclerView.Adapter<TransaccionesAdapter.TransaccionHolder>() {

    private val TAG = InicioFragment::class.java

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransaccionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_home,parent,false)
        return TransaccionHolder(view)
    }

    override fun onBindViewHolder(holder: TransaccionHolder, position: Int) {
        val transaccion = transacciones.get(position)
        with(holder){
            cardView.setOnClickListener {
                val intent = Intent(activity,HomeDetailsTransactionActivity::class.java)
                activity.startActivity(intent)
            }
            tv_concepto_movimiento.text = transaccion.concept
            tv_hora_concepto.text = transaccion.created_at.substring(11,18)
            tv_monto_movimiento.text = transaccion.amount.toString()
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