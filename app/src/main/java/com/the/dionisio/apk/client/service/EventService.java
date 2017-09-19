package com.the.dionisio.apk.client.service;

import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Filter;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by igorm on 29/05/2017.
 */

public interface EventService
{
    @GET("/event")
    Call<List<Event>> getEvents(@Header("X-Auth-Token") String token);

    @POST("/event/find-by")
    Call<List<Event>> getEventsWithFilter(@Header("X-Auth-Token") String token, @Body Filter filter);
}
