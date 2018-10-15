package com.squabbi.iitk;

import com.appizona.yehiahd.fastsave.FastSave;

import androidx.multidex.MultiDexApplication;

/**
 * Application class which extends MultiDexApplication due to it's large DEX size.
 */

public class IitkApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initalise FastSaves
        FastSave.init(getApplicationContext());
    }
}
