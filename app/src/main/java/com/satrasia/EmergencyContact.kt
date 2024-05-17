package com.satrasia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.satrasia.Adapter.EmergencyContactAdapter
import com.satrasia.Emergency.EmergencyContactRepository
import com.satrasia.Emergency.EmergencyContactSheet
import com.satrasia.ViewModels.ContactViewModel
import com.satrasia.ViewModels.ContactViewModelFactory

class EmergencyContact : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var newButtonContact: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_contact)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener{
            val intent = Intent(this, Setting::class.java)
            startActivity(intent)
        }


        newButtonContact = findViewById(R.id.newButtonContact)
        recyclerView = findViewById(R.id.contactRecyclerView)

        // Set up ViewModel
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            // Handle the case where there is no logged-in user
            finish()  // Or navigate the user to the login screen
            return
        }

        // Set up ViewModel
        val repository = EmergencyContactRepository()
        val viewModelFactory = ContactViewModelFactory(repository, userId)
        contactViewModel = ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)

        newButtonContact.setOnClickListener {
            // Assuming EmergencyContactSheet is a dialog fragment for adding contacts
            EmergencyContactSheet().show(supportFragmentManager, "newContact")
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EmergencyContact)
            // The adapter must be changed to correspond with EmergencyContact data
            adapter = EmergencyContactAdapter(listOf())
        }

        // Assuming you have LiveData in your ViewModel to observe contacts
        contactViewModel.contacts.observe(this) { contacts ->
            (recyclerView.adapter as EmergencyContactAdapter).submitList(contacts)
        }
    }
}
