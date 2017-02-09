package com.samplemvp.mvp;

import com.samplemvp.pojo.MovieDetails;
import com.samplemvp.pojo.Result;

import java.util.ArrayList;

/**
 * Created by Vikrant Chauhan on 10/26/2016.
 */

public class MoviePresenter implements MovieListContract.Presenter {

    private MovieListContract.View mView;
    private MovieListContract.DataModel model;

    public MoviePresenter(MovieListContract.View mView) {
        this.mView = mView;
        initializeModel();
    }

    /*
    initialize model and request for movie list
     */
    private void initializeModel(){
        model = new MovieModel(MoviePresenter.this);
    }

    @Override
    public void requestMovieList() {
        model.getMovieList();
    }

    @Override
    public void requestMovieDetail(int id) {
        mView.createDialog();
        model.getMovieDetail(id);
    }

    /*
     update view with movie list
     */
    @Override
    public void onListReceived(ArrayList<Result> result) {
        mView.showList(result);
    }

    /*
    update view with list failure message
     */
    @Override
    public void onFailure(String message) {
        mView.showMessage(message);
    }

    /*
    update view with movie detail object
     */
    @Override
    public void onDetailsReceived(MovieDetails details) {
        mView.updateDialog(details);
    }

    /*
    update view with detail failure message
     */
    @Override
    public void onDetailFailure(String message) {
        mView.showMessage(message);
    }
}
