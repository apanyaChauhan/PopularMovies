package com.example.android.moviesApp.service.repository;

import android.arch.lifecycle.LiveData;

import com.example.android.moviesApp.api.ApiResponse;
import com.example.android.moviesApp.service.Persistence.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String USGS_URL = "https://api.themoviedb.org/";

    @GET("/3/movie/popular")
    LiveData<ApiResponse<Movies>> getMoviesList(@Query("api_key") String apiKey, @Query("language") String        language, @Query("page") int page);



    //3/movie///popular?api_key=b8b9e99d11eb150d1abc559f9056a344&language=en-US&page=1";

}


