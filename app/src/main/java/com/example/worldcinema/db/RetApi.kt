package com.example.worldcinema.db
import retrofit2.Call
import retrofit2.http.*

interface RetApi {
    @POST("auth/login")
    fun login(@Body hashMap: HashMap<String, String>): Call<Login>

    @POST("auth/register")
    fun registration(@Body hashMap: HashMap<String, String>): Call<Void>

    @GET("movies?filter=new")
    fun getMovies(): Call<ArrayList<Movie>>

    @GET("user")
    fun getUser(@Header("Authorization") token: Int): Call<ArrayList<User>>
}