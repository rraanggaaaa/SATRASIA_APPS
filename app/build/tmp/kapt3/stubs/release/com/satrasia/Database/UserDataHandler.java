package com.satrasia.Database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/satrasia/Database/UserDataHandler;", "", "()V", "Companion", "app_release"})
public final class UserDataHandler {
    @org.jetbrains.annotations.NotNull
    private static final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull
    public static final com.satrasia.Database.UserDataHandler.Companion Companion = null;
    
    public UserDataHandler() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/satrasia/Database/UserDataHandler$Companion;", "", "()V", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "checkEmailExists", "", "email", "", "callback", "Lkotlin/Function1;", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void checkEmailExists(@org.jetbrains.annotations.NotNull
        java.lang.String email, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback) {
        }
    }
}