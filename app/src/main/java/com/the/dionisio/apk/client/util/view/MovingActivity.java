package com.the.dionisio.apk.client.util.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.view.View;

import java.io.Serializable;
import java.util.List;

/**
 * Created by igorm on 14/04/2017.
 */

public class MovingActivity
{
    public void backView(Activity activity)
    {
        activity.finish();
    }


    public void goView(Context context, Class classDestiny)
    {
        Intent intent = new Intent(context, classDestiny);
        context.startActivity(intent);
    }

    public void goViewWithData(Context context, Class classDestiny, Person person, Token token)
    {
        Intent intent = new Intent(context, classDestiny);
        intent.putExtra("TOKEN", token);
        intent.putExtra("PERSON", person);
        context.startActivity(intent);
    }

    public void goViewClearWithData(Context context, Class classDestiny, Person person, List<Event> events, Token token)
    {
        Intent intent = new Intent(context, classDestiny);
        intent.putExtra("TOKEN", token);
        intent.putExtra("PERSON", person);
        intent.putExtra("EVENTS", (Serializable) events);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void goViewClear(Context context, Class classDestiny)
    {
        Intent intent = new Intent(context, classDestiny);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void cancelProgressBar()
    {
        View.login.cancelProgessBar();
    }
}
