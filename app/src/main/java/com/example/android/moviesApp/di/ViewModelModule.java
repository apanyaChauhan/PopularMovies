package com.example.android.moviesApp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.android.moviesApp.viewmodel.MoviesViewModelFactory;
import com.example.android.moviesApp.viewmodel.SharedViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module

abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel.class)
    abstract ViewModel bindSharedViewModel(SharedViewModel sharedViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MoviesViewModelFactory factory);
}
