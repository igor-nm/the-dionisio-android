package com.the.dionisio.apk.client.model.dto;

import java.io.Serializable;

/**
 * Created by igorm on 29/05/2017.
 */

public class Place implements Serializable
{
    private static final long serialVersionUID = 1;

    public String description;
    public String zipCode;
    public String city;
    public String street;
    public String number;
    public String phone;
    public String answerable;
    public Location location;
    public Boolean isActive;
}
