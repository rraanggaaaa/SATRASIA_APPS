package com.satrasia.Reminder

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * Repository untuk mengelola operasi data pada entitas TaskItem.
 * Repository ini menyediakan metode abstrak untuk interaksi dengan database melalui DAO.
 *
 * @param taskItemDao DAO yang menyediakan metode akses data untuk entitas TaskItem.
 */
class TaskItemRepository(private val taskItemDao: TaskItemDao) {
    // Stream data semua item tugas, dapat diobservasi untuk pembaruan UI secara real-time.
    val allTaskItem: Flow<List<TaskItem>> = taskItemDao.allTaskItems()

    /**
     * Menyisipkan item tugas baru ke dalam database.
     * Metode ini harus dipanggil dari thread yang bukan merupakan main thread karena operasi database
     * yang berpotensi lama.
     *
     * @param taskItem Item tugas yang akan disisipkan.
     */
    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem) {
        taskItemDao.insertTaskItem(taskItem)
    }

    /**
     * Memperbarui item tugas yang ada di database.
     * Serupa dengan insert, pemanggilan ini harus dilakukan dari background thread.
     *
     * @param taskItem Item tugas yang perlu diperbarui.
     */
    @WorkerThread
    suspend fun updateTaskItem(taskItem: TaskItem) {
        taskItemDao.updateTaskItem(taskItem)
    }

    /**
     * Menghapus item tugas dari database.
     * Penghapusan juga harus dilakukan dari background thread untuk menghindari jeda pada UI thread.
     *
     * @param taskItem Item tugas yang akan dihapus.
     */
    @WorkerThread
    suspend fun deleteTaskItem(taskItem: TaskItem) {
        taskItemDao.deleteTaskItem(taskItem)
    }
}
