package com.example.android.moviesstage1;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieList>>{

    /**
     * Constant value for the movielist loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int MOVIELIST_LOADER_ID = 1;

    /** Adapter for the list of movies */
    private MovieAdapter mAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    private String urlString = "https://api.themoviedb.org/3/movie/popular?api_key=02ff7187d940e5bd15cd5acd2b41b63e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("LOG.MAINACTIVITY","The url is: " + urlString);

        // Find a reference to the {@link ListView} in the layout
        ListView movieListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of BookList as input
        mAdapter = new MovieAdapter(this, new ArrayList<MovieList>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        movieListView.setAdapter(mAdapter);

    }

    @Override
    public Loader<List<MovieList>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<MovieList>> loader, List<MovieList> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<MovieList>> loader) {

    }
}
