package com.example.worldcinema

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.worldcinema.db.Login
import com.example.worldcinema.db.MyRetrofit
import com.example.worldcinema.db.RetApi
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var soname: EditText
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var pass2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        name = findViewById(R.id.text_name)
        soname = findViewById(R.id.text_soname)
        email = findViewById(R.id.text_email)
        pass = findViewById(R.id.text_pass)
        pass2 = findViewById(R.id.text_pass2)
    }

    fun reg(view: View){
        if (email.text.isNotEmpty() && name.text.isNotEmpty() && soname.text.isNotEmpty() && pass.text.isNotEmpty() && pass2.text.isNotEmpty()){
            if (pass.text.toString() == pass2.text.toString()){
                val retrofit = MyRetrofit().getRetrofit().create(RetApi::class.java)
                val hashMap: HashMap<String, String> = HashMap<String, String>()
                hashMap["email"] = email.text.toString()
                hashMap["password"] = pass.text.toString()
                hashMap["firstName"] = name.text.toString()
                hashMap["lastName"] = soname.text.toString()
                val call: Call<Void> = retrofit.registration(hashMap)
                call.enqueue(object: retrofit2.Callback<Void>{
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(this@SignUpActivity, "Успешная регистрация", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        val toast = Toast.makeText(this@SignUpActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else{
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Пароли не совпадают")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
            }

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

    fun cancel(view: android.view.View) { finish() }
}