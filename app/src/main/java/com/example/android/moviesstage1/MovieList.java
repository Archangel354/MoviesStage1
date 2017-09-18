package com.example.android.moviesstage1;

import android.widget.ImageView;

/**
 * Created by Owner on 9/17/2017.
 */

public class MovieList {

    // image for a particular movie
    private ImageView mPosterPath;

    public MovieList(ImageView PosterPath) {
        mPosterPath = PosterPath;
    }

    public ImageView getmPosterPath() {
        return mPosterPath;
    }
}
