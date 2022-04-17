package com.android.amm.androidcodemanagementtest.data.db

import androidx.room.*
import com.android.amm.androidcodemanagementtest.models.MovieModel

/**
 * Created by ayemyatmon on 17,April,2022
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieModel WHERE isPopular = :isPopular")
    fun getMovies(isPopular: Boolean): List<MovieModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movieModel: List<MovieModel>)

    @Query("UPDATE MovieModel SET isFavourite=:isFavourite WHERE id = :id")
    fun updateMovie(isFavourite: Boolean, id: String)

    @Query("DELETE FROM MovieModel WHERE isPopular = :isPopular")
    fun deleteMovies(isPopular: Boolean)
}