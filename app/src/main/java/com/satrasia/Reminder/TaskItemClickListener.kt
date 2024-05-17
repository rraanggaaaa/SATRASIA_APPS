package com.satrasia.Reminder

/**
 * Interface TaskItemClickListener mendefinisikan metode callback untuk aksi-aksi
 * yang dapat dilakukan pada item tugas dalam daftar.
 */
interface TaskItemClickListener {
    /**
     * Dipanggil ketika pengguna memilih untuk mengedit sebuah item tugas.
     * @param taskItem Item tugas yang akan diedit.
     */
    fun editTaskItem(taskItem: TaskItem)

    /**
     * Dipanggil ketika pengguna memilih untuk menandai sebuah item tugas sebagai selesai.
     * @param taskItem Item tugas yang akan ditandai selesai.
     */
    fun completeTaskItem(taskItem: TaskItem)

    /**
     * Dipanggil ketika pengguna memilih untuk menghapus sebuah item tugas.
     * @param taskItem Item tugas yang akan dihapus.
     */
    fun deleteTaskItem(taskItem: TaskItem)
}
