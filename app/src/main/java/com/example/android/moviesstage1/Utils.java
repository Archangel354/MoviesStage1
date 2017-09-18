package com.example.android.moviesstage1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Owner on 9/10/2017.
 */

public final class Utils {

    /** Tag for the log messages */
    public static final String LOG_TAG = Utils.class.getSimpleName();

    // Create an empty ArrayList that we can start adding movies to
    static ArrayList<MovieList> movies = new ArrayList<>();

    /**
     * Query the iMdb dataset and return an {@link List} object to represent a single movie.
     */
    public static List fetchMovieData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link List} object
        List movies = extractFeatureFromJson(jsonResponse);

        // Return the {@link List}
        return movies;
    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
            * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link List} object by parsing out information
     * about the first movie from the input moviesJSON string.
     */
    private static List extractFeatureFromJson(String moviesJSON) {
        String authors = "";
        String publisher = "";
        String posterBaseString = "https://image.tmdb.org/t/p/w185/";
        JSONArray featureArray = null;

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(moviesJSON)) {
            return null;
        }
        try {
            JSONObject baseJsonResponse = new JSONObject(moviesJSON);

            // Checking if "items" is present
            if (baseJsonResponse.has("results")) {
                featureArray = baseJsonResponse.getJSONArray("results");

                Log.i("INSIDE Utils.java", "inside if has results");
            } else
            {
                // Built placeholder JSON string in case "items" not found
                String placeholderJSON = "{\n" +
                        " \"kind\": \"movies#volumes\",\n" +
                        " \"totalItems\": 2135,\n" +
                        " \"items\": [\n" +
                        "  {\n" +
                        "   \"kind\": \"movies#volume\",\n" +
                        "   \"id\": \"9e9Kn9N8yP0C\",\n" +
                        "   \"etag\": \"fyWDBegzDw0\",\n" +
                        "   \"selfLink\": \"https://www.googleapis.com/movies/v1/volumes/9e9Kn9N8yP0C\",\n" +
                        "   \"volumeInfo\": {\n" +
                        "    \"title\": \"No items found\",\n" +
                        "    \"authors\": [\n" +
                        "     \"No items found\"" +
                        "    ],\n" +
                        "    \"publisher\": \"\\\"No items found\\\"\",\n" +
                        "    \"publishedDate\": \"No items found\"\n" +
                        "   }\n" +
                        "\n" +
                        "  }\n" +
                        " ]\n" +
                        "}";
                baseJsonResponse = new JSONObject(placeholderJSON);
                featureArray = baseJsonResponse.getJSONArray("items");
            }

            for (int i = 0;i < featureArray.length();i++){
                JSONObject currentMovie = featureArray.getJSONObject(i);
                String poster_path = currentMovie.getString("poster_path");
                String posterUrlString = posterBaseString + poster_path;
                Log.i("UTILS","The posterUrlString is: " + posterUrlString);

                //https://www.google.com/url?q=http://image.tmdb.org/t/p/w185/
                //String poster_path = properties.getString("poster_path");

                MovieList mMovieList = new MovieList(posterUrlString);

                movies.add(mMovieList);

            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the mMovieList JSON results", e);

        }

        return movies;
    }




}
