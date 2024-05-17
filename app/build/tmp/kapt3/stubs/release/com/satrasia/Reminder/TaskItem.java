package com.satrasia.Reminder;

/**
 * Representasi entitas 'TaskItem' untuk tabel dalam Room Database yang menyimpan tugas pengingat.
 *
 * @param name Nama tugas yang harus dilakukan.
 * @param desc Deskripsi tugas.
 * @param dueTimeString Waktu jatuh tempo tugas sebagai string, bisa null jika tidak ditetapkan.
 * @param completedDateString Tanggal penyelesaian tugas sebagai string, bisa null jika tugas belum selesai.
 * @param id Primary key yang dihasilkan otomatis untuk identifikasi unik setiap tugas.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010 \u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010!\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\"\u001a\u00020\bJ\u0006\u0010#\u001a\u00020\bJ\u0006\u0010$\u001a\u00020%R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r\u00a8\u0006\'"}, d2 = {"Lcom/satrasia/Reminder/TaskItem;", "", "name", "", "desc", "dueTimeString", "completedDateString", "id", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getCompletedDateString", "()Ljava/lang/String;", "setCompletedDateString", "(Ljava/lang/String;)V", "getDesc", "setDesc", "getDueTimeString", "setDueTimeString", "getId", "()I", "setId", "(I)V", "getName", "setName", "black", "context", "Landroid/content/Context;", "completedDate", "Ljava/time/LocalDate;", "dueTime", "Ljava/time/LocalTime;", "green", "imageColor", "imageColorUncompleted", "imageResource", "imageResourceUncompleted", "isCompleted", "", "Companion", "app_release"})
@androidx.room.Entity(tableName = "reminder_table")
public final class TaskItem {
    @androidx.room.ColumnInfo(name = "name")
    @org.jetbrains.annotations.NotNull
    private java.lang.String name;
    @androidx.room.ColumnInfo(name = "desc")
    @org.jetbrains.annotations.NotNull
    private java.lang.String desc;
    @androidx.room.ColumnInfo(name = "dueTimeString")
    @org.jetbrains.annotations.Nullable
    private java.lang.String dueTimeString;
    @androidx.room.ColumnInfo(name = "completedDateString")
    @org.jetbrains.annotations.Nullable
    private java.lang.String completedDateString;
    @androidx.room.PrimaryKey(autoGenerate = true)
    private int id;
    
    /**
     * Formatter waktu untuk standarisasi format string waktu ISO.
     */
    @org.jetbrains.annotations.NotNull
    private static final java.time.format.DateTimeFormatter timeFormatter = null;
    
    /**
     * Formatter tanggal untuk standarisasi format string tanggal ISO.
     */
    @org.jetbrains.annotations.NotNull
    private static final java.time.format.DateTimeFormatter dateFormatter = null;
    @org.jetbrains.annotations.NotNull
    public static final com.satrasia.Reminder.TaskItem.Companion Companion = null;
    
    public TaskItem(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String desc, @org.jetbrains.annotations.Nullable
    java.lang.String dueTimeString, @org.jetbrains.annotations.Nullable
    java.lang.String completedDateString, int id) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDesc() {
        return null;
    }
    
    public final void setDesc(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDueTimeString() {
        return null;
    }
    
    public final void setDueTimeString(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCompletedDateString() {
        return null;
    }
    
    public final void setCompletedDateString(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final int getId() {
        return 0;
    }
    
    public final void setId(int p0) {
    }
    
    /**
     * Mengembalikan tanggal penyelesaian tugas jika ada, null jika belum diselesaikan.
     */
    @org.jetbrains.annotations.Nullable
    public final java.time.LocalDate completedDate() {
        return null;
    }
    
    /**
     * Mengembalikan waktu jatuh tempo tugas jika ada, null jika tidak ditetapkan.
     */
    @org.jetbrains.annotations.Nullable
    public final java.time.LocalTime dueTime() {
        return null;
    }
    
    /**
     * Menentukan apakah tugas telah selesai.
     */
    public final boolean isCompleted() {
        return false;
    }
    
    /**
     * Mengembalikan resource gambar yang sesuai dengan status penyelesaian tugas.
     */
    public final int imageResource() {
        return 0;
    }
    
    /**
     * Mengembalikan resource gambar yang sesuai untuk tugas yang belum selesai.
     */
    public final int imageResourceUncompleted() {
        return 0;
    }
    
    /**
     * Mengembalikan warna gambar berdasarkan status penyelesaian tugas.
     */
    public final int imageColor(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return 0;
    }
    
    /**
     * Mengembalikan warna gambar yang sesuai untuk tugas yang belum selesai.
     */
    public final int imageColorUncompleted(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return 0;
    }
    
    /**
     * Mengembalikan warna hijau dari resources.
     */
    private final int green(android.content.Context context) {
        return 0;
    }
    
    /**
     * Mengembalikan warna hitam dari resources.
     */
    private final int black(android.content.Context context) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/satrasia/Reminder/TaskItem$Companion;", "", "()V", "dateFormatter", "Ljava/time/format/DateTimeFormatter;", "getDateFormatter", "()Ljava/time/format/DateTimeFormatter;", "timeFormatter", "getTimeFormatter", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Formatter waktu untuk standarisasi format string waktu ISO.
         */
        @org.jetbrains.annotations.NotNull
        public final java.time.format.DateTimeFormatter getTimeFormatter() {
            return null;
        }
        
        /**
         * Formatter tanggal untuk standarisasi format string tanggal ISO.
         */
        @org.jetbrains.annotations.NotNull
        public final java.time.format.DateTimeFormatter getDateFormatter() {
            return null;
        }
    }
}