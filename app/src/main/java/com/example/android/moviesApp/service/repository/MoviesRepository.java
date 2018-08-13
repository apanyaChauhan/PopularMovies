package com.example.android.moviesApp.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.example.android.moviesApp.AppExecutors;
import com.example.android.moviesApp.Utils.Resource;
import com.example.android.moviesApp.service.Persistence.MoviesDao;
import com.example.android.moviesApp.service.Persistence.MoviesDatabase;
import com.example.android.moviesApp.service.model.Movies;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class MoviesRepository {
    private ApiInterface apiInterface;
    private MoviesDao moviesDao;
    public  AppExecutors appExecutors;
    private MoviesDatabase db;

    @Inject
    public MoviesRepository(AppExecutors appExecutors, MoviesDatabase db, MoviesDao moviesDao,
                          ApiInterface Service) {
        this.db = db;
        this.moviesDao = moviesDao;
        this.apiInterface = Service;
        this.appExecutors = appExecutors;
    }


public LiveData<Resource<List<Movies.Results>>> getMoviesList(String apiKey, String language, int page ) {


       LiveData<Resource<List<Movies.Results>>> resource=new                                          NetworkBoundResource<List<Movies.Results>, List<Movies.Results>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<Movies.Results> item) {
               moviesDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movies.Results> data) {
              return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Movies.Results>> loadFromDb() {
               return moviesDao.getMovies();
            }

            @NonNull
            @Override
            protected LiveData<List<Movies.Results>> createCall() {
                return getMovieList(apiKey, language, page);
            }

            @Override
            protected void onFetchFailed() {
                return;
            }
        }.asLiveData();

      return resource;

    }


    public LiveData<List<Movies.Results>> getMovieList(String apiKey, String language, int page ) {
        final MutableLiveData<List<Movies.Results>> data = new MutableLiveData<>();

        apiInterface.getMoviesList(apiKey,language,page).enqueue(new Callback<Movies>()              {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                if (response.isSuccessful()) {
                    Movies movies1 = response.body();
                    data.setValue(movies1.getResults());
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


}
