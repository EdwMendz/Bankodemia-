package mx.kodemia.bankodemiaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.data.model.request.enummodels.TransactionType
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityPruebasBinding

class PruebasActivity : AppCompatActivity() {

    lateinit var binding : ActivityPruebasBinding
    val viewModel: PruebasActivityViewModel by viewModels()
    val TAG = "LOGIN"

    lateinit var shared : SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        binding.apply {

            btnTestSigup.setOnClickListener {
                val signUp = SignUpResquest(
                    "federico123@kodemia.com",
                    "Federico",
                    "Gonzales",
                    "Terrorist",
                    "1987-09-27T01:20:06.037Z",
                    "FedericoGonza123",
                    "+523315248658",
                    "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
                    "INE"
                )
                mandarDatosSignUp(signUp)
            }

            btnTestLogin.setOnClickListener {
                val logIn = LogInRequest(
                    "federico123@kodemia.com",
                    "FedericoGonza123"
                )
                mandarDatosLogIn("1h",logIn)
            }

            btnTestMakeTrans.setOnClickListener {
                val makeTransaction = MakeTransactionRequest(
                    10000,
                    TransactionType.DEPOSIT.toString(), //Si se quiere hacer un deposito a otra cuenta se pone PAYMENT
                    null, // Si es deposito no se pone Usuario de Destino
                    "De mi para mi x3 xD"
                )
                mandarDatosMakeTransaction(makeTransaction)
            }

            btnTestListTrans.setOnClickListener {
                traerDatosListTransacciones()
            }

        }

        observers()
    }

    private fun init(){
        //Shared
        shared = SharedPreferencesInstance.obtenerInstancia(this)

        //binding
        binding = ActivityPruebasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.onCreate(context = this)
    }

    private fun observers(){
        viewModel.signUpResponse.observe(this){signUp: SignUpResponse ->
            shared.guardarRespuestaSigUp(signUp)
            lifecycleScope.launch {
                signUp.success.let {  }
            }

        }

        viewModel.logInResponse.observe(this) {logIn: LoginResponse ->
            shared.guardarSesionLogin(logIn)
            lifecycleScope.launch {
                logIn.apply {
                    Log.e(TAG,this.token.toString())
                    Log.e(TAG,this.expiresIn.toString())
                }
            }

        }

        viewModel.makeTransactionResponse.observe(this){ transaction: MakeTransactionResponse ->
            shared.guardarConceptoDeTransaccion(transaction)
            lifecycleScope.launch {
                transaction.apply {
                    Log.e(TAG,this.data.transaction.concept)
                    Log.e(TAG,this.success.toString())
                }
            }
        }

        viewModel.listTransactionResponse.observe(this){ listTransaccion: ListaTransaccionesResponse ->
            lifecycleScope.launch {
                listTransaccion.apply {
                    Log.e(TAG,this.data.transactions[5].concept)
                    Log.e(TAG,this.success.toString())
                }
            }
        }
    }

    private fun mandarDatosSignUp(signUpResquest: SignUpResquest) {
        viewModel.signUp(signUpResquest)
    }

    private fun mandarDatosLogIn(expires_in: String, logInRequest: LogInRequest) {
        viewModel.logIn(expires_in,logInRequest)
    }

    private fun mandarDatosMakeTransaction(makeTransactionRequest: MakeTransactionRequest){
        viewModel.makeTransaction(makeTransactionRequest)
    }

    private fun traerDatosListTransacciones(){
        viewModel.listTransacciones()
    }

}