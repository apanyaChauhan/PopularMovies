package com.example.android.moviesApp.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.moviesApp.Common.DataBoundListAdapter;
import com.example.android.moviesApp.R;
import com.example.android.moviesApp.databinding.MoviesListItemBinding;
import com.example.android.moviesApp.service.Persistence.Movies;
import com.example.android.moviesApp.view.callback.MoviesClickCallback;

import java.util.Objects;

public class MoviesAdapter extends DataBoundListAdapter<Movies.Results, MoviesListItemBinding> {


    private final MoviesClickCallback moviesClickCallback;
    DataBindingComponent dataBindingComponent;

    public MoviesAdapter(DataBindingComponent dataBindingComponent, MoviesClickCallback             moviesClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.moviesClickCallback = moviesClickCallback;
    }


    @Override
    protected MoviesListItemBinding createBinding(ViewGroup parent) {
        MoviesListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.movies_list_item,
                        parent, false, dataBindingComponent);
        return binding;
    }

    @Override
    protected void bind(MoviesListItemBinding binding, Movies.Results item) {

        binding.setResults(item);
        binding.getRoot().setOnClickListener(v -> {
            if (item != null && moviesClickCallback != null) {
                moviesClickCallback.onClick(item); }
        });

        binding.setCallback(moviesClickCallback);

        }

    @Override
    protected boolean areItemsTheSame(Movies.Results oldItem, Movies.Results newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());

    }

    @Override
    protected boolean areContentsTheSame(Movies.Results oldItem, Movies.Results newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());

    }


}
