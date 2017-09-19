package com.the.dionisio.apk.client.model.dto;

import java.util.List;

/**
 * Created by igorm on 13/06/2017.
 */

public class Filter
{
    public String name;
    public List<String> genres;
    public DateTimeRange dateTimeRange = new DateTimeRange();
}
