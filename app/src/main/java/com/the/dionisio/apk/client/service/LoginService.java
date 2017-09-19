package com.the.dionisio.apk.client.service;

import com.the.dionisio.apk.client.model.dto.Entity;
import com.the.dionisio.apk.client.model.dto.Login;
import com.the.dionisio.apk.client.model.dto.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by igorm on 13/05/2017.
 */

public interface LoginService
{
    @POST("/login")
    Call<Entity> postLogin(@Body Login login);

    @GET("/login/refresh")
    Call<Token> getToken(@Header("X-Auth-Token") String token);
}
