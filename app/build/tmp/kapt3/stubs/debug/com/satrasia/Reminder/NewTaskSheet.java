package com.satrasia.Reminder;

/**
 * NewTaskSheet mengelola tampilan dialog untuk menambahkan atau mengedit tugas dengan opsi pengaturan pengingat waktu.
 * Dialog ini memungkinkan pengguna untuk menginput atau mengedit informasi tugas dan menyimpannya ke dalam ViewModel.
 * Pengguna juga dapat mengatur alarm untuk tugas jika diperlukan.
 *
 * @param taskItem Item tugas yang bisa null, digunakan untuk mengisi ulang data tugas dalam mode edit.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0002J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001a\u001a\u00020\u000fH\u0002J \u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0003J\b\u0010 \u001a\u00020\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/satrasia/Reminder/NewTaskSheet;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "taskItem", "Lcom/satrasia/Reminder/TaskItem;", "(Lcom/satrasia/Reminder/TaskItem;)V", "binding", "Lcom/satrasia/databinding/FragmentNewTaskSheetBinding;", "dueTime", "Ljava/time/LocalTime;", "getTaskItem", "()Lcom/satrasia/Reminder/TaskItem;", "setTaskItem", "taskViewModel", "Lcom/satrasia/ViewModels/TaskViewModel;", "cancelAction", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "saveAction", "scheduleAlarm", "time", "name", "", "description", "updateTimeButtonText", "app_debug"})
public final class NewTaskSheet extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @org.jetbrains.annotations.Nullable
    private com.satrasia.Reminder.TaskItem taskItem;
    private com.satrasia.databinding.FragmentNewTaskSheetBinding binding;
    private com.satrasia.ViewModels.TaskViewModel taskViewModel;
    @org.jetbrains.annotations.Nullable
    private java.time.LocalTime dueTime;
    
    public NewTaskSheet(@org.jetbrains.annotations.Nullable
    com.satrasia.Reminder.TaskItem taskItem) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.satrasia.Reminder.TaskItem getTaskItem() {
        return null;
    }
    
    public final void setTaskItem(@org.jetbrains.annotations.Nullable
    com.satrasia.Reminder.TaskItem p0) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateTimeButtonText() {
    }
    
    private final void saveAction() {
    }
    
    private final void cancelAction() {
    }
    
    @android.annotation.SuppressLint(value = {"UnspecifiedImmutableFlag", "ScheduleExactAlarm"})
    private final void scheduleAlarm(java.time.LocalTime time, java.lang.String name, java.lang.String description) {
    }
}