package com.example.android.moviesstage1;

/**
 * Created by Owner on 9/6/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<MovieList> {

    private Context context;
    private LayoutInflater inflater;
    private String urlImageBaseString = "https://www.google.com/url?q=http://image.tmdb.org/t/p/w185/";
    private String completeUrlString = "";


    public MovieAdapter(Activity context, ArrayList<MovieList> movieListRecords) {
        super(context,0,  movieListRecords);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_list_items, parent, false);
        }

        // Get the MovieRecord jpg object located at this "position" in the list
        MovieList currentMovie = getItem(position);

        TextView txtTitleView = (TextView) listItemView.findViewById(R.id.txtTitle);

        // Find the TextView in the movie_list_items.xml layout with the ID txtTitleView
        //TextView txtTitleView = (TextView) listItemView.findViewById(R.id.txtTitle);
        // Get the jpg string from the current MovieRecord object and
        // set this text on the title TextView
        txtTitleView.setText(String.valueOf(currentMovie.getmPosterPath()));

        completeUrlString = urlImageBaseString + txtTitleView.getText().toString();
        Log.i("LOG.MovieAdapter","The completeUrlString is: " + completeUrlString);

        return listItemView;
    }
}
