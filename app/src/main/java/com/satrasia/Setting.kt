package com.satrasia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mikhaellopez.circularimageview.CircularImageView

class Setting : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    private lateinit var btnLogout: Button
    private lateinit var etName: TextView
    private lateinit var etEmail: TextView
    private lateinit var etNoTelp: TextView
    private lateinit var btnContact: LinearLayout
    private lateinit var btnAccountManager: LinearLayout
    private lateinit var btnDeveloper: LinearLayout
    private lateinit var profileImage: CircularImageView
    private lateinit var storage: FirebaseStorage
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        navView = findViewById(R.id.nav)
        setupBottomNavigationView()

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etNoTelp = findViewById(R.id.etNoTelp)
        btnLogout = findViewById(R.id.buttonLogout)
        profileImage = findViewById(R.id.CircularImageView) // Initialize profileImage

        // Initialize Firebase Storage
        storage = FirebaseStorage.getInstance()

        displayFirestoreUserInfo()
        setupLogoutButton()

        btnContact = findViewById(R.id.kontakDarurat)
        btnContact.setOnClickListener {
            val intent = Intent(this, EmergencyContact::class.java)
            startActivity(intent)
        }
        btnAccountManager = findViewById(R.id.kelolaAkun)
        btnAccountManager.setOnClickListener {
            val intent = Intent(this, AccountManager::class.java)
            startActivity(intent)
        }
        btnDeveloper = findViewById(R.id.tentangDeveloper)
        btnDeveloper.setOnClickListener {
            val intent = Intent(this, AboutApps::class.java)
            startActivity(intent)
        }
    }

    private fun setupBottomNavigationView() {
        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> navigateTo(Home::class.java)
                R.id.navigation_iot -> navigateTo(Main::class.java)
                R.id.navigation_setting -> navigateTo(Setting::class.java)
            }
            true
        }
    }

    private fun navigateTo(destination: Class<*>) {
        val intent = Intent(this, destination)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun displayFirestoreUserInfo() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let { usr ->
            db.collection("users").document(usr.uid).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    etName.text = document.getString("name") ?: "Not available"
                    etEmail.text = document.getString("email") ?: "Not available"
                    etNoTelp.text = document.getString("phoneNumber") ?: "Not available"

                    // Load profile image after fetching user info
                    val profileImageUrl = document.getString("profileImageUrl")
                    profileImageUrl?.let { imageUrl ->
                        fetchAndSetProfileImage(imageUrl)
                    }
                } else {
                    Toast.makeText(
                        this,
                        "No additional user info found in Firestore.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Failed to fetch user info: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupLogoutButton() {
        btnLogout.setOnClickListener {
            // Logout from Firebase
            FirebaseAuth.getInstance().signOut()

            // Clear the session from SharedPreferences
            val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("IsLoggedIn", false).apply()

            // Navigate to LoginActivity and clear the activity stack
            val intent = Intent(this, Main::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun fetchAndSetProfileImage(imageUrl: String) {
        // Use Glide or Picasso to load the image from the URL into your ImageView
        // For example, using Glide:
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.vector_manage_account) // Placeholder image while loading
            .error(R.drawable.vector_manage_account) // Image to show if loading fails
            .into(profileImage) // Set the loaded image into the profileImage ImageView
    }
}
