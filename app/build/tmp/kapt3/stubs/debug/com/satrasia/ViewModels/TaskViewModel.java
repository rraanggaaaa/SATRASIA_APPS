package com.satrasia.ViewModels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0015"}, d2 = {"Lcom/satrasia/ViewModels/TaskViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/satrasia/Reminder/TaskItemRepository;", "(Lcom/satrasia/Reminder/TaskItemRepository;)V", "taskItems", "Landroidx/lifecycle/LiveData;", "", "Lcom/satrasia/Reminder/TaskItem;", "getTaskItems", "()Landroidx/lifecycle/LiveData;", "setTaskItems", "(Landroidx/lifecycle/LiveData;)V", "addTaskItem", "Lkotlinx/coroutines/Job;", "newTask", "deleteTask", "", "taskItem", "setCompleted", "updateTaskItem", "app_debug"})
public final class TaskViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.satrasia.Reminder.TaskItemRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.LiveData<java.util.List<com.satrasia.Reminder.TaskItem>> taskItems;
    
    public TaskViewModel(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItemRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.satrasia.Reminder.TaskItem>> getTaskItems() {
        return null;
    }
    
    public final void setTaskItems(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LiveData<java.util.List<com.satrasia.Reminder.TaskItem>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job addTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem newTask) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job updateTaskItem(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job setCompleted(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem) {
        return null;
    }
    
    public final void deleteTask(@org.jetbrains.annotations.NotNull
    com.satrasia.Reminder.TaskItem taskItem) {
    }
}