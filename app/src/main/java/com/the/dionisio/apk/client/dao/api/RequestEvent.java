package com.the.dionisio.apk.client.dao.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Filter;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.presenter.Presenter;
import com.the.dionisio.apk.client.retrofit.RetrofitFactory;
import com.the.dionisio.apk.client.util.Util;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by igorm on 13/05/2017.
 */

public class RequestEvent
{
    private RetrofitFactory retrofitFactory = new RetrofitFactory();
    private String TAG = "EVENT";

    public void getEvents(final Token token, final Person person, final Context context)
    {
        try
        {
            Call request = retrofitFactory.getEventService().getEvents(token.token);

            request.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    validationEvents(response, token, person, context);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
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

    public void getEventsWithFilter(final Token token, final Person person, Filter filter, final Context context)
    {
        try
        {
            Call request = retrofitFactory.getEventService().getEventsWithFilter(token.token, filter);

            request.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    validationEvents(response, token, person, context);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.e(TAG, "Failure to communication with the server!");
                    Toast.makeText(context, R.string.validation_connection, Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch(Exception error)
        {

        }
    }

    public void validationEvents(Response response, Token token, Person person, Context context)
    {
        List<Event> events = (List<Event>) response.body();

        if(events != null)
        {
            if(response.isSuccessful())
            {
                Log.i(TAG, "Sucessfull - code: " + response.code());

                for(Event event : events)
                {
                    Log.i(TAG, "Event: " + event.description);
                }

                Presenter.loginAction.resultLoginOk(context, person, events, token);
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
