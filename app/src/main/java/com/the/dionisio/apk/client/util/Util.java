package com.the.dionisio.apk.client.util;

import com.the.dionisio.apk.client.util.api.AddressAPI;
import com.the.dionisio.apk.client.util.verification.ValidationResponse;
import com.the.dionisio.apk.client.util.view.CardGenre;
import com.the.dionisio.apk.client.util.view.MovingActivity;
import com.the.dionisio.apk.client.util.view.SetData;
import com.the.dionisio.apk.client.util.verification.ValidationInput;

/**
 * Created by igorm on 14/04/2017.
 */

public interface Util
{
    /**
     * Methods abstract for use in view.
     */
    MovingActivity moviment = new MovingActivity();
    CardGenre cardGenre = new CardGenre();
    SetData setData = new SetData();
    /**
     * Methods of use in retrofit for api.
     */
    AddressAPI addressApi = new AddressAPI();

    /**
     * Methods of validation independent of use.
     */
    ValidationInput validationInput = new ValidationInput();
    ValidationResponse validationResponse = new ValidationResponse();
}
