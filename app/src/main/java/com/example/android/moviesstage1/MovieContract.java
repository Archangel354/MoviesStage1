package com.example.android.moviesstage1;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Owner on 9/28/2017.
 */

public final class MovieContract {
    // To prevent someone from accidentally instatiating the contract class,
    // give it an empty constructor.
    private MovieContract() {}

    /**
            * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.moviesstage1";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/moviesstage1/ is a valid path for
     * looking at movie data. */

    public static final String PATH_MOVIES = "moviesstage1";

    public final static String _ID = BaseColumns._ID;
    public final static String COLUMN_MOVIE_TITLE ="title";
    public final static String COLUMN_MOVIE_RELEASE_DATE ="releasedate";
    public final static String COLUMN_MOVIE_POSTER_IMAGE ="image";
    public final static String COLUMN_MOVIE_VOTE_AVERAGE ="vote";
    public final static String COLUMN_MOVIE_SYNOPSIS ="synopsis";
}
