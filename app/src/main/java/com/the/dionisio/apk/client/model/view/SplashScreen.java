package com.the.dionisio.apk.client.model.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.util.Util;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);   // set the duration of splash screen
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Util.moviment.goViewClear(getApplicationContext(), PreLogin.class);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
