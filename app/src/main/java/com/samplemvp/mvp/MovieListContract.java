package com.samplemvp.mvp;

import com.samplemvp.pojo.MovieDetails;
import com.samplemvp.pojo.Result;

import java.util.ArrayList;

/**
 * Created by Vikrant Chauhan on 10/26/2016.
 */

public class MovieListContract {
    public interface View{
        void showList(ArrayList<Result> result);
        void showMessage(String message);
        void createDialog();
        void updateDialog(MovieDetails details);
    }

    public interface Presenter {
        void requestMovieList();
        void requestMovieDetail(int id);
        void onListReceived(ArrayList<Result> result);
        void onFailure(String message);
        void onDetailsReceived(MovieDetails details);
        void onDetailFailure(String message);
    }

    public interface DataModel{
        void getMovieList();
        void getMovieDetail(int id);
    }
}
