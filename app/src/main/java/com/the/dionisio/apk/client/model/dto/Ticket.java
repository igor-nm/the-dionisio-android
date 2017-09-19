package com.the.dionisio.apk.client.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by igorm on 3/7/17.
 */

public class Ticket implements Serializable
{
    private static final long serialVersionUID = 1;

    public String _id;
    public String _idPerson;
    public String _idCompany;
    public String _idEvent;
    public Batch  batch;
    public List<Integer> purchaseDate;
    public Price price;
    public OpenBar openBar;
    public Boolean isActive;
    public String status;
}




