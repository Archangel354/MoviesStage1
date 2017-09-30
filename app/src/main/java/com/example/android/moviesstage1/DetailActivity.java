package com.example.android.moviesstage1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import static com.example.android.moviesstage1.Utils.movies;

public class DetailActivity extends AppCompatActivity {

    private static final String MOVIES_SHARE_HASHTAG = " #MoviesStage1App";

    private String mMovies;
    private TextView mMovieDisplay;
    private TextView mDateDisplay;
    private TextView mVoteDisplay;
    private TextView mSynopsisDisplay;
    private TextView mPosterDisplay;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovieDisplay = (TextView) findViewById(R.id.txtTitle);
        mDateDisplay = (TextView) findViewById(R.id.txtReleaseDate);
        mVoteDisplay = (TextView) findViewById(R.id.txtVoteAverage);
        mSynopsisDisplay = (TextView) findViewById(R.id.txtSynopsis);
        mPosterDisplay = (TextView) findViewById(R.id.txtPoster);


        Intent intentThatStartedThisActivity = getIntent();
        Bundle mBundle = intentThatStartedThisActivity.getExtras();
        String mTitle = mBundle.getString("MBUNDLE_TITLE");
        mMovieDisplay.setText(mTitle);
        String mDate = mBundle.getString("MBUNDLE_DATE");
        mDateDisplay.setText(mDate);
        String mVote = mBundle.getString("MBUNDLE_VOTE");
        mVoteDisplay.setText(mVote);
        String mSynopsis = mBundle.getString("MBUNDLE_SYNOPSIS");
        mSynopsisDisplay.setText(mSynopsis);
        String mPoster = mBundle.getString("MBUNDLE_POSTER");
        //mSynopsisDisplay.setText(mPoster);



        //TextView txtPosterView = (TextView) convertView.findViewById(R.id.txtPoster);
        ImageView imageView = (ImageView) findViewById(R.id.imgPoster);
        imageView.setAdjustViewBounds(true);

        // Use the Picasso software tool to display URLs
        Picasso
                .with(context)
                .load(mPoster)
                .fit()
                .into(imageView);


        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mMovies = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                mMovieDisplay.setText(mTitle);

            }
        }

    }private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(mMovies + MOVIES_SHARE_HASHTAG)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }


}
