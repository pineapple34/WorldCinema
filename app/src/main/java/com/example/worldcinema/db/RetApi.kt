package com.example.worldcinema.db
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetApi {
    @POST("auth/login")
    fun login(@Body hashMap: HashMap<String, String>): Call<Login>

    @POST("auth/register")
    fun registration(@Body hashMap: HashMap<String, String>): Call<Void>

    @GET("movies?filter=new")
    fun getMovies(): Call<ArrayList<Movie>>
}