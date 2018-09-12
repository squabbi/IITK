package com.squabbi.iitk;

import android.app.Application;

import com.appizona.yehiahd.fastsave.FastSave;

public class IITKApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FastSave.init(getApplicationContext());
    }
}
