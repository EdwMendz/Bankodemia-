package mx.kodemia.bankodemiaapp.modules.identity_verification.encoder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

object ImageConverter {

    fun pathToBase64(foto: String): String {
        val imageFile = File(foto)
        var fis: FileInputStream? = null

        try {
            fis = FileInputStream(imageFile)
        } catch (e: FileNotFoundException) {
            Log.e("NOCONVERSOR", e.printStackTrace().toString())
        }

        val bm: Bitmap = BitmapFactory.decodeStream(fis)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()

        return Base64.encodeToString(b, 0, 64, Base64.NO_WRAP)
    }

}