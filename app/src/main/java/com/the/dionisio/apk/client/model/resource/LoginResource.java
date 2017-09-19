package com.the.dionisio.apk.client.model.resource;

import android.content.Context;
import android.widget.Toast;

import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Filter;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Ticket;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.presenter.Presenter;

/**
 * Created by igorm on 27/05/2017.
 */

public class LoginResource
{
    public void  methodsWithToken(Token token, Person person, Ticket ticket, Event event, Filter filter, Context context, String methodHttp)
    {
        switch(methodHttp)
        {
            case "get":
                if (validationParameters(token)) {Presenter.eventAction.getEvents(token, person, context);}
                else {Toast.makeText(context, "", Toast.LENGTH_SHORT).show();}
                break;
            case "post":
                if(filter != null)
                {
                    if (validationParameters(token)) {Presenter.eventAction.getEventsWithFilter(token, person, filter, context);}
                    else { Toast.makeText(context, "", Toast.LENGTH_SHORT).show();}
                }
                else
                {
                    if(validationParameters(token)){Presenter.ticketAction.getTicket(token, ticket, person, event, context);}
                }
                break;
            case "put":
                if (validationParameters(token)) {Presenter.personAction.updatePersonApi(token, person, context);}
                else {Toast.makeText(context, "", Toast.LENGTH_SHORT).show();}
                break;
            case "delete":
                if (validationParameters(token, person)) {Presenter.personAction.deletePersonApi(token, person, context);}
                else {Toast.makeText(context, "", Toast.LENGTH_SHORT).show();}
                break;
            default:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public Boolean validationParameters(Token token)
    {
        if(token != null) {return true;}
        else  {return false;}
    }

    public Boolean validationParameters(Token token, Person person)
    {
        if(token != null && person != null) {return true;}
        else  {return false;}
    }
}
