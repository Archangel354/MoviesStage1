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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<MovieList> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MovieList> imageUrls;
    private String urlImageBaseString = "http://image.tmdb.org/t/p/w185/";
    private String completeUrlString = "";


    public MovieAdapter(Activity context, ArrayList<MovieList> movieListRecords) {
        super(context,R.layout.movie_list_items,  movieListRecords);

        this.context = context;
        this.imageUrls = movieListRecords;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        //View listItemView = convertView;
        if (convertView == null) {
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_items, parent, false);
            convertView = inflater.inflate(R.layout.movie_list_items, parent, false);
        }

        // Get the MovieRecord jpg object located at this "position" in the list
        MovieList currentMovie = getItem(position);

        TextView txtTitleView = (TextView) convertView.findViewById(R.id.txtTitle);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgPosterPath);

        // Find the TextView in the movie_list_items.xml layout with the ID txtTitleView
        //TextView txtTitleView = (TextView) listItemView.findViewById(R.id.txtTitle);
        // Get the jpg string from the current MovieRecord object and
        // set this text on the title TextView
        txtTitleView.setText(String.valueOf(currentMovie.getmPosterPath()));
       // imageView.setImageResource(currentMovie.getmPosterImage());

        completeUrlString = urlImageBaseString + txtTitleView.getText().toString();
        Log.i("LOG.MovieAdapter","The completeUrlString is: " + completeUrlString);

       Picasso
               .with(context)
               .load(completeUrlString)
               .fit()
               .into(imageView);
        return convertView;
    }
}
