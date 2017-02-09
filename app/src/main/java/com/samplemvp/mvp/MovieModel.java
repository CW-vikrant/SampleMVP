package com.samplemvp.mvp;

import android.view.View;

import com.samplemvp.SampleApplication;
import com.samplemvp.pojo.MovieDetails;
import com.samplemvp.pojo.MovieResponse;
import com.samplemvp.rest.ApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Vikrant Chauhan on 10/26/2016.
 */

public class MovieModel implements MovieListContract.DataModel {

    @Inject Retrofit mClient;
    MovieListContract.Presenter mPresenter;

    public MovieModel(MovieListContract.Presenter mPresenter){
        this.mPresenter = mPresenter;
        SampleApplication.getInstance().getAppComponent().inject(this);
    }

    /*
    download movie list and send callback to presenter
     */
    @Override public void getMovieList() {
        Call<MovieResponse> call =
                mClient.create(ApiInterface.class).getTopRatedMovie(SampleApplication.API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mPresenter.onListReceived(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mPresenter.onFailure(t.getMessage());
            }
        });
    }

    /*
    download movie detail and send callback to presenter
     */
    @Override
    public void getMovieDetail(int id) {
        Call<MovieDetails> call = mClient.create(ApiInterface.class)
                .getMovieDetail(id, SampleApplication.API_KEY);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                mPresenter.onDetailsReceived(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                mPresenter.onDetailFailure(t.getMessage());
            }
        });
    }
}
