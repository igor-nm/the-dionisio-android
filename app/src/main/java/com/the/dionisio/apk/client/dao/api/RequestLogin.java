package com.the.dionisio.apk.client.dao.api;

import android.content.Context;
import android.util.Log;

import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Ticket;
import com.the.dionisio.apk.client.model.view.View;
import android.widget.Toast;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.dto.Entity;
import com.the.dionisio.apk.client.model.dto.Filter;
import com.the.dionisio.apk.client.model.dto.Login;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.resource.Resource;
import com.the.dionisio.apk.client.retrofit.RetrofitFactory;
import com.the.dionisio.apk.client.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by igorm on 13/05/2017.
 */

public class RequestLogin
{
    private RetrofitFactory retrofitFactory = new RetrofitFactory();
    public static final String TAG = "LOGIN";

    public void postLogin(final Person person, Login login, final Context context, final String typeLogin)
    {
        try
        {
            Call request = retrofitFactory.getLoginService().postLogin(login);

            request.enqueue(new Callback()
            {
                @Override
                public void onResponse(Call call, Response response)
                {
                    validationLogin(response, person, context, typeLogin);
                }

                @Override
                public void onFailure(Call call, Throwable t)
                {
                    Log.e(TAG, "Failure to communication with the server!");
                    Toast.makeText(context, R.string.validation_connection, Toast.LENGTH_SHORT).show();
                    Util.moviment.cancelProgressBar();
                }
            });
        }
        catch(Exception error)
        {

        }
    }

    public void refreshToken(Token token, final Person person, final Ticket ticket, final Event event, final Filter filter, final Context context, final String methodHTTP)
    {
        try
        {
            Call request = retrofitFactory.getLoginService().getToken(token.token);

            request.enqueue(new Callback()
            {
                @Override
                public void onResponse(Call call, Response response)
                {
                    validationRefreshToken(response, person,  ticket, event, filter, context, methodHTTP);
                }

                @Override
                public void onFailure(Call call, Throwable t)
                {
                    Log.e(TAG, "Failure to communication with the server!");
                }
            });
        }
        catch(Exception error)
        {

        }
    }

    public void validationLogin(Response response, Person person, Context context, String typeLogin)
    {
        Entity entity = (Entity) response.body();

        if(entity != null)
        {
            if(response.isSuccessful())
            {
                Token token = new Token();
                token.token = entity.token;

                Person newPerson = entity.entity;

                Log.i(TAG, "Sucessfull - code: " + response.code() + " username: " + newPerson.email + " token: " + token.token);

                Util.validationResponse.validationPerson(response.code(), newPerson, newPerson, context);
                Resource.loginResource.methodsWithToken(token, newPerson, null, null, null, context, Api.METHOD_GET);
            }
            else
            {
                Util.validationResponse.validationLogin(response.code(), person, null, context, null, typeLogin);
                Log.e(TAG, "Failed - code: " + response.code());
            }
        }
        else
        {
            Util.validationResponse.validationLogin(response.code(), person, null, context, null, typeLogin);
            Log.e(TAG, "Failed, the data does not match, code: " + response.code());
        }
    }

    public void validationRefreshToken(Response response, Person person, Ticket ticket, Event event, Filter filter, Context context, String methodHTTP)
    {
        Token newToken = (Token) response.body();

        if(newToken != null)
        {
            if(response.isSuccessful())
            {
                Log.i(TAG, "Sucessfull - code: " + response.code() + " token: " + newToken.token);

                Resource.loginResource.methodsWithToken(newToken, person, ticket, event, filter, context, methodHTTP);
            }
            else
            {
                Log.e(TAG, "Failed - code: " + response.code());
            }
        }
        else
        {
            Log.e(TAG, "Failed, the data does not match, code: " + response.code());
        }
    }
}
