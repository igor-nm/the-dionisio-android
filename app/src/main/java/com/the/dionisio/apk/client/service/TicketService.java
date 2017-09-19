package com.the.dionisio.apk.client.service;

import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Ticket;
import com.the.dionisio.apk.client.model.dto.Validation;
import com.the.dionisio.apk.client.model.dto.ValidationTicket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by dantas on 6/15/17.
 */

public interface TicketService {

    @POST("/ticket")
    Call<ValidationTicket> postTicket(@Header("X-Auth-Token") String token, @Body Ticket ticket);
}
