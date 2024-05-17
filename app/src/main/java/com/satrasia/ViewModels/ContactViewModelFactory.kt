package com.satrasia.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.satrasia.Emergency.EmergencyContactRepository

class ContactViewModelFactory(
    private val repository: EmergencyContactRepository,
    private val userId: String  // Adding userId as a constructor parameter
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            // Pass the userId to the ViewModel
            return ContactViewModel(repository, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
