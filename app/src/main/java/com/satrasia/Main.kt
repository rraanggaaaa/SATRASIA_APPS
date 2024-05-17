package com.satrasia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class Main : AppCompatActivity() {

    lateinit var buttonLogin: Button
    lateinit var buttonRegister: Button
    lateinit var logoAnimated: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        logoAnimated = findViewById(R.id.logo_animated)
        Glide.with(this /* context */)
            .asGif()
            .load(R.drawable.img_logo_animated)
            .into(findViewById(R.id.logo_animated))

        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener{
            val intentLogin = Intent(this, Login::class.java)
            startActivity(intentLogin)
        }

        buttonRegister = findViewById(R.id.button_register)

        buttonRegister.setOnClickListener{
            val intentRegister = Intent(this, Register::class.java)
            startActivity(intentRegister)
        }
    }
}