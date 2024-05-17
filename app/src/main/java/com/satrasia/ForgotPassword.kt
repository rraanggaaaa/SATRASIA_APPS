package com.satrasia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ForgotPassword : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnVerification: Button
    private lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize UI Components
        btnVerification = findViewById(R.id.btnVerification)
        etEmail = findViewById(R.id.etEmail)

        // Set onClickListener for the verification button
        btnVerification.setOnClickListener {
            sendEmailForgotPassword()
        }
    }

    private fun sendEmailForgotPassword() {
        val email = etEmail.text.toString()

        if (!email.contains("@")) {
            Toast.makeText(this, "Email tidak valid!", Toast.LENGTH_SHORT).show()
            return
        }

        // Send a password reset email
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email untuk reset password telah dikirim. Silakan cek email Anda.", Toast.LENGTH_LONG).show()

                    // Optionally update user-related information here if necessary
                    updateUserResetRequest(email)

                    // Navigate back to login screen or any other appropriate screen
                    Intent(this, Login::class.java).also { startActivity(it) }
                    finish()
                } else {
                    // Handle failed password reset
                    Toast.makeText(this, "Gagal mengirim email reset: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun updateUserResetRequest(email: String) {
        // Example: Update a Firestore document related to the user
        val userResetInfo = hashMapOf(
            "lastResetRequest" to System.currentTimeMillis()  // Logging the time of the request
        )
        // Assuming users are identified by their email in Firestore
        FirebaseFirestore.getInstance().collection("userResets").document(email).set(userResetInfo)
            .addOnSuccessListener {
                Toast.makeText(this, "Reset request logged successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to log reset request: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updatePasswordInFirestore(email: String, newPassword: String) {
        val userInfo = hashMapOf(
            "password" to newPassword
        )

        FirebaseFirestore.getInstance().collection("users").document(email).update(userInfo as Map<String, Any>)
            .addOnSuccessListener {
                Toast.makeText(this, "Password updated in Firestore", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to update password in Firestore: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // This method should be called when the user sets a new password after login
    private fun onNewPasswordSet(newPassword: String) {
        val user = auth.currentUser
        user?.let {
            updatePasswordInFirestore(it.email!!, newPassword)
        }
    }
}
