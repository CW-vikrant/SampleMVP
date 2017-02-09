package com.samplemvp.dagger;

/**
 * Created by Vikrant Chauhan on 10/26/2016.
 */

import com.samplemvp.mvp.MovieModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)

public interface AppComponent {
    void inject(MovieModel client);
}