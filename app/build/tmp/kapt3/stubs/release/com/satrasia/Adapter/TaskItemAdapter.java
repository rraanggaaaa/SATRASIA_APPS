package com.satrasia.Adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/satrasia/Adapter/TaskItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/satrasia/ViewHolders/TaskItemViewHolder;", "taskItems", "", "Lcom/satrasia/Reminder/TaskItem;", "clickListener", "Lcom/satrasia/Reminder/TaskItemClickListener;", "(Ljava/util/List;Lcom/satrasia/Reminder/TaskItemClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"})
public final class TaskItemAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.satrasia.ViewHolders.TaskItemViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.satrasia.Reminder.TaskItem> taskItems;
    @org.jetbrains.annotations.NotNull
    private final com.satrasia.Reminder.TaskItemClickListener clickListener = null;
    
    public TaskItemAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.satrasia.Reminder.TaskItem> taskItems, @org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItemClickListener clickListener) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.satrasia.ViewHolders.TaskItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.satrasia.ViewHolders.TaskItemViewHolder holder, int position) {
    }
}