package com.the.dionisio.apk.client.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dantas on 5/21/17.
 */

public class Event implements Serializable
{
    private static final long serialVersionUID = 1;

    public String _id;
    public String _idCompany;
    public String name;
    public String description;
    public DateTimeRange dateTimeRange;
    public List<String> genres;
    public List<String> urlBanners;
    public List<Batch> batches;
    public List<Ticket> tickets;
    public Place place;
    public Boolean isActive;
}