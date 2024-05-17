package com.satrasia.Reminder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.satrasia.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Representasi entitas 'TaskItem' untuk tabel dalam Room Database yang menyimpan tugas pengingat.
 *
 * @param name Nama tugas yang harus dilakukan.
 * @param desc Deskripsi tugas.
 * @param dueTimeString Waktu jatuh tempo tugas sebagai string, bisa null jika tidak ditetapkan.
 * @param completedDateString Tanggal penyelesaian tugas sebagai string, bisa null jika tugas belum selesai.
 * @param id Primary key yang dihasilkan otomatis untuk identifikasi unik setiap tugas.
 */
@Entity(tableName = "reminder_table")
class TaskItem(
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "desc") var desc : String,
    @ColumnInfo(name = "dueTimeString") var dueTimeString: String?,
    @ColumnInfo(name = "completedDateString") var completedDateString : String?,
    @PrimaryKey(autoGenerate = true) var id : Int = 0
) {
    /**
     * Mengembalikan tanggal penyelesaian tugas jika ada, null jika belum diselesaikan.
     */
    fun completedDate(): LocalDate? = completedDateString?.let { LocalDate.parse(it) }

    /**
     * Mengembalikan waktu jatuh tempo tugas jika ada, null jika tidak ditetapkan.
     */
    fun dueTime(): LocalTime? = dueTimeString?.let { LocalTime.parse(it) }

    /**
     * Menentukan apakah tugas telah selesai.
     */
    fun isCompleted() = completedDate() != null

    /**
     * Mengembalikan resource gambar yang sesuai dengan status penyelesaian tugas.
     */
    fun imageResource(): Int = if (isCompleted()) R.drawable.vector_check else R.drawable.vector_uncheck

    /**
     * Mengembalikan resource gambar yang sesuai untuk tugas yang belum selesai.
     */
    fun imageResourceUncompleted(): Int = if (isCompleted()) R.drawable.vector_uncheck else R.drawable.vector_check

    /**
     * Mengembalikan warna gambar berdasarkan status penyelesaian tugas.
     */
    fun imageColor(context: Context): Int = if (isCompleted()) green(context) else black(context)

    /**
     * Mengembalikan warna gambar yang sesuai untuk tugas yang belum selesai.
     */
    fun imageColorUncompleted(context: Context): Int = if (isCompleted()) black(context) else green(context)

    /**
     * Mengembalikan warna hijau dari resources.
     */
    private fun green(context: Context) = ContextCompat.getColor(context, R.color.teal_200)

    /**
     * Mengembalikan warna hitam dari resources.
     */
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    companion object {
        /**
         * Formatter waktu untuk standarisasi format string waktu ISO.
         */
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME

        /**
         * Formatter tanggal untuk standarisasi format string tanggal ISO.
         */
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}
