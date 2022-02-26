package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String title;
    String overView;
    String backdropPath;
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title= jsonObject.getString("title");
        overView=jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
    }



    public static List<Movie> fromJsonArray(JSONArray data) throws JSONException {
        List<Movie> movies=new ArrayList<>();
        for(int i=0;i<data.length();i++){
            Movie temp = new Movie(data.getJSONObject(i));
            movies.add(temp);
        }

        return movies;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342"+backdropPath);
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w780"+posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }
}
