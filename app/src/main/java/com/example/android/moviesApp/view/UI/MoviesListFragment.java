package com.example.android.moviesApp.view.UI;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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
import com.example.android.moviesApp.Utils.AutoClearedValue;
import com.example.android.moviesApp.binding.FragmentDataBindingComponent;
import com.example.android.moviesApp.databinding.FragmentMoviesListBinding;
import com.example.android.moviesApp.di.Injectable;
import com.example.android.moviesApp.service.model.Movies;
import com.example.android.moviesApp.view.adapter.MoviesAdapter;
import com.example.android.moviesApp.view.callback.MoviesClickCallback;
import com.example.android.moviesApp.viewmodel.SharedViewModel;

import java.util.Collections;

import javax.inject.Inject;

public class MoviesListFragment extends Fragment implements Injectable {

    private FragmentMoviesListBinding binding;
    private SharedViewModel viewModel;
    private DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    AutoClearedValue<MoviesAdapter> adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel.class);
        initRecyclerView();
        MoviesAdapter rvAdapter = new MoviesAdapter(dataBindingComponent,
                moviesClickCallback);
        binding.moviesList.setAdapter(rvAdapter);
        adapter = new AutoClearedValue<>(this, rvAdapter);
        viewModel.setId("b8b9e99d11eb150d1abc559f9056a344", "en-US", 1);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false, dataBindingComponent);
        binding.setRetryCallback(() -> viewModel.retry());
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    private void initRecyclerView() {
        viewModel.getResults().observe(this, result -> {
            if (result != null && result.data != null) {
                binding.setListResource(result);
                adapter.get().replace(result.data);
            } else {
                adapter.get().replace(Collections.emptyList());
            }
        });
    }

    private final MoviesClickCallback moviesClickCallback = new MoviesClickCallback() {
        @Override
        public void onClick(Movies.Results movies) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(movies);
            }
        }
    };


}

