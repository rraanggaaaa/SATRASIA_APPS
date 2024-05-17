package com.satrasia

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var button_forgotPassword : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        button_forgotPassword = findViewById(R.id.button_forgotPassword)
        button_forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()

        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        // Saat login berhasil
        val editor = sharedPreferences.edit()
        editor.putBoolean("IsLoggedIn", true)
        editor.apply()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@Login, Main::class.java)
                startActivity(intent)
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        findViewById<Button>(R.id.button_register).setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_next).setOnClickListener {
            val email = findViewById<EditText>(R.id.etEmail).text.toString().trim()
            val password = findViewById<EditText>(R.id.etPassword).text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.contains("@")) {
                Toast.makeText(this, "Alamat email tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && auth.currentUser?.isEmailVerified == true) {
                        // Email verified
                        Toast.makeText(this, "Berhasil Masuk", Toast.LENGTH_SHORT).show()
                        saveUserSession(email)
                        startActivity(Intent(this, Home::class.java))
                        finish()
                    } else if (task.isSuccessful) {
                        // Email not verified
                        Toast.makeText(this, "Silahkan verifikasi email anda terlebih dahulu.", Toast.LENGTH_LONG).show()
                    } else {
                        // Login failed
                        Toast.makeText(this, "Gagal Masuk, Email atau Password tidak sesuai}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun saveUserSession(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("UserEmail", email)
        editor.apply()
    }
}
