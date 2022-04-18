package com.android.amm.androidcodemanagementtest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.amm.androidcodemanagementtest.models.MovieModel


@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieModel WHERE isPopular = :isPopular")
    fun getMovies(isPopular: Boolean): List<MovieModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movieModel: List<MovieModel>)

    @Query("UPDATE MovieModel SET isFavourite=:isFavourite WHERE id = :id")
    fun updateMovie(isFavourite: Boolean, id: String)

    @Query("UPDATE MovieModel SET  isPopular=:isPopular AND isUpcoming=:isUpcoming WHERE id = :id")
    fun updateExistingMovie(isPopular: Boolean, isUpcoming: Boolean, id: String)

    @Query("DELETE FROM MovieModel WHERE isPopular = :isPopular")
    fun deleteMovies(isPopular: Boolean)
}