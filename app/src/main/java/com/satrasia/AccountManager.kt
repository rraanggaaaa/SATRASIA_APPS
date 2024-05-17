package com.satrasia

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mikhaellopez.circularimageview.CircularImageView
import java.io.ByteArrayOutputStream
import java.util.UUID
import java.util.concurrent.TimeUnit

class AccountManager : AppCompatActivity() {
    private lateinit var profileImage: CircularImageView
    private lateinit var etName: TextView
    private lateinit var etEmail: TextView
    private lateinit var etNoTelp: TextView
    private lateinit var updateName: EditText
    private lateinit var updateEmail: EditText
    private lateinit var updatePhoneNumber: EditText

    private lateinit var btnBack: ImageButton
    private lateinit var btnDeleteProfile: Button
    private lateinit var btnUpdateProfile: Button
    private lateinit var btnSaveName: Button
    private lateinit var btnSaveEmail: Button
    private lateinit var btnSavePhoneNumber: Button
    private lateinit var btnChangePassword: Button
    private lateinit var btnDeleteAccount: Button

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private lateinit var storage: FirebaseStorage
    private lateinit var imageUri: Uri
    private var previousImageRef: StorageReference? = null

    private val REQUEST_IMAGE_PICK = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_account_manager)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        // Initialize Firebase Storage
        storage = FirebaseStorage.getInstance()
        profileImage = findViewById(R.id.CircularImageView)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etNoTelp = findViewById(R.id.etNoTelp)

        updateName = findViewById(R.id.updateName)
        updateEmail = findViewById(R.id.updateEmail)
        updatePhoneNumber = findViewById(R.id.updatePhoneNumber)

        btnBack = findViewById(R.id.btnBack)
        btnDeleteProfile = findViewById(R.id.btnDeleteProfile)
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile)
        btnSaveName = findViewById(R.id.btnSaveName)
        btnSaveEmail = findViewById(R.id.btnSaveEmail)
        btnSavePhoneNumber = findViewById(R.id.btnSavePhoneNumber)
        btnChangePassword = findViewById(R.id.btnChangePassword)
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount)

        displayProfileImage()
        displayFirestoreUserInfo()

        btnBack.setOnClickListener {
            val intent = Intent(this, Setting::class.java)
            startActivity(intent)
        }

        btnUpdateProfile.setOnClickListener {
            pickImageFromGallery()
        }

        btnDeleteProfile.setOnClickListener {
            deleteProfilePicture()
        }

        btnSaveName.setOnClickListener {
            updateName()
        }

        btnSaveEmail.setOnClickListener {
            val newEmail = updateEmail.text.toString().trim()
            if (newEmail.isNotEmpty()) {
                sendEmailVerification(newEmail)
            } else {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            }
        }

        btnSavePhoneNumber.setOnClickListener {
            val newPhone = updatePhoneNumber.text.toString().trim()
            if (newPhone.isNotEmpty()) {
                sendSMSVerification(newPhone)
            } else {
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
            }
        }

        btnChangePassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        btnDeleteAccount.setOnClickListener {
            confirmAndDeleteAccount()
        }
    }

    private fun displayProfileImage() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let { currentUser ->
            db.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { documentSnapshot ->
                    val imageUrl = documentSnapshot.getString("profileImageUrl")
                    if (imageUrl != null && imageUrl.isNotEmpty()) {
                        // Load and display profile image from Firebase Storage
                        fetchAndSetProfileImage(imageUrl)
                    } else {
                        // Display default profile image from drawable
                        fetchAndSetDefaultProfileImage()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(
                        applicationContext,
                        "Failed to fetch profile image from database: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Display default profile image from drawable
                    fetchAndSetDefaultProfileImage()
                }
        } ?: run {
            // Display default profile image from drawable
            fetchAndSetDefaultProfileImage()
        }
    }

    private val pickVisualMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {
            cropImage(it)
        }
    }

    private fun pickImageFromGallery() {
        pickVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun cropImage(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        inputStream?.close()

        bitmap?.let {
            val cropSize = minOf(bitmap.width, bitmap.height)
            val startX = (bitmap.width - cropSize) / 2
            val startY = (bitmap.height - cropSize) / 2
            val croppedBitmap = Bitmap.createBitmap(bitmap, startX, startY, cropSize, cropSize)

            // Resize the cropped bitmap to fit within 200dp x 200dp
            val resizedBitmap = Bitmap.createScaledBitmap(croppedBitmap, 100.dpToPx(), 100.dpToPx(), true)

            // Create a transparent circle bitmap
            val output = Bitmap.createBitmap(resizedBitmap.width, resizedBitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(output)

            val paint = Paint()
            paint.isAntiAlias = true
            canvas.drawCircle((resizedBitmap.width / 2).toFloat(), (resizedBitmap.height / 2).toFloat(), (resizedBitmap.width / 2).toFloat(), paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(resizedBitmap, 0f, 0f, paint)

            // Display the cropped circle image with transparent background
            profileImage.setImageBitmap(output)

            // Upload the cropped circle image with transparent background to Firebase Storage
            uploadCroppedImageToFirebaseStorage(output)
        }
    }

    fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    private fun uploadCroppedImageToFirebaseStorage(croppedBitmap: Bitmap) {
        val user = FirebaseAuth.getInstance().currentUser
        val imageName = user?.email ?: UUID.randomUUID().toString() // Gunakan email pengguna jika tersedia, jika tidak, buat UUID acak

        val imageRef = storage.reference.child("profile_images/$imageName")

        val baos = ByteArrayOutputStream()
        croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        imageRef.putBytes(data)
            .addOnSuccessListener { taskSnapshot ->
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    currentUser?.let { currentUser ->
                        // Periksa apakah path di Firebase Storage kosong, jika ya, gunakan avatar default
                        if (previousImageRef == null) {
                            // Path kosong, tampilkan avatar default
                            fetchAndSetDefaultProfileImage()
                        }

                        // Set previousImageRef to the current imageRef
                        previousImageRef = imageRef
                        // Update profile image URL in Firestore
                        db.collection("users").document(currentUser.uid)
                            .update("profileImageUrl", uri.toString())
                            .addOnSuccessListener {
                                Toast.makeText(
                                    applicationContext,
                                    "Profile image uploaded successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                fetchAndSetProfileImage(uri.toString())
                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(
                                    applicationContext,
                                    "Failed to update profile image URL: ${exception.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    applicationContext,
                    "Failed to upload image: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun fetchAndSetDefaultProfileImage() {
        // Gunakan avatar default
        Glide.with(this)
            .load(R.drawable.vector_avatar)
            .placeholder(R.drawable.vector_avatar)
            .error(R.drawable.vector_avatar)
            .into(profileImage)
    }

    private fun deleteProfilePicture() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let { currentUser ->
            db.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { documentSnapshot ->
                    val userEmail = currentUser.email
                    val imageUrl = documentSnapshot.getString("profileImageUrl")
                    if (userEmail != null && imageUrl != null && imageUrl.isNotEmpty()) {
                        val fileName = "$userEmail"

                        val imageRef = storage.reference.child("profile_images/$fileName")
                        imageRef.delete()
                            .addOnSuccessListener {
                                Toast.makeText(
                                    applicationContext,
                                    "Profil berhasil dihapus!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                db.collection("users").document(currentUser.uid)
                                    .update("profileImageUrl", null)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            applicationContext,
                                            "URL gambar terupdate!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        fetchAndSetDefaultProfileImage()
                                    }
                                    .addOnFailureListener { exception ->
                                        Toast.makeText(
                                            applicationContext,
                                            "Gagal mengupdate URL gambar, karena ${exception.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
                            .addOnFailureListener { exception ->
                                // Failed to delete profile image from Firebase Storage
                                Toast.makeText(
                                    applicationContext,
                                    "Gagal menghapus gambar, karena ${exception.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    } else {
                        // Profile image URL not found or empty in Firestore
                        Toast.makeText(
                            applicationContext,
                            "URL gambar tidak tersedia pada Firestore!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener { exception ->
                    // Failed to fetch profile image URL from Firestore
                    Toast.makeText(
                        applicationContext,
                        "Failed to fetch profile image URL from Firestore: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }



    private fun fetchAndSetProfileImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.vector_manage_account)
            .error(R.drawable.vector_manage_account)
            .into(profileImage)
    }

    private fun updateName() {
        val name = updateName.text.toString().trim()
        if (name.isNotEmpty()) {
            val userProfileChangeRequest =
                UserProfileChangeRequest.Builder().setDisplayName(name).build()
            auth.currentUser?.updateProfile(userProfileChangeRequest)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        db.collection("users").document(auth.currentUser!!.uid)
                            .update("name", name)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Name updated successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                etName.text = name
                                updateName.setText("")
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Failed to update name in Firestore: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    } else {
                        Toast.makeText(
                            this,
                            "Failed to update name: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this, "Name field cannot be empty.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendEmailVerification(newEmail: String) {
        if (!isValidEmail(newEmail)) {
            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show()
            return
        }
        auth.currentUser?.updateEmail(newEmail)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.currentUser?.sendEmailVerification()
                    ?.addOnCompleteListener { verificationTask ->
                        if (verificationTask.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Verification email sent to $newEmail",
                                Toast.LENGTH_LONG
                            ).show()
                            updateEmail.setText("")
                        } else {
                            Toast.makeText(
                                this,
                                "Failed to send verification email: ${verificationTask.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this,
                    "Failed to update email: ${task.exception?.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun sendSMSVerification(phoneNumber: String) {
        if (!isValidPhone(phoneNumber)) {
            Toast.makeText(this, "Invalid phone number format.", Toast.LENGTH_SHORT).show()
            return
        }
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    auth.currentUser?.updatePhoneNumber(credential)?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            db.collection("users").document(auth.currentUser!!.uid)
                                .update("phoneNumber", phoneNumber)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        applicationContext,
                                        "Phone number updated successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    etNoTelp.text = phoneNumber
                                    updatePhoneNumber.setText("")
                                }
                        }
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(
                        applicationContext,
                        "Verification failed: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, token)
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun displayFirestoreUserInfo() {
        val user = auth.currentUser
        user?.let { usr ->
            db.collection("users").document(usr.uid).get().addOnSuccessListener { document ->
                if (document.exists()) {
                    etName.text = document.getString("name") ?: "Not available"
                    etEmail.text = document.getString("email") ?: "Not available"
                    etNoTelp.text = document.getString("phoneNumber") ?: "Not available"
                    val imageUrl = document.getString("profileImageUrl")
                    imageUrl?.let {
                        fetchAndSetProfileImage(it)
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

    private fun confirmAndDeleteAccount() {
        AlertDialog.Builder(this)
            .setTitle("Confirm Delete")
            .setMessage("Are you sure you want to delete your account? This cannot be undone.")
            .setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, id ->
                deleteAccount()
            })
            .setNegativeButton("Cancel", null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


    private fun deleteAccount() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show()
            return
        }

        promptReAuthentication {
            deleteProfileImageFromStorage(user.uid) {
                deleteUserDataInFirestore(user.uid, "userResets") {
                    deleteUserDataInFirestore(user.uid, "users") {
                        user.delete().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Account deleted successfully", Toast.LENGTH_LONG)
                                    .show()
                                redirectToLogin()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Failed to delete account: ${task.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }


    private fun deleteProfileImageFromStorage(userId: String, onComplete: () -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let { currentUser ->
            db.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { documentSnapshot ->
                    val imageUrl = documentSnapshot.getString("profileImageUrl")
                    if (imageUrl != null && imageUrl.isNotEmpty()) {

                        val userEmail = currentUser.email
                        val fileName = "$userEmail"

                        val imageRef =
                            FirebaseStorage.getInstance().reference.child("profile_images/$fileName")
                        imageRef.delete()
                            .addOnSuccessListener {
                                Toast.makeText(
                                    applicationContext,
                                    "Profile picture deleted successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                db.collection("users").document(userId)
                                    .update("profileImageUrl", null)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            applicationContext,
                                            "Profile image URL updated successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        onComplete()
                                    }
                                    .addOnFailureListener { exception ->
                                        Toast.makeText(
                                            applicationContext,
                                            "Failed to update profile image URL: ${exception.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
                            .addOnFailureListener { exception ->
                                // Failed to delete profile image from Firebase Storage
                                Toast.makeText(
                                    applicationContext,
                                    "Failed to delete profile picture: ${exception.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    } else {
                        // Profile image URL not found or empty in Firestore
                        Toast.makeText(
                            applicationContext,
                            "Profile image URL not found in Firestore",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener { exception ->
                    // Failed to fetch profile image URL from Firestore
                    Toast.makeText(
                        applicationContext,
                        "Failed to fetch profile image URL from Firestore: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun deleteUserDataInFirestore(userId: String, collectionPath: String, onComplete: () -> Unit) {
        FirebaseFirestore.getInstance().collection(collectionPath).document(userId)
            .delete()
            .addOnSuccessListener {
                onComplete()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    applicationContext,
                    "Failed to delete user data from $collectionPath: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }


    private fun redirectToLogin() {
        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun promptReAuthentication(onSuccess: () -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show()
            return
        }

        val passwordInput = EditText(this)
        passwordInput.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        AlertDialog.Builder(this)
            .setTitle("Please Re-authenticate")
            .setMessage("Re-enter your password to proceed:")
            .setView(passwordInput)
            .setPositiveButton("Submit") { _, _ ->
                val password = passwordInput.text.toString()
                val email = currentUser.email
                if (email != null && password.isNotBlank()) {
                    reAuthenticateUser(email, password, onSuccess)
                } else {
                    Toast.makeText(this, "Email or password field is empty.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun reAuthenticateUser(email: String, password: String, onSuccess: () -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val credential = EmailAuthProvider.getCredential(email, password)
            currentUser.reauthenticate(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    Toast.makeText(
                        this,
                        "Re-authentication failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            Toast.makeText(this, "No user logged in to re-authenticate.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPhone(phone: String): Boolean {
        return android.util.Patterns.PHONE.matcher(phone).matches() && phone.length >= 10
    }
}

