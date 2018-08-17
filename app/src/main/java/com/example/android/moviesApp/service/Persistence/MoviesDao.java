package com.example.android.moviesApp.service.Persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class MoviesDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   public abstract void insert(List<Movies.Results> movie);

   @Query("SELECT * FROM movieResults")
    public abstract LiveData<List<Movies.Results>> getMovies();

   @Query("DELETE FROM movieResults")
     public abstract void deleteAllMovies();


}
