package com.example.planner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class ActivitySplash extends Activity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);


        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.splash_act);
        try {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ActivitySplash.this.startActivity(new Intent(ActivitySplash.this, ActivityMyMainDrawer.class));
                    ActivitySplash.this.finish();
                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
