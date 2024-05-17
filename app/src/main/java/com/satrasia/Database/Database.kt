//package com.satrasia.Database
//
//import com.google.firebase.database.FirebaseDatabase
//
//// DATABASE FIREBASE
//class UsersDatabaseHandler {
//
//    // DEFINISIKAN DATABASE FIREBASE
//    private val database =
//        FirebaseDatabase.getInstance("https://satrasia-f7dd4-default-rtdb.asia-southeast1.firebasedatabase.app/")
//
//    // Fungsi untuk mendaftarkan pengguna baru ke Firebase Realtime Database
//    fun registerUser(user: Users, onComplete: (Boolean, String) -> Unit) {
//
//        // Membersihkan email dari karakter '.' karena Firebase Realtime Database tidak mendukung karakter tersebut dalam kunci
//        val cleanedEmail = user.email.replace(".", ",")
//
//        // Menyimpan data pengguna ke database di jalur "users/{cleanedEmail}"
//        database.getReference("users").child(cleanedEmail).setValue(user)
//            .addOnSuccessListener {
//                // Panggil callback dengan parameter (true, "User registration successful") jika operasi berhasil
//                onComplete(true, "User registration successful")
//            }
//            .addOnFailureListener { exception ->
//                // Panggil callback dengan parameter (false, "Failed to register user: {exception.message}") jika operasi gagal
//                onComplete(false, "Failed to register user: ${exception.message}")
//            }
//    }
//}
