package com.satrasia.Adapter

//IMPORT CLASS DAN DEPENDENCY
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.satrasia.Emergency.EmergencyContact
import com.satrasia.databinding.EmergencyContactCellBinding

//MEMBUAT CLASS DENGAN LIST ADAPTER, VIEW HOLDER, DAN EMERGENCY CONTACT CALLBACK SEBAGAI RESPONSE
class EmergencyContactAdapter(listOf: List<Any>) : ListAdapter<EmergencyContact, EmergencyContactAdapter.ViewHolder>(ContactDiffCallback()) {

//    CREATE LAYOUT VIEW HOLDER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EmergencyContactCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

//    INCREMENT LAYOUT JIKA DITAMBAHKAN LAYOUT BARU
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

//    BINDING RECYCLE VIEW UNTUK MENAMPILKAN NAMA DAN NOMOR TELEPON YANG DIDAFTARKAN
    class ViewHolder(private val binding: EmergencyContactCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: EmergencyContact) {
            binding.etName.text = contact.name
            binding.etPhoneNumber.text = contact.phone
        }
    }

//    REPLACE NOMOR LAMA MENGGUNAKAN NOMOR BARU
    class ContactDiffCallback : DiffUtil.ItemCallback<EmergencyContact>() {
        override fun areItemsTheSame(oldItem: EmergencyContact, newItem: EmergencyContact): Boolean {
            return oldItem.phone == newItem.phone // Example
        }

//    COMMIT
        override fun areContentsTheSame(oldItem: EmergencyContact, newItem: EmergencyContact): Boolean {
            return oldItem == newItem
        }
    }
}
