package com.example.android.moviesstage1.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface MyFavoriteMoviesDao {

    @Query("SELECT * FROM myfavoritesmovies")
    LiveData<List<MyFavoriteMoviesEntry>> loadAllfavoriteMovies();

    @Insert
    void insertMyFavortieMovie(MyFavoriteMoviesEntry myFavoriteMoviesEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMyFavoriteMovies(MyFavoriteMoviesEntry myFavoriteMoviesEntry);

    @Delete
    void deleteMyFavoriteMoviesEntry(MyFavoriteMoviesEntry myFavoriteMoviesEntry);

    @Query("SELECT * FROM myfavoritesmovies WHERE id = :id")
    LiveData<MyFavoriteMoviesEntry> loadMovieById(int id);
}
