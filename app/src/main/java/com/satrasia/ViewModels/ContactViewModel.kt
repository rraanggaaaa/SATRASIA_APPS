package com.satrasia.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.satrasia.Emergency.EmergencyContactRepository
import com.satrasia.Emergency.EmergencyContact
import kotlinx.coroutines.launch

class ContactViewModel(
    private val repository: EmergencyContactRepository,
    private val userId: String
) : ViewModel() {

    // LiveData containing a list of Emergency Contacts, observed by the UI
    val contacts: LiveData<List<EmergencyContact>> = repository.getContacts(userId).asLiveData()

    /**
     * Adds a new contact to the repository.
     * @param name The name of the contact.
     * @param phone The phone number of the contact.
     */
    fun addContact(name: String, phone: String) {
        val contact = EmergencyContact(name, phone)
        viewModelScope.launch {
            try {
                repository.addContact(userId, contact)
            } catch (e: Exception) {
                // Handle exceptions, e.g., show an error message to the user
                // You might want to log this error or inform the user through a UI component
            }
        }
    }

    // Example method to update a contact if you have an ID or some unique identifier
    fun updateContact(contactId: String, newName: String, newPhone: String) {
        viewModelScope.launch {
            try {
                // Assuming updateContact in the repository requires a contact object or parameters
                repository.updateContact(userId, contactId, newName, newPhone)
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    // Method to delete a contact
    fun deleteContact(contactId: String) {
        viewModelScope.launch {
            try {
                repository.deleteContact(userId, contactId)
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    // Consider adding more functionality as needed, such as fetching a specific contact details
}
