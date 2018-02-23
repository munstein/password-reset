package com.munstein.passwordreset.api


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by @Munstein on 23/02/2018.
 */
class RetrofitBuilder {

    companion object {

        val url = "https://us-central1-last-minute-app-71f82.cloudfunctions.net"

        fun getRetrofit() : Retrofit {

            return Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

        }
    }

}