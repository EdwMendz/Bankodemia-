package mx.kodemia.bankodemiaapp.modules.transaction.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.response.maketransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.network.service.MakeTransactionService
import java.io.IOException

class EnviarDineroViewModel: ViewModel() {

    //Service
    private lateinit var serviceMakeTransaction: MakeTransactionService

    val makeTransactionResponse = MutableLiveData<MakeTransactionResponse>()
    val cargando = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun onCreate(context: Context){
        serviceMakeTransaction = MakeTransactionService(context)
    }

    fun makeTransaction(makeTransactionRequest: MakeTransactionRequest){
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceMakeTransaction.makeTransaction(makeTransactionRequest)
            try {
                when {
                    response.isSuccessful -> {
                        makeTransactionResponse.postValue(response.body())
                    }
                    response.code() == 400 -> {
                        error.postValue("Se mandaron datos incorrectos")
                    }
                    response.code() == 401 -> {
                        error.postValue("No se tiene permiso para hacer la transaccion")
                    }
                    response.code() == 402 -> {
                        error.postValue("Aun no hay dinero en esta cuenta")
                    }
                    response.code() == 412 -> {
                        error.postValue("Saldo insuficiente")
                    }
                    else -> {
                        error.postValue("Ha ocurrido un error por parte del servidor")
                    }
                }
                cargando.postValue(false)
            }catch (io: IOException){
                error.postValue("Error de excepcion")
                cargando.postValue(false)
            }
        }
    }

}