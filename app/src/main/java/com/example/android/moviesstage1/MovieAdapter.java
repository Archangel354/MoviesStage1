package com.example.android.moviesstage1;

/**
 * Created by Owner on 9/6/2017.
 */

import android.app.Activity;
import android.content.Context;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<MovieList> {


    public MovieAdapter(Activity context, ArrayList<MovieList> movieListRecords) {
        super(context,0,  movieListRecords);

    }

    @NonNull
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

        return listItemView;
    }
}
