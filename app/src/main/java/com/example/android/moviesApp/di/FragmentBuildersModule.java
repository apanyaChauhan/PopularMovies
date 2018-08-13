
package com.example.android.moviesApp.di;

import com.example.android.moviesApp.view.UI.MoviesFragment;
import com.example.android.moviesApp.view.UI.MoviesListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
   abstract MoviesFragment contributeMoviesFragment();

    @ContributesAndroidInjector
    abstract MoviesListFragment contributeMoviesListFragment();



}
