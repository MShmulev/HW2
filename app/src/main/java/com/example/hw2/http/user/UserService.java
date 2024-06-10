package com.example.hw2.http.user;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("/api")
    public Call<Root> getUsers();
}