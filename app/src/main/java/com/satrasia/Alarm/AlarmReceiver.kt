package com.satrasia

//IMPORT CLASS DAN DEPENDENCY
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

//BUAT CALSS ALARMRECEIVER DENGAN ISI FUNGSI BROADCAST RECEIVER UNTUK MENAMPILKAN NAMA DAN DESKRIPSI ALARM DAAT RUTINITAS DIJADWALKAN
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

//      MENG-EXTRACT NAMA, DESKRIPSI DAN PUSH NOTIFICATION KEPADA ALARM UNTUK RUTINITAS DARI INTENT
        val taskName = intent.getStringExtra("TASK_NAME") ?: "Pengingat rutinitas"
        val taskDescription = intent.getStringExtra("TASK_DESCRIPTION") ?: "Kamu memiliki jadwal rutinitas."
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(context, notificationManager)
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(taskName)
            .setContentText(taskDescription)
            .setSmallIcon(R.drawable.vector_alert)  // Use the specific icon you mentioned
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager.notify(1, notification)
    }

//  BUAT PUSH NOTIFICATION SEBAGAI IMPORTANT (PENTING)
    private fun createNotificationChannel(context: Context, notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notifikasi Pengingat Rutinitas"
            val descriptionText = "Notification channel for 'Pengingat Rutinitas'"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

//  MEMBUAT COMPANY OBJECT CHANNEL SEBAGAI PENGINGAT RUTINITAS
    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "pengingat_rutinitas_channel"
    }
}
