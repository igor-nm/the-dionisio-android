package com.the.dionisio.apk.client.model.view;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.util.Util;
import com.the.dionisio.apk.client.util.view.QrCode;


/**
 * Created by Daniel on 03/06/2017.
 */

public class Ticket extends AppCompatActivity {
    String idTicket = "592df8e565bd903c75b5cdfc";
    ImageView ivCode;

    com.the.dionisio.apk.client.model.dto.Ticket ticketResponse;
    Event event;
    private TextView txtNomeEvent;
    private TextView txtLote;
    private TextView txtSetor;
    private TextView txtData;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_ticket);

        ticketResponse = (com.the.dionisio.apk.client.model.dto.Ticket) getIntent().getSerializableExtra("TICKET");
        event = (Event) getIntent().getSerializableExtra("EVENT");

        txtNomeEvent = (TextView) findViewById(R.id.txtNameEvent);
        txtLote = (TextView) findViewById(R.id.txtBachEvent);
        txtSetor = (TextView) findViewById(R.id.txtSectorEvent);
        txtData = (TextView) findViewById(R.id.txtDateEvent);


        txtLote.setText(ticketResponse.batch.description);
        txtSetor.setText(ticketResponse.batch.sector);
        txtNomeEvent.setText(event.name);
        txtData.setText(event.dateTimeRange.getStart());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ivCode = (ImageView) findViewById(R.id.ivCode);
        new QrCode(idTicket,ivCode,size);

    }
    public void backMenu(View view)
    {
        Util.moviment.backView(this);
    }

}
