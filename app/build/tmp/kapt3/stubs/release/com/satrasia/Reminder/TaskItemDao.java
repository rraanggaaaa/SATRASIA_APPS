package com.satrasia.Reminder;

/**
 * Interface DAO yang digunakan untuk mendefinisikan operasi-operasi database untuk tugas atau aktivitas.
 * Menggunakan Room untuk abstraksi dan manipulasi SQLite database.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/satrasia/Reminder/TaskItemDao;", "", "allTaskItems", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/satrasia/Reminder/TaskItem;", "deleteTaskItem", "", "taskItem", "(Lcom/satrasia/Reminder/TaskItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTaskItem", "updateTaskItem", "app_release"})
@androidx.room.Dao
public abstract interface TaskItemDao {
    
    /**
     * Mengambil semua item tugas dari database yang diurutkan berdasarkan ID secara ascending.
     * @return Flow dari daftar TaskItem, memungkinkan UI untuk berespons terhadap data yang berubah secara reaktif.
     */
    @androidx.room.Query(value = "SELECT * FROM reminder_table ORDER BY id ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.satrasia.Reminder.TaskItem>> allTaskItems();
    
    /**
     * Menyisipkan item tugas baru ke dalam database. Jika tugas dengan ID yang sama sudah ada, maka akan digantikan.
     * @param taskItem TaskItem yang akan disisipkan ke dalam database.
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Memperbarui item tugas yang ada di database.
     * @param taskItem TaskItem yang sudah dimodifikasi yang perlu diperbarui dalam database.
     */
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Menghapus item tugas dari database.
     * @param taskItem TaskItem yang akan dihapus dari database.
     */
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}