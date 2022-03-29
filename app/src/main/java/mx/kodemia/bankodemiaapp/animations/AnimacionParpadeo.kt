package mx.kodemia.bankodemiaapp.animations

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.R

fun initParpadeoGuionLogo(context: Context, logo: ImageView) = runBlocking{
    val guionAnimacion = withContext(Dispatchers.Default){
        AnimationUtils.loadAnimation(context, R.anim.parpadeo_guion_logo)
    }
    logo.animation = guionAnimacion

    guionAnimacion.setAnimationListener(object: Animation.AnimationListener{
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {}

        override fun onAnimationRepeat(animation: Animation?) {}
    })
}