package com.satrasia.ViewModels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0005J\u001e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/satrasia/ViewModels/ContactViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/satrasia/Emergency/EmergencyContactRepository;", "userId", "", "(Lcom/satrasia/Emergency/EmergencyContactRepository;Ljava/lang/String;)V", "contacts", "Landroidx/lifecycle/LiveData;", "", "Lcom/satrasia/Emergency/EmergencyContact;", "getContacts", "()Landroidx/lifecycle/LiveData;", "addContact", "", "name", "phone", "deleteContact", "contactId", "updateContact", "newName", "newPhone", "app_release"})
public final class ContactViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.satrasia.Emergency.EmergencyContactRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String userId = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.satrasia.Emergency.EmergencyContact>> contacts = null;
    
    public ContactViewModel(@org.jetbrains.annotations.NotNull
    com.satrasia.Emergency.EmergencyContactRepository repository, @org.jetbrains.annotations.NotNull
    java.lang.String userId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.satrasia.Emergency.EmergencyContact>> getContacts() {
        return null;
    }
    
    /**
     * Adds a new contact to the repository.
     * @param name The name of the contact.
     * @param phone The phone number of the contact.
     */
    public final void addContact(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String phone) {
    }
    
    public final void updateContact(@org.jetbrains.annotations.NotNull
    java.lang.String contactId, @org.jetbrains.annotations.NotNull
    java.lang.String newName, @org.jetbrains.annotations.NotNull
    java.lang.String newPhone) {
    }
    
    public final void deleteContact(@org.jetbrains.annotations.NotNull
    java.lang.String contactId) {
    }
}