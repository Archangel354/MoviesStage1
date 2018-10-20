package com.example.android.moviesstage1.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "myfavoritesmovies")
public class MyFavoriteMoviesEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String posterPath;
    private String movieTitle;
    private String releaseDate;
    private String voteAverage;
    private String synopsis;

    @Ignore
    public MyFavoriteMoviesEntry(int id, String posterPath, String movieTitle, String releaseDate, String voteAverage, String synopsis) {
        this.id = id;
        this.posterPath = posterPath;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.synopsis = synopsis;
    }

    public MyFavoriteMoviesEntry(String posterPath, String movieTitle, String releaseDate, String voteAverage, String synopsis) {
        this.posterPath = posterPath;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.synopsis = synopsis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
