package com.satrasia

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth

class Welcome : ComponentActivity() {

    lateinit var logo: ImageView
    lateinit var logo2: ImageView
    lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("IsLoggedIn", false)

        if (isLoggedIn && FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, Home::class.java))
        } else {
            startActivity(Intent(this, Main::class.java))
        }
        finish()

// Additional logic can be placed here if there are more conditions or default actions

        setContentView(R.layout.activity_welcome)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        logo = findViewById(R.id.logo)
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_logo)
        logo.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 500, 500, false))

        logo2 = findViewById(R.id.logo2)
        val bitmap2: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_logo2)
        logo2.setImageBitmap(Bitmap.createScaledBitmap(bitmap2, 1000, 500, false))

        buttonNext = findViewById(R.id.button_next)

        buttonNext.setOnClickListener {
            val intentNext = Intent(this, Main::class.java) // Asumsikan ini adalah LoginActivity atau MainActivity
            startActivity(intentNext)
        }
    }
}
