package com.the.dionisio.apk.client.model.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.util.Util;

/**
 * Created by igorm on 23/04/2017.
 */

public class ForgotPassword extends AppCompatActivity
{
    private TextInputLayout inputLayoutEmailForgotPassword;
    private EditText inputEmailForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_forgot_password);

        inputLayoutEmailForgotPassword = (TextInputLayout) findViewById(R.id.inputLayoutEmailForgotPassword);
        inputEmailForgotPassword = (EditText) findViewById(R.id.inputEmailForgotPassword);
    }

    public void toRecover(View v)
    {
        if(Util.validationInput.emptyInput(inputEmailForgotPassword, inputLayoutEmailForgotPassword) == false)
        {
            return;
        }
        else
        {

        }
    }

    public void backForgotPassword(View v)
    {
        Util.moviment.backView(this);
    }
}
