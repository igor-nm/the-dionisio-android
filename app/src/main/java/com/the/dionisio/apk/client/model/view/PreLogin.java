package com.the.dionisio.apk.client.model.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.util.Util;

/**
 * Created by igorm on 23/04/2017.
 */

public class PreLogin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_login);
    }

    public void goCreateAccount(View v)
    {
        Util.moviment.goView(this, CreateAccount.class);
    }

    public void goLogin(View v)
    {
        Util.moviment.goView(this, Login.class);
    }
}
