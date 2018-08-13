package com.example.android.moviesApp.service.repository;

import com.example.android.moviesApp.service.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String USGS_URL = "https://api.themoviedb.org/";

    @GET("/3/movie/popular")
    Call<Movies> getMoviesList(@Query("api_key") String apiKey, @Query("language") String        language, @Query("page") int page);



    //3/movie///popular?api_key=b8b9e99d11eb150d1abc559f9056a344&language=en-US&page=1";

}


