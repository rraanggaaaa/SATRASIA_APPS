package com.satrasia;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\nH\u0002J0\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/satrasia/Register;", "Landroidx/activity/ComponentActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "buttonLogin", "Landroid/widget/Button;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "clearRegistrationSession", "", "isValidPassword", "", "password", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "registerUser", "saveUserData", "userId", "name", "email", "phoneNumber", "app_release"})
public final class Register extends androidx.activity.ComponentActivity {
    private android.widget.Button buttonLogin;
    private com.google.firebase.auth.FirebaseAuth auth;
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    
    public Register() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void registerUser() {
    }
    
    private final void saveUserData(java.lang.String userId, java.lang.String name, java.lang.String email, java.lang.String password, java.lang.String phoneNumber) {
    }
    
    private final boolean isValidPassword(java.lang.String password) {
        return false;
    }
    
    private final void clearRegistrationSession() {
    }
}