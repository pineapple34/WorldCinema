package com.example.worldcinema.db

data class Movie(val movieId: String,
                 val name: String,
                 val description: String,
                 val age: String,
                 val images: Array<String>,
                 val poster: String,
                 val tags: Array<Tag>)

data class Tag(val idTags: String, val tagName: String)
