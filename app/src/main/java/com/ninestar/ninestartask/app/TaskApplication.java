package com.ninestar.ninestartask.app;

import android.app.Application;
import android.content.Context;

public class TaskApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
    /* Crating application context */
    public static Context getContext() {
        return sContext;
    }
}
