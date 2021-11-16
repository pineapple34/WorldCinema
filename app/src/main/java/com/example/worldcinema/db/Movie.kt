package com.example.worldcinema.db

data class Movie(val movieId: Int,
                 val name: String,
                 val description: String,
                 val age: Int,
                 val images: List<String>,
                 val poster: String,
                 val tags: HashMap<String, String>)
