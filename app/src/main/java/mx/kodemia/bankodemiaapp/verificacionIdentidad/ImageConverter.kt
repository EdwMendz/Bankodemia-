package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

object ImageConverter {

    fun PathToBase64(foto: String): String{
        val imageFile = File(foto)
        var fis: FileInputStream? = null

        try {
            fis = FileInputStream(imageFile)
        }catch (e: FileNotFoundException){
            Log.e("CONVERSOR", e.printStackTrace().toString())
        }

        val bm: Bitmap = BitmapFactory.decodeStream(fis)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val b:ByteArray = baos.toByteArray()
        val encodeImage = Base64.encodeToString(b, Base64.DEFAULT)

        return encodeImage
    }

    fun Base64ToPath(foto: String): Bitmap {
        val imageBytes = Base64.decode(foto, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return decodedImage
    }

}