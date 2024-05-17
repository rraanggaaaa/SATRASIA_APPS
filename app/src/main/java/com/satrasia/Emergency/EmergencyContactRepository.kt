package com.satrasia.Emergency

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

// EmergencyContactRepository adalah kelas yang mengelola kontak darurat pengguna di Firestore.
// Kelas ini menyediakan metode untuk mendapatkan, menambahkan, menghapus, dan memperbarui kontak darurat.
class EmergencyContactRepository {

    // Mendapatkan instance dari FirebaseFirestore untuk berinteraksi dengan Firestore.
    private val db = FirebaseFirestore.getInstance()

    // Fungsi untuk mendapatkan kontak darurat sebagai aliran data (Flow) dari Firestore.
    fun getContacts(userId: String): Flow<List<EmergencyContact>> = callbackFlow {
        // Referensi ke koleksi tempat kontak darurat disimpan.
        val collectionRef = db.collection("users").document(userId).collection("emergencyContacts")

        // Mendengarkan perubahan data di Firestore.
        val subscription = collectionRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                // Menangani kesalahan.
                close(e)  // Menutup aliran dengan kesalahan.
                return@addSnapshotListener
            }

            // Mengonversi snapshot ke daftar objek EmergencyContact.
            val contacts = snapshot?.documents?.mapNotNull {
                it.toObject(EmergencyContact::class.java)
            }

            // Mengirim daftar kontak melalui aliran.
            trySend(contacts ?: emptyList()).isSuccess
        }

        // Menutup aliran saat tidak diperlukan lagi.
        awaitClose {
            subscription.remove()  // Berhenti mendengarkan perubahan saat kolektor aliran dibatalkan.
        }
    }

    // Fungsi untuk menambahkan kontak darurat ke Firestore.
    suspend fun addContact(userId: String, contact: EmergencyContact) {
        db.collection("users").document(userId).collection("emergencyContacts").add(contact)
    }

    // Fungsi untuk menghapus kontak darurat dari Firestore.
    fun deleteContact(userId: String, contactId: String) {
        db.collection("users")
            .document(userId)
            .collection("emergencyContacts")
            .document(contactId)
            .delete()
            .addOnSuccessListener {
                // Menangani penghapusan yang berhasil, misalnya logging atau memberi tahu antarmuka pengguna.
                Log.d("Firestore", "Contact successfully deleted!")
            }
            .addOnFailureListener { e ->
                // Menangani kegagalan, misalnya logging atau penanganan kesalahan.
                Log.e("Firestore", "Error deleting contact", e)
            }
    }

    // Fungsi untuk memperbarui kontak darurat di Firestore.
    fun updateContact(userId: String, contactId: String, newName: String, newPhone: String) {
        val contactUpdateMap = hashMapOf<String, Any>(
            "name" to newName,
            "phone" to newPhone
        )

        db.collection("users")
            .document(userId)
            .collection("emergencyContacts")
            .document(contactId)
            .update(contactUpdateMap)
            .addOnSuccessListener {
                // Menangani pembaruan yang berhasil, misalnya logging atau memberi tahu antarmuka pengguna.
                Log.d("Firestore", "Contact successfully updated!")
            }
            .addOnFailureListener { e ->
                // Menangani kegagalan, misalnya logging atau penanganan kesalahan.
                Log.e("Firestore", "Error updating contact", e)
            }
    }
}
