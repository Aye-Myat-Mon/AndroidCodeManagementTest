package com.android.amm.androidcodemanagementtest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.amm.androidcodemanagementtest.models.MovieModel

@Database(entities = [MovieModel::class], version = 1)
@TypeConverters(MovieTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}