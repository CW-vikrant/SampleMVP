package com.samplemvp.activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samplemvp.mvp.MovieListContract;
import com.samplemvp.mvp.MoviePresenter;
import com.samplemvp.R;
import com.samplemvp.adapter.MoviesAdapter;
import com.samplemvp.pojo.MovieDetails;
import com.samplemvp.pojo.Result;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements MovieListContract.View,MoviesAdapter.CallBack {

    MoviesAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar pb, pb_dialog;
    MovieListContract.Presenter mPresenter;
    Dialog d;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        mPresenter = new MoviePresenter(HomeActivity.this);
        mPresenter.requestMovieList();
    }

    /*
    initialize all view objects here
     */
    public void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.retrofit_rv);
        pb = (ProgressBar) findViewById(R.id.pb);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
    }


    /*
    display downloaded list in recycler view
     */
    @Override
    public void showList(ArrayList<Result> result) {
        pb.setVisibility(View.GONE);
        adapter = new MoviesAdapter(HomeActivity.this,result);
        recyclerView.setAdapter(adapter);
    }

    /*
    display error message on failure
     */
    @Override
    public void showMessage(String message) {
        pb.setVisibility(View.GONE);
        Toast.makeText(HomeActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    /*
    create dialog when user clicks on recyclerview item
     */
    @Override
    public void createDialog() {
        d = new Dialog(HomeActivity.this);
        d.setContentView(R.layout.dialog);
        tv = (TextView) d.findViewById(R.id.tv);
        pb_dialog = (ProgressBar) d.findViewById(R.id.pb_dialog);
        d.show();
    }

    /*
    update dialog when item details are downloaded
     */
    @Override
    public void updateDialog(MovieDetails details) {
        tv.setText(details.getOverview());
        pb_dialog.setVisibility(View.GONE);
    }

    @Override
    public void onListItemClick(int id) {
        mPresenter.requestMovieDetail(id);
    }
}
