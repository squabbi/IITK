package com.squabbi.iitk;

import android.app.Application;

import com.appizona.yehiahd.fastsave.FastSave;
import com.google.firebase.FirebaseApp;

import androidx.multidex.MultiDexApplication;

public class IitkApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FastSave.init(getApplicationContext());
    }
}
