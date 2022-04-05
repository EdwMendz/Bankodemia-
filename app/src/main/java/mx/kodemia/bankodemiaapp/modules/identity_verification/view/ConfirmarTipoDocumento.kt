package mx.kodemia.bankodemiaapp.modules.identity_verification.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_confirmacion_tipo_documento.*
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.ActivityConfirmacionTipoDocumentoBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ConfirmarTipoDocumento : AppCompatActivity() {

    lateinit var  binding: ActivityConfirmacionTipoDocumentoBinding
    private var archivoFoto: File? = null
    private lateinit var absolutePathImagen: String

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                val imagen = BitmapFactory.decodeFile(absolutePathImagen)
                binding.apply {
                    ivFoto.setImageBitmap(imagen)
                    ivFoto.visibility = View.VISIBLE
                    textViewTomarFoto.visibility = View.GONE
                    btnSubirFoto.isEnabled = true
                }
                archivoFoto?.also {
                    //Conversion de Archivo de Foto a Base64
                    val shared = SharedPreferencesInstance.obtenerInstancia(this)
                    shared.guardarFotoArchivo(it.toString())
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuracionInicial()

        btnSubirFoto.setOnClickListener {

            startActivity(Intent(this, Contrasena::class.java))
        }
    }


    private fun configuracionInicial() {
        binding = ActivityConfirmacionTipoDocumentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shared = SharedPreferencesInstance.obtenerInstancia(this)
        binding.apply {
            tvTipoDocumento.text = shared.obtenerTipoDocumento()
            btnSubirFoto.isEnabled = false
            textViewTomarFoto.setOnClickListener {
                checarPermisos()
            }
        }

    }

    private fun checarPermisos() {
        val requestCodeCamera = 100
        val permisoCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permisoCamara == PackageManager.PERMISSION_GRANTED) {

            lanzarCamara()

        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                requestCodeCamera
            )

        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun lanzarCamara() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { camara ->
            camara.resolveActivity(packageManager)?.also {
                archivoFoto = try {

                    crearImagen()

                } catch (ex: IOException) {
                    null
                }

                archivoFoto?.also { archivo ->
                    val photoPATH: Uri = FileProvider.getUriForFile(
                        this,
                        "mx.kodemia.bankodemiaapp.fileprovider",
                        archivo
                    )
                    camara.putExtra(MediaStore.EXTRA_OUTPUT, photoPATH)
                    camara.putExtra("REQUEST_TAKE_PHOTO", 100)


                    startForResult.launch(camara)
                }
            }

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun crearImagen(): File? {

        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val directorioAlmacenamiento: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(

            "JPEG_${timeStamp}",
            ".jpeg",
            directorioAlmacenamiento

        ).apply {
            absolutePathImagen = absolutePath
        }
    }
}