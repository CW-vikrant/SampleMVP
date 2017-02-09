package com.samplemvp;

import android.app.Application;

import com.samplemvp.dagger.AppComponent;
import com.samplemvp.dagger.AppModule;
import com.samplemvp.dagger.DaggerAppComponent;

/**
 * Created by Vikrant Chauhan on 10/26/2016.
 */

public class SampleApplication extends Application {

    private static SampleApplication instance = new SampleApplication();
    private static AppComponent component;
    public final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";

    public static SampleApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        if (component == null){
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return component;
    }
}
