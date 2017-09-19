package com.the.dionisio.apk.client.model.presenter;

import android.content.Context;

import com.the.dionisio.apk.client.dao.api.Api;
import com.the.dionisio.apk.client.model.dto.Filter;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;

/**
 * Created by igorm on 01/06/2017.
 */

public class EventPresenter
{
    public void getEvents(Token token, Person person, Context context)
    {
        if(validationData(token, person))
        {
            Api.requestEvent.getEvents(token, person, context);
        }
    }

    public void getEventsWithFilter(Token token, Person person, Filter filter, Context context)
    {
        if(validationData(token, person))
        {
            Api.requestEvent.getEventsWithFilter(token, person, filter, context);
        }
    }

    private Boolean validationData(Token token, Person person)
    {
        if(token.token != null && !token.token.equals("") && person != null)
        {
            return true;
        }

        return false;
    }
}
