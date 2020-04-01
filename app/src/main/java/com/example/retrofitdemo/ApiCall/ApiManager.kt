package com.example.retrofitdemo.ApiCall

import com.example.retrofitdemo.Constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {

    companion object {
        private var service: ApiClient? = null
        fun getServices(): ApiClient? {
            if (service == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit.create(ApiClient::class.java)
            }
            return service
        }

    }


}