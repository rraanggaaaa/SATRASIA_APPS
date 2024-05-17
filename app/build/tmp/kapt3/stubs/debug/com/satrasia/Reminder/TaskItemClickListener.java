package com.satrasia.Reminder;

/**
 * Interface TaskItemClickListener mendefinisikan metode callback untuk aksi-aksi
 * yang dapat dilakukan pada item tugas dalam daftar.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\b"}, d2 = {"Lcom/satrasia/Reminder/TaskItemClickListener;", "", "completeTaskItem", "", "taskItem", "Lcom/satrasia/Reminder/TaskItem;", "deleteTaskItem", "editTaskItem", "app_debug"})
public abstract interface TaskItemClickListener {
    
    /**
     * Dipanggil ketika pengguna memilih untuk mengedit sebuah item tugas.
     * @param taskItem Item tugas yang akan diedit.
     */
    public abstract void editTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem);
    
    /**
     * Dipanggil ketika pengguna memilih untuk menandai sebuah item tugas sebagai selesai.
     * @param taskItem Item tugas yang akan ditandai selesai.
     */
    public abstract void completeTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem);
    
    /**
     * Dipanggil ketika pengguna memilih untuk menghapus sebuah item tugas.
     * @param taskItem Item tugas yang akan dihapus.
     */
    public abstract void deleteTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem);
}