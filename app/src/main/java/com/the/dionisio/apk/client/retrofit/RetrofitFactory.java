package com.the.dionisio.apk.client.retrofit;

import com.the.dionisio.apk.client.service.EventService;
import com.the.dionisio.apk.client.service.LoginService;
import com.the.dionisio.apk.client.service.PersonService;
import com.the.dionisio.apk.client.service.TicketService;
import com.the.dionisio.apk.client.util.Util;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by igorm on 13/06/2017.
 */

public class RetrofitFactory
{
    private static final String API_BASE_URL = Util.addressApi.getAddressAPI();
    private final Retrofit retrofit;

    public RetrofitFactory()
    {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public EventService getEventService()
    {
        try
        {
            return retrofit.create(EventService.class);
        }
        catch (Exception e )
        {
            return null;
        }
    }

    public PersonService getPersonService()
    {
        try
        {
            return retrofit.create(PersonService.class);
        }
        catch(Exception error)
        {
            return null;
        }
    }

    public LoginService getLoginService()
    {
        try
        {
            return retrofit.create(LoginService.class);
        }
        catch(Exception error)
        {
            return null;
        }
    }

    public TicketService getTicketService()
    {
        try
        {
            return retrofit.create(TicketService.class);
        }
        catch(Exception error)
        {
            return null;
        }
    }
}
