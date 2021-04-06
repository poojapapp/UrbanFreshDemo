package com.fitternity.urbanfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fitternity.urbanfresh.activity.BaseActivity;
import com.fitternity.urbanfresh.activity.HomeNavigationActivity;

public class SplashActivity extends BaseActivity {
    private static final int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(mActivity, HomeNavigationActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                //    printHashKey( );
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}