package com.example.planner;

import android.app.Application;
import com.facebook.ads.AudienceNetworkAds;

public class MyApplication extends Application {
    private static MyApplication instance;
    public int adCount = 0;

    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(this);
        AudienceNetworkAds.isInAdsProcess(getApplicationContext());
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
