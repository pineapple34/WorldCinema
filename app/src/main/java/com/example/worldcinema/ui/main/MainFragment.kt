package com.example.worldcinema.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.worldcinema.R
import com.example.worldcinema.db.Movie
import com.example.worldcinema.db.MyRetrofit
import retrofit2.Call
import retrofit2.Response

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val movie_rec: ViewPager2 = root.findViewById(R.id.movie_pager)
        val retrofit = MyRetrofit.getRetrofit()
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