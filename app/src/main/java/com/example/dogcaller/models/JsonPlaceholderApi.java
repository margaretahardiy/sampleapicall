package com.example.dogcaller.models;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.Call;

public interface JsonPlaceholderApi {

@GET("breeds/image/random")
    Call<Dog> getRandomDog();
}
