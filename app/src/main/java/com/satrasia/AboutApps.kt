package com.satrasia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * Kelas untuk menampilkan halaman tentang aplikasi.
 * Menyediakan tombol untuk kembali ke pengaturan dan membuka halaman GitHub.
 */
class AboutApps : AppCompatActivity() {

    // Deklarasi variabel untuk tombol kembali dan tombol GitHub
    private lateinit var btnBack : ImageButton
    private lateinit var btnGithub : LinearLayout

    /**
     * Metode yang dipanggil saat aktivitas dibuat.
     * Menginisialisasi tampilan dan menetapkan aksi klik untuk tombol.
     *
     * @param savedInstanceState State yang disimpan dari instance sebelumnya.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_apps)

        // Mengubah warna status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        // Menginisialisasi tombol kembali dan menetapkan aksi klik
        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            // Membuat intent untuk membuka aktivitas pengaturan
            val intent = Intent(this, Setting::class.java)
            startActivity(intent)
        }

        // Menginisialisasi tombol GitHub dan menetapkan aksi klik
        btnGithub = findViewById(R.id.findMeOn)
        btnGithub.setOnClickListener {
            // Membuat intent untuk membuka halaman GitHub di browser
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/rraanggaaaa"))
            startActivity(browserIntent)
        }
    }
}
