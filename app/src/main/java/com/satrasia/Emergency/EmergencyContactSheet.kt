package com.satrasia.Emergency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.satrasia.databinding.FragmentEmergencyContactSheetBinding

// EmergencyContactSheet adalah kelas yang mengelola tampilan dialog untuk menambahkan kontak darurat.
// Kelas ini memungkinkan pengguna untuk memasukkan dan menyimpan informasi kontak darurat ke dalam Firestore.
class EmergencyContactSheet : DialogFragment() {

    // Variabel yang menampung objek FragmentEmergencyContactSheetBinding yang bersifat nullable.
    private var _binding: FragmentEmergencyContactSheetBinding? = null

    // Properti yang mengakses _binding dan dijamin tidak null.
    private val binding get() = _binding!!

    // Instance dari FirebaseFirestore untuk berinteraksi dengan Firestore.
    private val firestore = FirebaseFirestore.getInstance()

    // Objek FirebaseUser yang merepresentasikan pengguna saat ini yang terautentikasi.
    private val user = FirebaseAuth.getInstance().currentUser

    // Metode untuk meng-inflate layout XML dan mengembalikan root view dari binding.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmergencyContactSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Metode yang dipanggil setelah view dibuat. Mengatur klik listener untuk tombol simpan.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val phone = binding.phone.text.toString().trim()
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                addEmergencyContact(name, phone)
            } else {
                Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Metode untuk menambahkan kontak darurat ke Firestore.
    private fun addEmergencyContact(name: String, phone: String) {
        if (user == null) {
            Toast.makeText(context, "User not signed in", Toast.LENGTH_SHORT).show()
            return
        }

        val contactInfo = hashMapOf(
            "name" to name,
            "phone" to phone
        )

        firestore.collection("users").document(user.uid)
            .collection("emergencyContacts").add(contactInfo)
            .addOnSuccessListener {
                Toast.makeText(context, "Contact added successfully", Toast.LENGTH_SHORT).show()
                dismiss()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error adding contact: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }

    // Metode yang dipanggil saat view dihancurkan untuk membersihkan binding.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
