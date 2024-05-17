package com.satrasia.Reminder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Mendefinisikan database Room untuk entitas TaskItem.
 *
 * Kelas ini menyediakan instansi database dan akses ke DAO.
 */
@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class TaskItemDatabase : RoomDatabase() {

    /**
     * Memberikan DAO untuk operasi item tugas.
     */
    abstract fun taskItemDao(): TaskItemDao

    companion object {
        // Anotasi Volatile memastikan visibilitas perubahan ke INSTANCE di seluruh thread.
        @Volatile
        private var INSTANCE: TaskItemDatabase? = null

        /**
         * Mengembalikan instansi tunggal dari TaskItemDatabase.
         *
         * Fungsi ini memastikan bahwa hanya satu instansi database yang dibuat selama masa hidup aplikasi.
         * Menggunakan blok synchronized untuk mencegah thread multipel membuat banyak instansi.
         *
         * @param context Context yang digunakan untuk membuat atau membuka database.
         * @return Instansi tunggal dari TaskItemDatabase.
         */
        fun getDatabase(context: Context): TaskItemDatabase {
            // Mengembalikan instansi yang ada jika sudah ada atau membuat yang baru secara aman thread
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskItemDatabase::class.java,
                    "reminder_database"  // Nama file database
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
