package com.the.dionisio.apk.client.model.dto;

import java.io.Serializable;

/**
 * Created by igorm on 16/05/2017.
 */

class Card implements Serializable
{
    private static final long serialVersionUID = 1;

    public String flag;
    public String validity;
    public String codSafe;
    public String cardNumber;
    public String owner;
    public Boolean isActive;
}
