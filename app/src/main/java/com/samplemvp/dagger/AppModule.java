package com.samplemvp.dagger;

import android.content.Context;

import com.samplemvp.SampleApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vikrant Chauhan on 10/26/2016.
 */

@Module
public class AppModule {

    private final SampleApplication mApp;
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static Retrofit retrofit;

    public AppModule(SampleApplication mApp){
        this.mApp = mApp;
    }

    @Provides @Singleton
    public Context provideContext(){
        return this.mApp;
    }

    @Provides @Singleton
    public Retrofit provideApiClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}