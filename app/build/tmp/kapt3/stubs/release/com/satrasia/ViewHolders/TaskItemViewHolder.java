package com.satrasia.ViewHolders;

/**
 * ViewHolder untuk menampilkan item tugas dalam RecyclerView.
 * Kelas ini mengikat data TaskItem ke tampilan yang sesuai.
 *
 * @param context Konteks aplikasi.
 * @param binding Objek binding untuk tampilan item tugas.
 * @param clickListener Listener untuk menangani aksi pengguna pada item tugas.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/satrasia/ViewHolders/TaskItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "binding", "Lcom/satrasia/databinding/TaskItemCellBinding;", "clickListener", "Lcom/satrasia/Reminder/TaskItemClickListener;", "(Landroid/content/Context;Lcom/satrasia/databinding/TaskItemCellBinding;Lcom/satrasia/Reminder/TaskItemClickListener;)V", "timeFormat", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "bindTaskItem", "", "taskItem", "Lcom/satrasia/Reminder/TaskItem;", "app_release"})
public final class TaskItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.satrasia.databinding.TaskItemCellBinding binding = null;
    @org.jetbrains.annotations.NotNull
    private final com.satrasia.Reminder.TaskItemClickListener clickListener = null;
    private final java.time.format.DateTimeFormatter timeFormat = null;
    
    public TaskItemViewHolder(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.satrasia.databinding.TaskItemCellBinding binding, @org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItemClickListener clickListener) {
        super(null);
    }
    
    /**
     * Metode untuk mengikat data TaskItem ke tampilan ViewHolder.
     *
     * @param taskItem Objek TaskItem yang akan ditampilkan.
     */
    public final void bindTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem) {
    }
}