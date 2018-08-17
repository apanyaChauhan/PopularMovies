package com.example.android.moviesApp.service.Persistence;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = { Movies.Results.class}, version = 5,exportSchema = false)
public abstract class  MoviesDatabase extends RoomDatabase {

    abstract  public  MoviesDao moviesDao();
    }



