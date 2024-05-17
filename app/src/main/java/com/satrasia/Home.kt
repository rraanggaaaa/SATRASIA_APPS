package com.satrasia

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mikhaellopez.circularimageview.CircularImageView
import com.satrasia.Adapter.TaskItemAdapter
import com.satrasia.Reminder.NewTaskSheet
import com.satrasia.Reminder.TaskItem
import com.satrasia.Reminder.TaskItemClickListener
import com.satrasia.Reminder.ToDoApplication
import com.satrasia.Retrofit.LocationResponse
import com.satrasia.Retrofit.RetrofitClient
import com.satrasia.ViewModels.TaskItemModelFactory
import com.satrasia.ViewModels.TaskViewModel
import com.satrasia.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity(), OnMapReadyCallback, TaskItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var profileImage: CircularImageView
    private lateinit var mapView: MapView
    private lateinit var nameTextView: TextView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityHomeBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskItemModelFactory((application as ToDoApplication).repository)
    }
    private lateinit var newButtonTask: Button
    private lateinit var navView: BottomNavigationView
    private val handler = Handler(Looper.getMainLooper())
    private val delay: Long = 5000 // 5 seconds delay
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_900)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener(this)

        profileImage = findViewById(R.id.CircularImageView)
        profileImage.setOnClickListener{
            val intent = Intent(this, Setting::class.java)
            startActivity(intent)
        }// Initialize profileImage


        newButtonTask = findViewById(R.id.newButtonTask)
        newButtonTask.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "Task Pengingat Baru")
        }
        setRecyclerView()

        navView = findViewById(R.id.nav)
        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, Home::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                R.id.navigation_iot -> {
                    val intent = Intent(this, Main::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                R.id.navigation_setting -> {
                    val intent = Intent(this, Setting::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
            }
            true
        }

        mapView = findViewById(R.id.map)

        displayFirestoreUserInfo()
        nameTextView = findViewById(R.id.etName)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val currentUser = auth.currentUser
        val name = currentUser?.displayName
        nameTextView.text = name ?: "Guest"

        // Schedule refreshing every 5 seconds
        handler.postDelayed(object : Runnable {
            override fun run() {
                mapView.getMapAsync(this@Home)
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        fetchDataFromServer(googleMap)
    }

    private fun fetchDataFromServer(googleMap: GoogleMap) {
        RetrofitClient.apiService.getLocation(999).enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val location = LatLng(it.lat.toDouble(), it.lng.toDouble())
                        googleMap.addMarker(MarkerOptions().position(location).title("Lokasi Terkini"))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
                    }
                } else {
                    Toast.makeText(this@Home, "Gagal Membaca Lokasi", Toast.LENGTH_SHORT).show()
                }
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                Toast.makeText(this@Home, "Gagal Membaca Lokasi: ${t.message}", Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun displayFirestoreUserInfo() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let { usr ->
            db.collection("users").document(usr.uid).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    // Load profile image after fetching user info
                    val profileImageUrl = document.getString("profileImageUrl")
                    profileImageUrl?.let { imageUrl ->
                        fetchAndSetProfileImage(imageUrl)
                    }
                } else {
                    Toast.makeText(
                        this,
                        "No additional user info found in Firestore.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Failed to fetch user info: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun fetchAndSetProfileImage(imageUrl: String) {
        // Load the image into the CircularImageView using Glide
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.vector_manage_account) // Placeholder image while loading
            .error(R.drawable.vector_manage_account) // Image to show if loading fails
            .into(profileImage) // Set the loaded image into the profileImage CircularImageView
    }

    override fun onRefresh() {
        mapView.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        handler.removeCallbacksAndMessages(null) // Remove any pending callbacks
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun setRecyclerView() {
        val homeActivity = this
        taskViewModel.taskItems.observe(this) {
            binding.toDoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it ?: listOf(), homeActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager, "Pengingat Rutinitas Baru")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }

    override fun deleteTaskItem(taskItem: TaskItem) {
        taskViewModel.deleteTask(taskItem)
        Toast.makeText(this, "Pengingat Berhasil Dihapus", Toast.LENGTH_SHORT).show()
    }
}
