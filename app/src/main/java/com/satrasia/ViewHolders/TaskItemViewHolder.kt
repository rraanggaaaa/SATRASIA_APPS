package com.satrasia.ViewHolders

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.satrasia.Reminder.TaskItem
import com.satrasia.Reminder.TaskItemClickListener
import com.satrasia.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

/**
 * ViewHolder untuk menampilkan item tugas dalam RecyclerView.
 * Kelas ini mengikat data TaskItem ke tampilan yang sesuai.
 *
 * @param context Konteks aplikasi.
 * @param binding Objek binding untuk tampilan item tugas.
 * @param clickListener Listener untuk menangani aksi pengguna pada item tugas.
 */
class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    /**
     * Metode untuk mengikat data TaskItem ke tampilan ViewHolder.
     *
     * @param taskItem Objek TaskItem yang akan ditampilkan.
     */
    fun bindTaskItem(taskItem: TaskItem) {
        // Menetapkan nama tugas
        binding.name.text = taskItem.name

        // Mengatur teks yang telah selesai dengan coretan jika tugas sudah selesai
        if (taskItem.isCompleted()) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        // Mengatur gambar dan warna tombol selesai berdasarkan status tugas
        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        // Menetapkan aksi klik untuk menandai tugas sebagai selesai
        binding.completeButton.setOnClickListener {
            clickListener.completeTaskItem(taskItem)
        }

        // Menetapkan aksi klik untuk mengedit tugas
        binding.taskCellContainer.setOnClickListener {
            clickListener.editTaskItem(taskItem)
        }

        // Menetapkan aksi klik untuk menghapus tugas
        binding.deleteButton.setOnClickListener {
            clickListener.deleteTaskItem(taskItem)
        }

        // Menetapkan waktu tugas jika tersedia
        if (taskItem.dueTime() != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime())
        else
            binding.dueTime.text = ""
    }
}
