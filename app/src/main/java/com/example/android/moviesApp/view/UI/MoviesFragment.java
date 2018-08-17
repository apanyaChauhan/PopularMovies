package com.example.android.moviesApp.view.UI;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moviesApp.R;
import com.example.android.moviesApp.binding.FragmentDataBindingComponent;
import com.example.android.moviesApp.databinding.FragmentMoviesDetailsBinding;
import com.example.android.moviesApp.di.Injectable;
import com.example.android.moviesApp.service.Persistence.Movies;
import com.example.android.moviesApp.view.adapter.MoviesAdapter;
import com.example.android.moviesApp.view.callback.MoviesClickCallback;
import com.example.android.moviesApp.viewmodel.SharedViewModel;

import java.util.List;

import javax.inject.Inject;


public class MoviesFragment extends Fragment implements Injectable {

    private static final String KEY_Movies_ID = "Movies_id";
    private FragmentMoviesDetailsBinding binding;
    private SharedViewModel viewModel;
    private DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent        (this);



    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_details,               container, false,dataBindingComponent);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel       .class);
         int i= Integer.valueOf(getArguments().getString(KEY_Movies_ID));
         Log.d("movesId", Integer.toString(i));

        observeViewModel();
        viewModel.setId("b8b9e99d11eb150d1abc559f9056a344", "en-US", 1);
    }



    private void observeViewModel() {
        viewModel.getResults().observe(this, result -> {
            if (result != null && result.data != null) {

                List<Movies.Results> list=result.data;
                Integer position=0;
                for(int i=0;i<list.size();i++) {
                   Integer mid = Integer.parseInt(getArguments().getString(KEY_Movies_ID));

                   if (list.get(i).id.equals(mid)) {
                       position = i;
                       break;
                   }
                   }
               binding.setResults(list.get(position));
             }
             else {
                binding.setResults(null);
            }
        });
    }

    public static MoviesFragment forMovies(String MoviesID) {

        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(KEY_Movies_ID,MoviesID.toString());
        fragment.setArguments(args);
        return fragment;
    }
}



