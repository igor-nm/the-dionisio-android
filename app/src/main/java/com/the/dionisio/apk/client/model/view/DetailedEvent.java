package com.the.dionisio.apk.client.model.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.dto.Batch;
import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailedEvent extends AppCompatActivity
{
    private Event event;
    private Person person;
    private Token token;
    private TextView txtNameEvent, txtDescriptionEvent, txtDateStartEvent, txtDateEndEvent;
    private ImageView imageBannerEvent;
    private List<Event> listEvent = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_event);

        txtNameEvent = (TextView) findViewById(R.id.txtNameEvent);
        txtDescriptionEvent = (TextView) findViewById(R.id.txtDescriptionEvent);
        txtDateStartEvent = (TextView) findViewById(R.id.txtDateStartEvent);
        txtDateEndEvent = (TextView) findViewById(R.id.txtDateEndEvent);
        imageBannerEvent = (ImageView) findViewById(R.id.imageBannerEvent);

        event = (Event) getIntent().getSerializableExtra("EVENT");
        person = (Person) getIntent().getSerializableExtra("PERSON");
        token = (Token) getIntent().getSerializableExtra("TOKEN");
        populateEvent();
        listEvent.add(event);
    }

    public void populateEvent()
    {
        txtNameEvent.setText(event.name);
        txtDescriptionEvent.setText(event.description);
        txtDateStartEvent.setText(event.dateTimeRange.getStart());
        txtDateEndEvent.setText(event.dateTimeRange.getEnd());

        if(event.urlBanners != null)
        {
            Glide.with(this).load(event.urlBanners.get(0)).into(imageBannerEvent);
        }
    }

    public void goLocation(View view)
    {
        Intent intent = new Intent(this, MapsEvents.class);
        intent.putExtra("ListEvents", (Serializable) listEvent);
        startActivity(intent);
    }

    public void goShoppingCart(View view){
        Intent intent = new Intent(this, ShoppingCart.class);
        intent.putExtra("EVENT", event);
        intent.putExtra("PERSON", person);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
    }

    public void backDetailedEvent(View view)
    {
        Util.moviment.backView(this);
    }
}
