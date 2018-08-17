package com.example.android.moviesApp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.android.moviesApp.Utils.Resource;
import com.example.android.moviesApp.service.Persistence.Movies;
import com.example.android.moviesApp.service.repository.MoviesRepository;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class SharedViewModel extends ViewModel {
    public LiveData<Resource<List<Movies.Results>>> moviesListObservable;
    private MutableLiveData<MoviesId> moviesId = new MutableLiveData<>();
    private LiveData<Resource<List<Movies.Results>>> results;


    @Inject
    public SharedViewModel(MoviesRepository moviesRepository) {
        results = Transformations.switchMap(moviesId, movie -> {
            if (movie.isEmpty()) {
                return null;
            } else {
                return moviesRepository.getMoviesList(movie.api_Key, movie.lang, movie.page);
            }
        });
    }

    public SharedViewModel() {

    }



    public LiveData<Resource<List<Movies.Results>>> getResults() {
        return results;
    }


    public void setId(String apiKey, String lang, int page) {
        MoviesId update = new MoviesId(apiKey, lang, page);
        if (Objects.equals(moviesId.getValue(), update)) {
            return;
        }
        moviesId.setValue(update);
    }

    static class MoviesId {
        public String api_Key;
        public String lang;
        public int page;

        MoviesId(String api_Key, String lang, int page) {
            this.api_Key = api_Key;
            this.lang = lang;
            this.page = page;
        }

        boolean isEmpty() {
            return api_Key == null;
        }
    }

    public void retry() {
        if (this.moviesListObservable.getValue() != null) {
        }
    }
}
