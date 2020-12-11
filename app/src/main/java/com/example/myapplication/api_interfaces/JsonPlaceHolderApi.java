package com.example.myapplication.api_interfaces;

import com.example.myapplication.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
 }
