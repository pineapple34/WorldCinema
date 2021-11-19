package com.example.worldcinema

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.worldcinema.db.Login
import com.example.worldcinema.db.MyRetrofit
import com.example.worldcinema.db.RetApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import android.util.Patterns

class SignInActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        email = findViewById(R.id.text_email)
        password = findViewById(R.id.text_pass)
    }

    fun Reg(view: android.view.View) {
        startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
    }

    fun Login(view: android.view.View) {
        if (email.text.isNotEmpty() && password.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
            val retrofit = MyRetrofit().getRetrofit().create(RetApi::class.java)
            val hashMap: HashMap<String, String> = HashMap<String, String>()
            hashMap["email"] = email.text.toString()
            hashMap["password"] = password.text.toString()
            val call: Call<Login> = retrofit.login(hashMap)
            call.enqueue(object: retrofit2.Callback<Login>{
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if (response.body()?.token != null){
                        Login.userToken = response.body()?.token
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                        finish()
                    }
                    else AlertDialog.Builder(this@SignInActivity).setMessage("Неверные данные входа").show()
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    val toast = Toast.makeText(this@SignInActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        else{
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Заполните все поля")
                .setPositiveButton("OK", null)
                .create()
                .show()
        }
    }
}