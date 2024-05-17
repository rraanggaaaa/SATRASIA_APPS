package com.satrasia.Reminder

import android.app.Application

/**
 * Aplikasi utama yang mengextend kelas Application untuk setup global dalam aplikasi.
 * Kelas ini menginisialisasi database dan repository yang akan digunakan di seluruh aplikasi.
 */
class ToDoApplication: Application() {

    // Database diinisialisasi secara lazy agar hanya dibuat saat pertama kali dibutuhkan.
    private val database by lazy { TaskItemDatabase.getDatabase(this) }

    // Repository diinisialisasi secara lazy, mengambil DAO dari database yang sudah diinisialisasi.
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
}
