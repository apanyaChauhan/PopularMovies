package com.example.android.moviesApp.view.adapter;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.android.moviesApp.Common.DataBoundListAdapter;
import com.example.android.moviesApp.R;
import com.example.android.moviesApp.databinding.MoviesListItemBinding;
import com.example.android.moviesApp.service.model.Movies;

import java.util.Objects;

public class MovieDetailsAdapter extends DataBoundListAdapter<Movies.Results, MoviesListItemBinding> {

    DataBindingComponent dataBindingComponent;

   public MovieDetailsAdapter(DataBindingComponent dataBindingComponent) {

       this.dataBindingComponent=dataBindingComponent;

    }


    @Override
    protected MoviesListItemBinding createBinding(ViewGroup parent) {

        MoviesListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout                                 .movies_details_item, parent, false,dataBindingComponent);

        Movies.Results movies = binding.getResults();
        return binding;
    }

    @Override
    protected void bind(MoviesListItemBinding binding, Movies.Results item) {
       binding.setResults(item);
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
