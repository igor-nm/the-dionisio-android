package com.the.dionisio.apk.client.util.view;

import com.the.dionisio.apk.client.model.dto.Login;
import com.the.dionisio.apk.client.model.dto.Person;

/**
 * Created by igorm on 13/05/2017.
 */

public class SetData
{
    public Person setPerson(String email, String password, String name, String picture)
    {
        Person person = new Person();
        person.email = email;
        person.password = password;
        person.name = name;
        person.picture = picture;

        return person;
    }

    public Login setLogin(String username, String password)
    {
        Login login = new Login();
        login.username = username;
        login.password = password;

        return login;
    }
}
