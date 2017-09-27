package com.example.android.moviesstage1;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.moviesstage1.Utils.movies;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieList>>{

    /**
     * Constant value for the movielist loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int MOVIELIST_LOADER_ID = 1;

    /** Adapter for the list of movies */
    private MovieAdapter mAdapter;
    //private ArrayList arrayList;

    private static final String TAG = MainActivity.class.getSimpleName();

    public final static String POPULARSTRING = "https://api.themoviedb.org/3/movie/popular?api_key=02ff7187d940e5bd15cd5acd2b41b63e";
    public final static String TOPRATEDSTRING = "https://api.themoviedb.org/3/movie/top_rated?api_key=02ff7187d940e5bd15cd5acd2b41b63e";
    public String urlPosterString = POPULARSTRING;
    private Button btnClear;
    private boolean firstTimeRunFlag = true;

    // Find a reference to the {@link GridView} in the layout
    public GridView movieGridView;
    //GridView movieGridView = (GridView) findViewById(R.id.movieGrid);

    private String urlImageBaseString = "https://image.tmdb.org/t/p/w185/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("LOG.MAINACTIVITY","The url is: " + POPULARSTRING);

        btnClear = (Button) findViewById(R.id.btnClear);

        movieGridView = (GridView) findViewById(R.id.movieGrid);
        //btnClear = findViewById(R.id.spnPopOrRated)

        //arrayList = new ArrayList<MovieList>();
        // Create a new adapter that takes an empty list of movies as input
        //mAdapter = new MovieAdapter(this, new ArrayList<MovieList>());
        mAdapter = new MovieAdapter(this, new ArrayList<MovieList>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        movieGridView.setAdapter(mAdapter);

        Spinner mSpinner = (Spinner) findViewById(R.id.spnPopOrRated);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,
                R.array.movie_choices, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        //getLoaderManager().restartLoader(MOVIELIST_LOADER_ID, null, this);
        GrabMovieList();

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                Log.i("setOnItemSelectedLi... ","spinner result: " + selected);

                if (( selected.contains("Most Popular"))  && (!firstTimeRunFlag)){
                    //movieGridView.setAdapter(null);
                    urlPosterString = POPULARSTRING;
                    getLoaderManager().restartLoader(MOVIELIST_LOADER_ID, null, MainActivity.this);
                    Toast.makeText(MainActivity.this,"POPULARSTRING", Toast.LENGTH_LONG).show();


                } else if (selected.contains("Highest Rated")){
                   //movieGridView.setAdapter(null);
                    urlPosterString = TOPRATEDSTRING;

                    getLoaderManager().restartLoader(MOVIELIST_LOADER_ID, null, MainActivity.this);
                    Toast.makeText(MainActivity.this,"TOPRATEDSTRING", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"Spinner error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {

               //movieGridView.setAdapter(null);
               //mAdapter.clear();
                //arrayList.clear();
              //mAdapter.getCount();
               // String value = String.valueOf(mAdapter.getCount());
                Toast.makeText(MainActivity.this,"Clear GridView", Toast.LENGTH_LONG).show();
               // mAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public Loader<List<MovieList>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
        Log.i("ONCREATELOADER... ","urlPosterString: " + urlPosterString);
        return new MovieListLoader(this, urlPosterString);
    }

    @Override
    public void onLoadFinished(Loader<List<MovieList>> loader, List<MovieList> movies) {
        // Clear the adapter of previous movie data
        mAdapter.clear();

        // If there is a valid list of books, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movies != null && !movies.isEmpty()) {
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();
            mAdapter.UpdateMovies(movies);
            mAdapter.addAll(movies);

        }

    }

    @Override
    public void onLoaderReset(Loader<List<MovieList>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }



    public void GrabMovieList(){
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(MOVIELIST_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            //View loadingIndicator = findViewById(R.id.loading_indicator);
            //loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            //mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            //bookListView.setEmptyView(mEmptyStateTextView);
            //mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


    }


}

