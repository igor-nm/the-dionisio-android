package com.the.dionisio.apk.client.dao.api;

/**
 * Created by igorm on 06/05/2017.
 */

public interface Api
{
    String METHOD_GET = "get";
    String METHOD_POST = "post";
    String METHOD_PUT = "put";
    String METHOD_DELETE = "delete";

    RequestPerson requestPerson = new RequestPerson();
    RequestLogin requestLogin = new RequestLogin();
    RequestEvent requestEvent = new RequestEvent();
    RequestTicket requestTicket = new RequestTicket();
}
