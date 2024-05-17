package com.satrasia;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0014\u0010\u0013\u001a\u00020\u00122\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/satrasia/Setting;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnAccountManager", "Landroid/widget/LinearLayout;", "btnContact", "btnDeveloper", "btnLogout", "Landroid/widget/Button;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "etEmail", "Landroid/widget/TextView;", "etName", "etNoTelp", "navView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "displayFirestoreUserInfo", "", "navigateTo", "destination", "Ljava/lang/Class;", "navigateToMain", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupBottomNavigationView", "setupLogoutButton", "app_release"})
public final class Setting extends androidx.appcompat.app.AppCompatActivity {
    private com.google.android.material.bottomnavigation.BottomNavigationView navView;
    private android.widget.Button btnLogout;
    private android.widget.TextView etName;
    private android.widget.TextView etEmail;
    private android.widget.TextView etNoTelp;
    private android.widget.LinearLayout btnContact;
    private android.widget.LinearLayout btnAccountManager;
    private android.widget.LinearLayout btnDeveloper;
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    
    public Setting() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupBottomNavigationView() {
    }
    
    private final void navigateTo(java.lang.Class<?> destination) {
    }
    
    private final void displayFirestoreUserInfo() {
    }
    
    private final void setupLogoutButton() {
    }
    
    private final void navigateToMain() {
    }
}