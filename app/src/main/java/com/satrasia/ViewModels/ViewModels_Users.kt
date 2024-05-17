package com.satrasia.Database

import com.google.firebase.database.FirebaseDatabase

class UserDataHandler {

    companion object {

        private val database = FirebaseDatabase.getInstance("https://satrasia-f7dd4-default-rtdb.asia-southeast1.firebasedatabase.app/")

        // Fungsi baru untuk memeriksa email
        fun checkEmailExists(email: String, callback: (Boolean) -> Unit) {
            val cleanedEmail = email.replace(".", ",")
            database.reference.child("users").child(cleanedEmail).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    callback(true)
                } else {
                    callback(false)
                }
            }.addOnFailureListener {
                callback(false)
            }
        }
    }
}



