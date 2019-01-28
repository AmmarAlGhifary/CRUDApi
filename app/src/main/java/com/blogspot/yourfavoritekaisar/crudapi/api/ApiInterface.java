package com.blogspot.yourfavoritekaisar.crudapi.api;

import com.blogspot.yourfavoritekaisar.crudapi.model.Detail.ResponseDetailUser;
import com.blogspot.yourfavoritekaisar.crudapi.model.Login.LoginBody;
import com.blogspot.yourfavoritekaisar.crudapi.model.Login.LoginResponse;
import com.blogspot.yourfavoritekaisar.crudapi.model.User.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // TODO Membuat Login
    @POST("/api/login")
    Call<LoginResponse> postLogin(@Body LoginBody loginBody);

    // Membuat request Get Data User
    @GET("/api/users")
    Call<UserResponse> getUser(
            @Query("per_page") int per_page
    );

    @GET("/api/users/{id}")
    Call<ResponseDetailUser> getDetailUser(@Path("id")int id);
}
