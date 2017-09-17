package com.example.android.moviesstage1;

import android.widget.ImageView;

/**
 * Created by Owner on 9/17/2017.
 */

public class MovieList {

    // image for a particular movie
    private String mPosterPath;

    public MovieList(String PosterPath) {
        mPosterPath = PosterPath;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }
}
