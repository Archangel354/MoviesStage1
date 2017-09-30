package com.example.android.moviesstage1;

import android.widget.ImageView;

/**
 * Created by Owner on 9/17/2017.
 */

public class MovieList {

    // image for a particular movie
    private String mPosterPath;
    private String mMovieTitle;

    public MovieList(String PosterPath, String MovieTitle) {
        mPosterPath = PosterPath;
        mMovieTitle = MovieTitle;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmMovieTitle() {
        return mMovieTitle;
    }
}
