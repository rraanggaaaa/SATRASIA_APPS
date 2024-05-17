package com.satrasia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class Register : ComponentActivity() {

    private lateinit var buttonLogin : Button
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()  // Firestore instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        window.statusBarColor = ContextCompat.getColor(this, R.color.fifth)
        auth = FirebaseAuth.getInstance()

        buttonLogin = findViewById(R.id.btnLogin)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val buttonRegister = findViewById<Button>(R.id.btnRegister)
        buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = findViewById<EditText>(R.id.etName).text.toString().trim()
        val email = findViewById<EditText>(R.id.etEmail).text.toString().trim()
        val password = findViewById<EditText>(R.id.etPassword).text.toString().trim()
        val confirmPassword = findViewById<EditText>(R.id.etConfirmPassword).text.toString().trim()
        val phoneNumber = findViewById<EditText>(R.id.etPhoneNumber).text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        if (!name.matches(Regex("^[a-zA-Z\\s]+$"))) {
            Toast.makeText(this, "Nama hanya boleh mengandung huruf dan spasi!", Toast.LENGTH_SHORT).show()
            return
        }

        if (!email.contains("@")) {
            Toast.makeText(this, "Email tidak valid!", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Password dan Konfirmasi Password harus sama", Toast.LENGTH_SHORT).show()
            return
        }

        if (!phoneNumber.matches(Regex("^\\+62[0-9]+$"))) {
            Toast.makeText(this, "Nomor telepon harus berupa angka dan diawali dengan '+62'!", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidPassword(password)) {
            Toast.makeText(this, "Password harus mengandung huruf besar, huruf kecil, angka, dan simbol.", Toast.LENGTH_SHORT).show()
            return
        }

        // Attempt to create a new user with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser!!
                    val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(name).build()
                    user.updateProfile(profileUpdates).addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            user.sendEmailVerification().addOnCompleteListener { verificationTask ->
                                if (verificationTask.isSuccessful) {
                                    Toast.makeText(this, "Verifikasi email telah dikirim, Silahkan cek email dan lakukan verifikasi.", Toast.LENGTH_LONG).show()
                                    saveUserData(user.uid, name, email, password, phoneNumber)
                                    // Simpan data pengguna ke Database (jika perlu) & Clear session dan navigasikan ke halaman login
                                    clearRegistrationSession()
                                    startActivity(
                                        Intent(
                                            this,
                                            Login::class.java
                                        )
                                    )
                                    finish()
                                } else {
                                    Toast.makeText(this, "Gagal mengirim verifikasi email.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                } else {
                    // Check for the specific exception of an already used email
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, "Email telah digunakan. Silakan gunakan email yang berbeda.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Registrasi gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun saveUserData(userId: String, name: String, email: String, password: String, phoneNumber: String) {
        val userData = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "phoneNumber" to phoneNumber
        )
        db.collection("users").document(userId).set(userData)
            .addOnSuccessListener {
                Toast.makeText(this, "User data saved successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save user data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun isValidPassword(password: String): Boolean {
        val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        return pattern.matcher(password).matches()
    }

    private fun clearRegistrationSession() {
        findViewById<EditText>(R.id.etName).text.clear()
        findViewById<EditText>(R.id.etEmail).text.clear()
        findViewById<EditText>(R.id.etPassword).text.clear()
        findViewById<EditText>(R.id.etConfirmPassword).text.clear()
        findViewById<EditText>(R.id.etPhoneNumber).text.clear()
    }
}
