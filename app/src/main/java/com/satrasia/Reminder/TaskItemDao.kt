package com.satrasia.Reminder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Interface DAO yang digunakan untuk mendefinisikan operasi-operasi database untuk tugas atau aktivitas.
 * Menggunakan Room untuk abstraksi dan manipulasi SQLite database.
 */
@Dao
interface TaskItemDao {

    /**
     * Mengambil semua item tugas dari database yang diurutkan berdasarkan ID secara ascending.
     * @return Flow dari daftar TaskItem, memungkinkan UI untuk berespons terhadap data yang berubah secara reaktif.
     */
    @Query("SELECT * FROM reminder_table ORDER BY id ASC")
    fun allTaskItems(): Flow<List<TaskItem>>

    /**
     * Menyisipkan item tugas baru ke dalam database. Jika tugas dengan ID yang sama sudah ada, maka akan digantikan.
     * @param taskItem TaskItem yang akan disisipkan ke dalam database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItem(taskItem: TaskItem)

    /**
     * Memperbarui item tugas yang ada di database.
     * @param taskItem TaskItem yang sudah dimodifikasi yang perlu diperbarui dalam database.
     */
    @Update
    suspend fun updateTaskItem(taskItem: TaskItem)

    /**
     * Menghapus item tugas dari database.
     * @param taskItem TaskItem yang akan dihapus dari database.
     */
    @Delete
    suspend fun deleteTaskItem(taskItem: TaskItem)
}
