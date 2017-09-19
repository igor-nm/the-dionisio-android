package com.the.dionisio.apk.client.util.verification;

import android.content.Context;
import android.widget.Toast;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.presenter.Presenter;
import com.the.dionisio.apk.client.model.resource.Resource;
import com.the.dionisio.apk.client.model.view.CreateAccountStepGenre;
import com.the.dionisio.apk.client.util.Util;
import java.util.List;

/**
 * Created by igorm on 20/05/2017.
 */

public class ValidationResponse
{
    public void validationPerson(Integer statusCode, Person person, Person newPerson, Context context)
    {
        switch (statusCode)
        {
            case 200:
                if(Resource.personResource.treatCreate(person))
                {
                    Resource.personResource.createPersonOrUpdatePerson(newPerson, context);
                }
                else if(Resource.personResource.treatUpdate(person))
                {
                    Resource.personResource.createPersonOrUpdatePerson(newPerson, context);
                }
                break;
            case 401:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
            case 404:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
            case 406:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
            case 509:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, R.string.validation_connection, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void validationLogin(Integer statusCode, Person person, List<Event> events, Context context, Token token, String typeLogin)
    {
        switch (statusCode)
        {
            case 200:
                Presenter.loginAction.resultLoginOk(context, person, events, token);
                break;
            case 401:
                if(typeLogin.equals("SOCIAL_NETWORK"))
                {
                    Util.moviment.goViewWithData(context, CreateAccountStepGenre.class, person, token);
                }
                else
                {
                    Toast.makeText(context, R.string.validation_login, Toast.LENGTH_SHORT).show();
                    Util.moviment.cancelProgressBar();
                }
                break;
            case 404:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
            case 406:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
            case 509:
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, R.string.validation_connection, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
