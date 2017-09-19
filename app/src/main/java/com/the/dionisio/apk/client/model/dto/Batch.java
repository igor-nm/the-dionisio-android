package com.the.dionisio.apk.client.model.dto;

import java.io.Serializable;

/**
 * Created by igorm on 29/05/2017.
 */

public class Batch implements Serializable
{
    private static final long serialVersionUID = 1;

    public String name;
    public String description;
    public String sector;
    public DateTimeRange dateTimeRange;
    public Integer limit;
    public Integer sold;
    public Price price;
    public OpenBar openBar;
    public Boolean isActive;
}
