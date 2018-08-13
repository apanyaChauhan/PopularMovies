package com.example.android.moviesApp.view.UI;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moviesApp.R;
import com.example.android.moviesApp.Utils.AutoClearedValue;
import com.example.android.moviesApp.binding.FragmentDataBindingComponent;
import com.example.android.moviesApp.databinding.FragmentMoviesDetailsBinding;
import com.example.android.moviesApp.di.Injectable;
import com.example.android.moviesApp.service.model.Movies;
//import com.example.android.moviesApp.view.adapter.MovieDetailsAdapter;
import com.example.android.moviesApp.view.callback.MoviesClickCallback;
import com.example.android.moviesApp.viewmodel.SharedViewModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;


public class MoviesFragment extends Fragment implements Injectable {

    private static final String KEY_Movies_ID = "Movies_id";
    private FragmentMoviesDetailsBinding binding;
    private SharedViewModel viewModel;
    private DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private int Movie_ID;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    //AutoClearedValue<MovieDetailsAdapter> adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_details, container, false,dataBindingComponent);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel       .class);

        observeViewModel();
        //MovieDetailsAdapter rvAdapter = new MovieDetailsAdapter(dataBindingComponent);
        //binding.moviesDetail.setAdapter(rvAdapter);
      //  adapter = new AutoClearedValue<>(this, rvAdapter);
        viewModel.setId("b8b9e99d11eb150d1abc559f9056a344", "en-US", 1);
    }


    private void observeViewModel() {

        viewModel.getResults().observe(this, result -> {
            if (result != null && result.data != null) {
                List<Movies.Results> list=result.data;
                for(int i=0;i<list.size();i++){
                   if(Integer.valueOf(getArguments().getString(KEY_Movies_ID))==list.get(i).id);                    binding.setResults(list.get(i));
                }
            } else {
                binding.setResults(null);
            }
        });
    }

    public static MoviesFragment forMovies(String MoviesID) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(KEY_Movies_ID, MoviesID);
        fragment.setArguments(args);
        return fragment;
    }
}



