package com.the.dionisio.apk.client.model.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.presenter.Presenter;
import com.the.dionisio.apk.client.util.Util;
import java.util.Arrays;

/**
 * Created by igorm on 23/04/2017.
 */

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener
{
    //This is data of google
    private SignInButton btnLoginGoogle;
    private GoogleApiClient googleApiClient;
    private static final int REQUEST_CODE_GOOGLE = 8888;

    //This is data of facebook
    private LoginButton btnLoginFacebook;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    //This is components this view
    private static ProgressDialog progressLogin;
    private TextInputLayout inputLayoutEmailLogin, inputLayoutPasswordLogin;
    private EditText inputEmailLogin, inputPasswordLogin;
    private TextView txtForgotPassword;
    private Button btnLogin;
    private String typeLogin = "";
    private boolean LOGIN_FB = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_login);

        instantiateComponents();

        methodsFacebook();
    }

    private void instantiateComponents()
    {
        //Components of facebook
        btnLoginFacebook = (LoginButton) findViewById(R.id.btnLoginFacebook);
        callbackManager = CallbackManager.Factory.create();
        btnLoginFacebook = (LoginButton) findViewById(R.id.btnLoginFacebook);
        btnLoginFacebook.setReadPermissions(Arrays.asList("email", "public_profile", "user_friends"));

        //Beggin bloc google
        //Componentes of google
        btnLoginGoogle = (SignInButton) findViewById(R.id.btnLoginGoogle);
        btnLoginGoogle.setOnClickListener(this);
        //Here is retrieved the data necessary for the integration of google
        GoogleSignInOptions signInOptions = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
        //End bloc google

        //Components of view
        inputLayoutEmailLogin = (TextInputLayout) findViewById(R.id.inputLayoutEmailLogin);
        inputLayoutPasswordLogin = (TextInputLayout) findViewById(R.id.inputLayoutPasswordLogin);
        inputEmailLogin = (EditText) findViewById(R.id.inputEmailLogin);
        inputPasswordLogin = (EditText) findViewById(R.id.inputPasswordLogin);

        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private  void methodsFacebook()
    {

        btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                /*Aqui tenho que verificar se o amiguinho já é cadastrado, caso não mando ele para
                cadastro de genero para finalizar o cadastro no aplicativo The Dionisio,
                caso ele já possua um cadastro devo redireciona-lo para a tela principal*/
                handleFacebookAcessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel()
            {
                //Mensagem de erro caso o amiginho cancele o login com o facebook
                Toast.makeText(getApplicationContext() , "Cancelad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error)
            {
                //Mensagem de erro caso tenha algum problema de conexão com o facebook
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    if(LOGIN_FB)
                    {
                        LOGIN_FB = false;
                        goViewMain();
                    }
                    Log.i("LOGIN", "Facebook login is successful!");
                }
            }
        };
    }

    private void handleFacebookAcessToken(AccessToken accessToken)
    {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "That so bad", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void goViewMain()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String password = user.getUid();
            String picture = user.getPhotoUrl().toString();
            typeLogin = "SOCIAL_NETWORK";
            loadProgressBar();

            Presenter.loginAction.startLogin(
                    Util.setData.setPerson(email, password, name, picture),
                    Util.setData.setLogin(email, password),
                    this,
                    typeLogin);
        }
    }

    protected void onStart()
    {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    protected void onStop()
    {
        super.onStop();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnLoginGoogle:
                signInGoogle();
                break;
        }
    }

    public void signInGoogle()
    {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQUEST_CODE_GOOGLE);
    }

    //Method of google
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    //Google's method where the user data is retrieved for creation of the same
    public void handleResultGoogle(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String password = account.getId();
            String picture = account.getPhotoUrl().toString();
            typeLogin = "SOCIAL_NETWORK";

            loadProgressBar();

            Presenter.loginAction.startLogin(
                    Util.setData.setPerson(email, password, name, picture),
                    Util.setData.setLogin(email, password),
                    this,
                    typeLogin);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Connection with google
        if(requestCode == REQUEST_CODE_GOOGLE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResultGoogle(result);
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void goLogin(View v)
    {
        if(!Util.validationInput.emptyInput(inputEmailLogin, inputLayoutEmailLogin))
        {
            return;
        }
        else if(!Util.validationInput.emptyInput(inputPasswordLogin, inputLayoutPasswordLogin))
        {
            return;
        }
        else
        {
            loadProgressBar();

            typeLogin = "NORMAL_LOGIN";

            Presenter.loginAction.startLogin(
                    Util.setData.setPerson(inputEmailLogin.getText().toString(), inputPasswordLogin.getText().toString(), null, null),
                    Util.setData.setLogin(inputEmailLogin.getText().toString(), inputPasswordLogin.getText().toString()),
                    this,
                    typeLogin);
        }
    }

    public void backLogin(View view)
    {
        Util.moviment.backView(this);
    }

    public void goForgotPassword(View view)
    {
        Util.moviment.goView(this, ForgotPassword.class);
    }

    public void loadProgressBar()
    {
        progressLogin = new ProgressDialog(Login.this);
        progressLogin.setTitle(getString(R.string.progress_title));
        progressLogin.setMessage(getString(R.string.progress_message));
        progressLogin.show();
    }

    public void cancelProgessBar()
    {
        progressLogin.dismiss();
    }
}
