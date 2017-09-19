package com.the.dionisio.apk.client.model.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.view.fragments.MapsLocation;
import com.the.dionisio.apk.client.util.Util;

public class MapsEvents extends AppCompatActivity
{
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_maps_location);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.MapsContainer, new MapsLocation(), "MapsLocation");
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void backEvent(View view)
    {
        Util.moviment.backView(this);
    }
}
