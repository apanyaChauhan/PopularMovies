package com.example.android.moviesApp.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.android.moviesApp.AppExecutors;
import com.example.android.moviesApp.Utils.Resource;
import com.example.android.moviesApp.api.ApiResponse;
import com.example.android.moviesApp.service.Persistence.MoviesDao;
import com.example.android.moviesApp.service.Persistence.MoviesDatabase;
import com.example.android.moviesApp.service.Persistence.Movies;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class MoviesRepository {
    private ApiService apiService;
    private MoviesDao moviesDao;
    public  AppExecutors appExecutors;
    private MoviesDatabase db;

    @Inject
    public MoviesRepository(AppExecutors appExecutors, MoviesDatabase db, MoviesDao moviesDao,
                          ApiService Service) {
        this.db = db;
        this.moviesDao = moviesDao;
        this.apiService = Service;
        this.appExecutors = appExecutors;
    }


public LiveData<Resource<List<Movies.Results>>> getMoviesList(String apiKey, String language, int page ) {

    Log.d("getMoviesList","response" );
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
            protected LiveData<ApiResponse<List<Movies.Results>>> createCall() {
            ApiResponse <Movies> moviesApiResponse= apiService.getMoviesList(apiKey,language,           page).getValue();

                Log.d("apiresponse","response" );

                List<Movies.Results> results=moviesApiResponse.body.getResults();

            ApiResponse<List<Movies.Results>> listApiResponse=  (ApiResponse<List<Movies                .Results>>) results;

            MutableLiveData<ApiResponse<List<Movies.Results>>> apiResponseLiveData=new                  MutableLiveData<>();

            apiResponseLiveData.setValue(listApiResponse);
            return apiResponseLiveData;

            }

            @Override
            protected void onFetchFailed() {
                return;
            }
        }.asLiveData();

      return resource;

    }


    /*public LiveData<ApiResponse<List<Movies.Results>>> getMovieList(String apiKey, String language, int page ) {
        final MutableLiveData<ApiResponse<List<Movies.Results>>> data = new MutableLiveData<>();

        apiService.getMoviesList(apiKey,language,page).enqueue(new Callback<Movies>()              {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                if (response.isSuccessful()) {

                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }*/


}
