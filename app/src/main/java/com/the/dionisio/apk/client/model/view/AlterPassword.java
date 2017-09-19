package com.the.dionisio.apk.client.model.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.util.Util;

public class AlterPassword extends AppCompatActivity {
    /**
     * Created by Daniel on 10/06/2017.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_alter_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void backMenu(View view)
    {
        Util.moviment.backView(this);
    }
}
