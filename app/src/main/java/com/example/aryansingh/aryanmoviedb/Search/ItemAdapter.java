package com.example.aryansingh.aryanmoviedb.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aryansingh.aryanmoviedb.MovieConstants;
import com.example.aryansingh.aryanmoviedb.Movies.MoviesAdaptor;
import com.example.aryansingh.aryanmoviedb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MoviesViewHolder> {

    public Context context;
    public List<Result> list;
    public ItemAdapter.MoviesClickListener listener;
//
//    boolean movie,tv,person;

    public ItemAdapter(Context context, List<Result> list, ItemAdapter.MoviesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.search_layout, parent, false);
        return new ItemAdapter.MoviesViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.MoviesViewHolder holder, int position) {

        Result searchResult = (Result) list.get(position);

        if(searchResult.getMediaType().equals("movie")){
            holder.title.setText(searchResult.getTitle());
            if(searchResult.getPosterPath()!=null)
                Picasso.with(context).load(MovieConstants.SLIDER_BASE_URL + searchResult.getPosterPath()).into(holder.backdrop);
            else{
                Picasso.with(context).load(R.drawable.noimage).into(holder.backdrop);
            }
        }
        if(searchResult.getMediaType().equals("tv")){
            holder.title.setText(searchResult.getName());
            if(searchResult.getPosterPath()!=null)
            Picasso.with(context).load(MovieConstants.SLIDER_BASE_URL + searchResult.getPosterPath()).into(holder.backdrop);
            else{
                Picasso.with(context).load(R.drawable.noimage).into(holder.backdrop);
            }
        }
        if(searchResult.getMediaType().equals("person")){
            holder.title.setText(searchResult.getName());
            if(searchResult.getProfile_path()!=null)
                Picasso.with(context).load(MovieConstants.SLIDER_BASE_URL + searchResult.getProfile_path()).into(holder.backdrop);
            else{
                Picasso.with(context).load(R.drawable.noimage).into(holder.backdrop);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface MoviesClickListener {
        void onMovieClick(View view, int position);
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView backdrop;
        TextView title;
        ItemAdapter.MoviesClickListener moviesClickListener;

        public MoviesViewHolder(View view, ItemAdapter.MoviesClickListener listener) {
            super(view);
            moviesClickListener = listener;
            backdrop = view.findViewById(R.id.backdrop);
            title = view.findViewById(R.id.title);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            moviesClickListener.onMovieClick(view, getAdapterPosition());
        }
    }
}