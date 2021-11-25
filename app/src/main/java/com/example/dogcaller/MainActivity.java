package com.example.dogcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dogcaller.models.JsonPlaceholderApi;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.dogcaller.models.Dog;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dog.ceo/api/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceholderApi service = retrofit.create(JsonPlaceholderApi.class);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Dog> dogCall = service.getRandomDog();
                dogCall.enqueue(new Callback<Dog>() {
                    @Override
                    public void onResponse(Call<Dog> call, Response<Dog> response) {
                        if (response.code() == 200) {
                            Dog currentDog = response.body();
                            ImageView imgView = findViewById(R.id.imageContainer);
                            Picasso.get().load(currentDog.getMessage()).into(imgView);
                        }
                    }
                    @Override
                    public void onFailure(Call<Dog> call, Throwable t) {
                        Log.d("FAILED","FAILED");
                    }
                });
            }
        });




    }
}