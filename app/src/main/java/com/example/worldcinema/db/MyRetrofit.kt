package com.example.worldcinema.db

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MyRetrofit {
    fun getRetrofit(): RetApi = Retrofit.Builder()
        .baseUrl("http://cinema.areas.su/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetApi::class.java)

    val imgUrl: String = "http://cinema.areas.su/up/images/"
}