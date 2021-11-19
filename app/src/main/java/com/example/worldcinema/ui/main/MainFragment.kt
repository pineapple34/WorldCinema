package com.example.worldcinema.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldcinema.R
import com.example.worldcinema.db.Login
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

        val movie_rec: RecyclerView = root.findViewById(R.id.movie_rec)
        val retrofit = MyRetrofit().getRetrofit().create(RetApi::class.java)
        val call: Call<ArrayList<Movie>> = retrofit.getMovies()
        call.enqueue(object: retrofit2.Callback<ArrayList<Movie>>{
            override fun onResponse(call: Call<ArrayList<Movie>>, response: Response<ArrayList<Movie>>) {
                movie_rec.adapter = response.body()?.let { MovieAdapter(requireContext(), it) }
            }

            override fun onFailure(call: Call<ArrayList<Movie>>, t: Throwable) {
                AlertDialog.Builder(this@MainFragment.context).setMessage(t.message).show()
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}