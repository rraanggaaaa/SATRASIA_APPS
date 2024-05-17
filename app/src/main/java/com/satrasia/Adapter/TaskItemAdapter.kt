package com.satrasia.Adapter

//IMPORT CLASS DAN DEPENDENCY
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satrasia.Reminder.TaskItemClickListener
import com.satrasia.ViewHolders.TaskItemViewHolder
import com.satrasia.Reminder.TaskItem
import com.satrasia.databinding.TaskItemCellBinding

//BUAT CLASS UNTUK ADAPTER TASK IEM (PENGINGAT RUTINITAS)
class TaskItemAdapter(

//    DEFINISIKAN TASK ITEM DAN CLASS CLICK LISTENER YANG TELAH DIBUAT
    private var taskItems: List<TaskItem>, // Jadikan MutableList agar bisa diubah
    private val clickListener: TaskItemClickListener
): RecyclerView.Adapter<TaskItemViewHolder>() {

//    CREATE BINDING UNTUK MENAMPILAKAN TASK ITEM DARI TASK ITEM VIEWHOLDE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskItemCellBinding.inflate(from, parent, false)
        return TaskItemViewHolder(parent.context, binding, clickListener)
    }

//    INCREMENT TASK ITEM JIKA LEBIH DARI SATU
    override fun getItemCount(): Int = taskItems.size

//    POSISI TASK ITEM BARU DIBAWAH TASK ITEM YANG TEALH ADA
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItems[position])
    }
}
