package mx.kodemia.bankodemiaapp.formatos

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import mx.kodemia.bankodemiaapp.R

fun darFormatoColorAlternado(context: Context, cardView: MaterialCardView, posicion: Int){
    if((posicion+1)%2 == 0){
        cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.gris_recycler))
    }
}