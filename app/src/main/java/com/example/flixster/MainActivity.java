package com.example.flixster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    String URL="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    String TAG = "MainActivity";

    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();
        //link the recycle view
        RecyclerView rvMovies= findViewById(R.id.rvMovies);

        //create the adapter
        MovieAdapter moviewAdapter=new MovieAdapter(this,movies);

        //set the adapter on the recycle view
        rvMovies.setAdapter(moviewAdapter);

        //set a layout manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        //get data from api
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG,"OnSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results= jsonObject.getJSONArray("results");
                    Log.i(TAG,"Result"+results.toString());

                    //save data into movies
                    movies.addAll(Movie.fromJsonArray(results));

                    //update adapter
                    moviewAdapter.notifyDataSetChanged();

                    Log.i(TAG,"size"+movies.size());
                } catch (JSONException e) {
                    Log.e(TAG,"JSON error",e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG,"OnFailure");
            }
        });


    }
}