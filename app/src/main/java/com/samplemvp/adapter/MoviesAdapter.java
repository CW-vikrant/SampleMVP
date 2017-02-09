package com.samplemvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.samplemvp.R;
import com.samplemvp.pojo.Result;

import java.util.List;

/**
 * Created by Vikrant Chauhan on 10/17/2016.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    private List<Result> mMoviesList;
    private Context mContext;
    private CallBack mCallBack;


    public MoviesAdapter(Context context, List<Result> mMoviesList){
        this.mContext = context;
        mCallBack = (CallBack) context;
        this.mMoviesList = mMoviesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false);
        return new MoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.movieTitle.setText(mMoviesList.get(position).getTitle());
        holder.data.setText(mMoviesList.get(position).getRelease_date());
        holder.movieDescription.setText(mMoviesList.get(position).getOverview());
        holder.rating.setText(mMoviesList.get(position).getVote_average().toString());
    }

    @Override
    public int getItemCount() {
        return mMoviesList !=null ? mMoviesList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;


        public ViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = mMoviesList.get(getAdapterPosition()).getId();
            mCallBack.onListItemClick(id);
        }
    }

    public interface CallBack{
        void onListItemClick(int id);
    }

}
