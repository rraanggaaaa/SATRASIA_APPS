package com.satrasia.Reminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.satrasia.AlarmReceiver
import com.satrasia.R
import com.satrasia.ViewModels.TaskViewModel
import com.satrasia.databinding.FragmentNewTaskSheetBinding
import java.time.LocalTime
import java.util.Calendar

/**
 * NewTaskSheet mengelola tampilan dialog untuk menambahkan atau mengedit tugas dengan opsi pengaturan pengingat waktu.
 * Dialog ini memungkinkan pengguna untuk menginput atau mengedit informasi tugas dan menyimpannya ke dalam ViewModel.
 * Pengguna juga dapat mengatur alarm untuk tugas jika diperlukan.
 *
 * @param taskItem Item tugas yang bisa null, digunakan untuk mengisi ulang data tugas dalam mode edit.
 */
class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentNewTaskSheetBinding  // Binding untuk komponen UI
    private lateinit var taskViewModel : TaskViewModel  // ViewModel untuk mengelola data tugas

    private var dueTime: LocalTime? = null  // Waktu jatuh tempo yang diatur melalui TimePicker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menginisialisasi tampilan berdasarkan apakah itu edit atau penambahan tugas baru
        if (taskItem != null) {
            binding.taskTitle.text = "Edit Aktivitas"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.desc.text = editable.newEditable(taskItem!!.desc)
            taskItem!!.dueTime()?.let {
                dueTime = it
                updateTimeButtonText()
            }
        } else {
            binding.taskTitle.text = "Aktivitas Baru"
        }

        // Menginisialisasi ViewModel dari Activity
        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        // Mengatur listener untuk tombol simpan, kembali, dan pengatur waktu
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.backButton.setOnClickListener {
            cancelAction()
        }
        binding.timePickerButton.setOnClickListener {
            val now = LocalTime.now()
            dueTime = dueTime ?: now
            val listener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                dueTime = LocalTime.of(hourOfDay, minute)
                updateTimeButtonText()
                scheduleAlarm(dueTime!!, binding.name.text.toString(), binding.desc.text.toString())
            }
            TimePickerDialog(requireActivity(), R.style.MyDarkTimePickerDialog, listener, dueTime!!.hour, dueTime!!.minute, true).apply {
                setTitle("Aktivitas Pukul :")
                show()
            }
        }
    }

    // Memperbarui teks pada tombol pemilih waktu
    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)
    }

    // Menyimpan atau mengupdate tugas berdasarkan kondisi
    private fun saveAction() {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        val dueTimeString = if (dueTime == null) null else TaskItem.timeFormatter.format(dueTime)
        if (taskItem == null) {
            val newTask = TaskItem(name, desc, dueTimeString, null)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskItem!!.name = name
            taskItem!!.desc = desc
            taskItem!!.dueTimeString = dueTimeString
            taskViewModel.updateTaskItem(taskItem!!)
        }
        dismiss()
    }

    // Menutup dialog tanpa menyimpan perubahan
    private fun cancelAction() {
        dismiss()
    }

    // Mengatur alarm berdasarkan waktu yang dipilih
    @SuppressLint("UnspecifiedImmutableFlag", "ScheduleExactAlarm")
    private fun scheduleAlarm(time: LocalTime, name: String, description: String) {
        val context = context ?: return

        val intent = Intent(context, AlarmReceiver::class.java).apply{
            putExtra("TASK_NAME", name)
            putExtra("TASK_DESCRIPTION", description)
        }

        // Membuat PendingIntent untuk menjalankan AlarmReceiver pada waktu yang ditentukan
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Mendapatkan waktu sekarang dan mengatur waktu alarm sesuai dengan waktu yang dipilih
        val now = Calendar.getInstance()
        val alarmTime = now.clone() as Calendar
        alarmTime.set(Calendar.HOUR_OF_DAY, time.hour)
        alarmTime.set(Calendar.MINUTE, time.minute)
        alarmTime.set(Calendar.SECOND, 0)
        alarmTime.set(Calendar.MILLISECOND, 0)

        // Jika waktu alarm sebelum waktu sekarang, atur untuk berjalan pada hari berikutnya
        if (alarmTime.before(now)) {
            alarmTime.add(Calendar.DATE, 1)
        }

        // Mengambil AlarmManager dan mengatur alarm yang tepat dan membolehkan berjalan saat idle
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        alarmManager?.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime.timeInMillis, pendingIntent)
    }
}