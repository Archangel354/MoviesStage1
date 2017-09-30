package com.example.android.moviesstage1;

import android.widget.ImageView;

/**
 * Created by Owner on 9/17/2017.
 */

public class MovieList {

    // image for a particular movie
    private String mPosterPath;
    private String mMovieTitle;
    private String mReleaseDate;
    private String mVoteAverage;
    private String mSynopsis;

    public MovieList(String PosterPath, String MovieTitle, String ReleaseDate, String VoteAverage, String Synopsis) {
        mPosterPath = PosterPath;
        mMovieTitle = MovieTitle;
        mReleaseDate = ReleaseDate;
        mVoteAverage = VoteAverage;
        mSynopsis = Synopsis;
    }


    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmMovieTitle() {
        return mMovieTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }
}
