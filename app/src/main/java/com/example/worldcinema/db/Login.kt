package com.example.worldcinema.db

data class Login(val token: Int){
    companion object Login{
        var userToken: Int? = null
    }
}
