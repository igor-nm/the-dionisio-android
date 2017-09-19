package com.the.dionisio.apk.client.model.presenter;

import com.the.dionisio.apk.client.model.dto.Ticket;

/**
 * Created by igorm on 06/05/2017.
 */

public interface Presenter
{
    PersonPresenter personAction = new PersonPresenter();
    LoginPresenter loginAction = new LoginPresenter();
    EventPresenter eventAction = new EventPresenter();
    TicketPresenter ticketAction = new TicketPresenter();
}
