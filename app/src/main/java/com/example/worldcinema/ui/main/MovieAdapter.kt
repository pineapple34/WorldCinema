package com.example.worldcinema.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldcinema.R
import com.example.worldcinema.db.Movie
import com.example.worldcinema.db.MyRetrofit

class MovieAdapter(val context: Context, val movies: ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.VH>() {
    class VH(ListOfView: View): RecyclerView.ViewHolder(ListOfView){
        val text: TextView = ListOfView.findViewById(R.id.text_main)
        val image: ImageView = ListOfView.findViewById(R.id.image_main)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val root = LayoutInflater.from(context).inflate(R.layout.movie_adapter, parent, false)
        return VH(root)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load(MyRetrofit.imgUrl + movies[position].poster).into(holder.image)
        holder.text.text = movies[position].name
    }

    override fun getItemCount(): Int {
        return movies.count()
    }
}