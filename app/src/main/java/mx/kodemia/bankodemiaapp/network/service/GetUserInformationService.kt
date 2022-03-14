package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.network.api.GetUserInformation
import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class GetUserInformationService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(GetUserInformation::class.java)

    suspend fun getUserFull(): Response<GetUserFullResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.getUserFullProfile()
            response
        }
    }

}