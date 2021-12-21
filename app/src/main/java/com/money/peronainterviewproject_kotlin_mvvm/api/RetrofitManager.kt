package com.money.peronainterviewproject_kotlin_mvvm.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {


    companion object{

        private const val BASE_URL = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/"

        private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        private val retrofit = retrofitBuilder.build()

        private val requestApi = retrofit.create(RequestApi::class.java)

        fun getRequestApi(): RequestApi = requestApi

    }

}