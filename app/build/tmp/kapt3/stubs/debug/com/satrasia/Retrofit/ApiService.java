package com.satrasia.Retrofit;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/satrasia/Retrofit/ApiService;", "", "getLocation", "Lretrofit2/Call;", "Lcom/satrasia/Retrofit/LocationResponse;", "id", "", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.GET(value = "fetch_data.php?")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<com.satrasia.Retrofit.LocationResponse> getLocation(@retrofit2.http.Query(value = "id")
    int id);
}