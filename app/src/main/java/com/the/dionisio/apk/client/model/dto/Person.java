package com.the.dionisio.apk.client.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dantas on 3/25/17.
 */

public class Person implements Serializable
{
    private static final long serialVersionUID = 1;

    public String _id;
    public String name;
    public String email;
    public String password;
    public Integer[] birth;
    public String cpf;
    public String picture;
    public String sex;
    public List<String> genres;
    public List<Card> card;
    public Boolean isActive;

    public Integer[] setBirth(String dateBirth)
    {
        Integer[] convertedBirth = new Integer[2];

        try
        {
            String[] date = dateBirth.split("/");
            Integer day = Integer.parseInt(date[0]);
            Integer month = Integer.parseInt(date[1]);
            Integer year = Integer.parseInt(date[2]);


            convertedBirth[0] = year;
            convertedBirth[1] = month;
            convertedBirth[2] = day;
        }
        catch (Exception error)
        {
            return null;
        }

        return convertedBirth;
    }

    public String getBirth(Integer[] value)
    {
        String convertedBirth = "";

        try
        {
            for(int i = 2; i>=0; i--)
            {
                convertedBirth += addZero(value[i]);
                if(i>0)
                {
                    convertedBirth += "/";
                }
            }
        }
        catch (Exception error)
        {
            return null;
        }

        return convertedBirth;
    }

    private String addZero(Integer value)
    {
        if (value<10)
        {
            return String.valueOf("0"+value);
        }
        else
        {
            return String.valueOf(value);
        }
    }
}
