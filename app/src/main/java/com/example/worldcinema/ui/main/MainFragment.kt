package com.example.worldcinema.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.worldcinema.R
import com.example.worldcinema.databinding.FragmentMainBinding
import com.example.worldcinema.db.Movie
import com.example.worldcinema.db.MyRetrofit
import com.example.worldcinema.db.RetApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val text: TextView = root.findViewById(R.id.text_main)
        val image: ImageView = root.findViewById(R.id.image_main)

        val retrofit = MyRetrofit().getRetrofit().create(RetApi::class.java)
        val call: Call<Movie> = retrofit.getMovies()
        call.enqueue(object: retrofit2.Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                text.setText(response.body()?.name)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {

            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}