package com.squabbi.iitk;

import android.app.Application;

import com.appizona.yehiahd.fastsave.FastSave;

import androidx.multidex.MultiDexApplication;

public class IITKApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FastSave.init(getApplicationContext());
    }
}
