package com.example.worldcinema.ui.profile

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.worldcinema.R
import com.example.worldcinema.db.Login
import com.example.worldcinema.db.MyRetrofit
import com.example.worldcinema.db.RetApi
import com.example.worldcinema.db.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val retrofit = MyRetrofit().getRetrofit().create(RetApi::class.java)
        val call: Call<ArrayList<User>> = retrofit.getUser(Login.userToken!!)
        call.enqueue(object: Callback<ArrayList<User>>{
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                text_name.text = "${response.body()!![0].firstName} ${response.body()!![0].lastName}"
                Glide.with(this@ProfileFragment).load(MyRetrofit.imgUrl + response.body()!![0].avatar).into(img_avatar)
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                AlertDialog.Builder(this@ProfileFragment.context).setMessage(t.message).show()
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}