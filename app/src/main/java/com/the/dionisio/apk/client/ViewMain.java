package com.the.dionisio.apk.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.dao.api.Api;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.presenter.Presenter;
import com.the.dionisio.apk.client.model.view.PreLogin;
import com.the.dionisio.apk.client.util.Util;

/**
 * Created by igorm on 23/04/2017.
 */

public class ViewMain extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private TextView txtName, txtEmail, txtPassword, txtBirth, txtSex;
    private ImageView imgPhoto;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_view_main);

        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtBirth = (TextView) findViewById(R.id.txtBirth);
        txtSex = (TextView) findViewById(R.id.txtSex);

        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);

        Person person = (Person) getIntent().getSerializableExtra("PERSON");

        txtName.setText(person.name);
        txtEmail.setText(person.email);
        txtPassword.setText(person.password);
        txtBirth.setText(person.getBirth(person.birth));
        txtSex.setText(person.sex);
        Glide.with(this).load(person.picture).into(imgPhoto);

        GoogleSignInOptions signInOptions = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
    }

    public void logOut(View view)
    {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
             public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    logOutAccount();
                } else {
                   Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                }
             }
        });

        logOutAccount();
    }

    public void logOutAccount()
    {
        Intent intent = new Intent(this, PreLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void testUpdatePerson(View view)
    {
        Person person = (Person) getIntent().getSerializableExtra("PERSON");
        Token token = (Token) getIntent().getSerializableExtra("TOKEN");

        person.name = "Jonathan Henrique";
        person.email = null;

        Presenter.loginAction.refreshTokenApi(token, person, null, null, null, this, Api.METHOD_PUT);
    }

    public void testDeletePerson(View view)
    {
        Person person = (Person) getIntent().getSerializableExtra("PERSON");
        Token token = (Token) getIntent().getSerializableExtra("TOKEN");

        person.name = "";
        person.email = null;

        Presenter.loginAction.refreshTokenApi(token, person, null, null, null, this, Api.METHOD_PUT);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }
}
