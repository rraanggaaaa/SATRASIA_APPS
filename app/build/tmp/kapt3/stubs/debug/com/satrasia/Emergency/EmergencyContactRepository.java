package com.satrasia.Emergency;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00100\u000f2\u0006\u0010\u0007\u001a\u00020\bJ&\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/satrasia/Emergency/EmergencyContactRepository;", "", "()V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "addContact", "", "userId", "", "contact", "Lcom/satrasia/Emergency/EmergencyContact;", "(Ljava/lang/String;Lcom/satrasia/Emergency/EmergencyContact;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "contactId", "getContacts", "Lkotlinx/coroutines/flow/Flow;", "", "updateContact", "newName", "newPhone", "app_debug"})
public final class EmergencyContactRepository {
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    
    public EmergencyContactRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.satrasia.Emergency.EmergencyContact>> getContacts(@org.jetbrains.annotations.NotNull
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addContact(@org.jetbrains.annotations.NotNull
    java.lang.String userId, @org.jetbrains.annotations.NotNull
    com.satrasia.Emergency.EmergencyContact contact, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void deleteContact(@org.jetbrains.annotations.NotNull
    java.lang.String userId, @org.jetbrains.annotations.NotNull
    java.lang.String contactId) {
    }
    
    public final void updateContact(@org.jetbrains.annotations.NotNull
    java.lang.String userId, @org.jetbrains.annotations.NotNull
    java.lang.String contactId, @org.jetbrains.annotations.NotNull
    java.lang.String newName, @org.jetbrains.annotations.NotNull
    java.lang.String newPhone) {
    }
}