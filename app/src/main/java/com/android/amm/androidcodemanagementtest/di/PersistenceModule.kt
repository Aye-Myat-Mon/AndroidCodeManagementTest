package com.android.amm.androidcodemanagementtest.di

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.android.amm.androidcodemanagementtest.data.db.AppDatabase
import com.android.amm.androidcodemanagementtest.data.db.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

  @Provides
  @Singleton
  fun provideDatabase(context: Context): AppDatabase {
    return Room
      .databaseBuilder(context, AppDatabase::class.java, "movies.db")
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(@NonNull database: AppDatabase): MovieDao {
    return database.movieDao()
  }
}
