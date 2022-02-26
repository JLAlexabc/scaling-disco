package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.lang.annotation.Target;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    //usually involves inflating a layout from xml and returning the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }

    @Override
    //involves populating data into the item through holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get movie at the postiton
        Movie movie=movies.get(position);
        //bing movie data into viewHolder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverView;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverView = itemView.findViewById(R.id.tvOverView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            String imageURL;
            String overView;
            tvTitle.setText(movie.getTitle());
//            Log.d("OverView",movie.getOverView());

            if(movie.getOverView().length()>230){
                overView = movie.getOverView().substring(0,229)+"...";
            }else{
                overView = movie.getOverView();
            }
            tvOverView.setText(overView);
//            Log.d("imgURL",movie.getPosterPath());
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageURL = movie.getBackdropPath();
            }else{
                imageURL = movie.getPosterPath();
            }

            Glide.with(context)
                    .load(imageURL)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivPoster);
        }
    }
}
