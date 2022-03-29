package mx.kodemia.bankodemiaapp.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object Alerts {

    fun showToast(
        mensaje: String,
        context: Context
    ){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    fun showSnackbar(
        snackStr: String,
        actionStrId: Int = 0,
        listener: View.OnClickListener? = null,
        activity: Activity
    ){
        val snackbar = Snackbar.make(activity.findViewById(android.R.id.content), snackStr,
            BaseTransientBottomBar.LENGTH_LONG
        )

        if(actionStrId != 0 && listener != null){
            snackbar.setAction(activity.getString(actionStrId), listener)
        }
        snackbar.show()
    }

}