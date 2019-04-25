package com.example.listapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RESTService {
    @GET("/tasks")
    Call<List<TODOTask>> getTasks();

    @POST("/tasks")
    Call<TODOTask> addTask(@Body TODOTask task);

    @PUT("/tasks/{id}")
    Call<TODOTask> updateTask(@Path("id") int id);

    @DELETE("/tasks/{id}")
    Call<TODOTask> deleteTask(@Path("id") int id);
}
