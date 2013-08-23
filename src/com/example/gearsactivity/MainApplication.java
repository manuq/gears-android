package com.example.gearsactivity;

import us.costan.chrome.ChromeView;
import android.app.Application;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ChromeView.initialize(this);
    }
}
