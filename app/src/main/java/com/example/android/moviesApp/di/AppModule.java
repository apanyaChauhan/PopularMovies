
package com.example.android.moviesApp.di;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.util.Log;


import com.example.android.moviesApp.Utils.LiveDataCallAdapterFactory;
import com.example.android.moviesApp.service.Persistence.MoviesDao;
import com.example.android.moviesApp.service.Persistence.MoviesDatabase;
import com.example.android.moviesApp.service.repository.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton @Provides
    ApiInterface provideMoviesService() {
        return new Retrofit.Builder()
                .baseUrl(ApiInterface.USGS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(ApiInterface.class);
    }

   @Singleton @Provides
   MoviesDatabase provideDb(Application app) {

       final Migration MIGRATION_1_2 = new Migration(1, 2) {
           @Override
           public void migrate(SupportSQLiteDatabase database) {
               database.execSQL("ALTER TABLE movieResults "
                       + " ADD COLUMN overview TEXT");
           }
       };

       final Migration MIGRATION_2_3 = new Migration(3,4) {
           @Override
           public void migrate(SupportSQLiteDatabase database) {
               database.execSQL("ALTER TABLE movieResults "
                       + " ADD COLUMN overview TEXT");

           }
       };

       final Migration MIGRATION_4_5 = new Migration(4,5) {
           @Override
           public void migrate(SupportSQLiteDatabase database) {
               database.execSQL("ALTER TABLE movieResults "
                       + " ADD COLUMN releaseDate TEXT");

           }
       };
       return Room.databaseBuilder(app, MoviesDatabase.class,"movies.db")    .addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_4_5).build();

       }

    @Singleton @Provides
    MoviesDao provideMoviesDao(MoviesDatabase db) {
        return db.moviesDao();
    }


}
