package com.the.dionisio.apk.client.model.presenter;

import android.content.Context;
import android.content.Intent;

import com.the.dionisio.apk.client.dao.api.Api;
import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Ticket;
import com.the.dionisio.apk.client.model.dto.Token;


/**
 * Created by dantas on 6/15/17.
 */

public class TicketPresenter {

    public void getTicket(Token token, Ticket ticket, Person person, Event event, Context context)
    {
        if(validationData(token, ticket))
        {
            Api.requestTicket.postTicket(token, ticket, person, event, context);
        }
    }

    private Boolean validationData(Token token, Ticket ticket)
    {
        if(token.token != null && !token.token.equals("") && ticket != null)
        {
            return true;
        }

        return false;
    }


    public void goTicket(Ticket ticket, Event event, Context context){
        Intent  intent = new Intent(context, com.the.dionisio.apk.client.model.view.Ticket.class);
        intent.putExtra("TICKET", ticket);
        intent.putExtra("EVENT", event);
        context.startActivity(intent);
    }
}
