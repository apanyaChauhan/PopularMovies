package com.example.android.moviesApp.view.callback;


import com.example.android.moviesApp.service.Persistence.Movies;

public interface MoviesClickCallback {
    void onClick(Movies.Results movies);
}
