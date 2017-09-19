package com.the.dionisio.apk.client.dao.api;

import android.content.Context;
import android.content.Intent;

import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Preference;
import com.the.dionisio.apk.client.model.dto.Ticket;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.dto.ValidationTicket;
import com.the.dionisio.apk.client.model.presenter.Presenter;
import com.the.dionisio.apk.client.model.presenter.TicketPresenter;
import com.the.dionisio.apk.client.retrofit.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dantas on 6/15/17.
 */

public class RequestTicket {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    public void postTicket(Token token, Ticket ticket, Person person, final Event event, final Context context){

        try {
            Call request = retrofitFactory.getTicketService().postTicket(token.token, ticket);

            request.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                    ValidationTicket validationTicket = (ValidationTicket) response.body();
                    Ticket ticketRespose = validationTicket.additional;

                    Presenter.ticketAction.goTicket(ticketRespose, event, context);
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });

        }catch(Exception erro){

        }
    }
}
