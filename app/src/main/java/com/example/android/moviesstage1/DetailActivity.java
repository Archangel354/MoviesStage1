package com.example.android.moviesstage1;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.moviesstage1.Utils.movies;

public class DetailActivity extends AppCompatActivity {

    private static final String MOVIES_SHARE_HASHTAG = " #MoviesStage1App";

    private String mMovies;
    private TextView mMovieDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovieDisplay = (TextView) findViewById(R.id.txtTitle);
        Intent intentThatStartedThisActivity = getIntent();
        Bundle mBundle = intentThatStartedThisActivity.getExtras();
        String mTitle = mBundle.getString("MBUNDLE_TITLE");
        mMovieDisplay.setText(mTitle);

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mMovies = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                mMovieDisplay.setText(mTitle);
                Toast.makeText(DetailActivity.this,"the title over here is: " + mTitle, Toast.LENGTH_LONG).show();
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
