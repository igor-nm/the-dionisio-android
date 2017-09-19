package com.the.dionisio.apk.client.model.view;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mercadopago.callbacks.Callback;
import com.mercadopago.core.MercadoPago;
import com.mercadopago.core.MerchantServer;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.model.ApiException;
import com.mercadopago.model.CheckoutPreference;
import com.mercadopago.model.DecorationPreference;
import com.mercadopago.model.Payment;
import com.mercadopago.util.JsonUtil;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.dao.api.Api;
import com.the.dionisio.apk.client.model.dto.Batch;
import com.the.dionisio.apk.client.model.dto.Ticket;
import com.the.dionisio.apk.client.model.dto.Event;
import com.the.dionisio.apk.client.model.dto.Items;
import com.the.dionisio.apk.client.model.dto.Payer;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Preference;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.presenter.Presenter;
import com.the.dionisio.apk.client.model.view.fragments.EventListAdapter;
import com.the.dionisio.apk.client.util.Util;
import com.the.dionisio.apk.client.util.api.AddressAPI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart extends AppCompatActivity  {

    static final String TAG = "mercadopago";
    static final String publicKey = "TEST-e207354b-53a0-4ab9-87ed-3150c598e690";
    private static final String API_BASE_URL = Util.addressApi.getAddressAPI();
    private Spinner spinnerSector;
    private ArrayAdapter<String> adapterSector;
    Event event = new Event();
    private Person person;
    private Token token;
    Ticket ticket = new Ticket();

    private RadioButton rbMan;
    private RadioButton rbWoman;
    private RadioButton rbOther;
    private TextView txtMan;
    private TextView txtWoman;
    private TextView txtOther;
    private Button btnCheckOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_shopping_cart);

        event = (Event) getIntent().getSerializableExtra("EVENT");
        person = (Person) getIntent().getSerializableExtra("PERSON");
        token = (Token) getIntent().getSerializableExtra("TOKEN");

        rbMan = (RadioButton) findViewById(R.id.radioManEvent);
        rbWoman = (RadioButton) findViewById(R.id.radioWomanEvent);
        rbOther = (RadioButton) findViewById(R.id.radioOthersEvent);
        txtMan = (TextView) findViewById(R.id.txtValueMan);
        txtWoman = (TextView) findViewById(R.id.txtValueWoman);
        txtOther = (TextView) findViewById(R.id.txtValueOther);
        btnCheckOut = (Button) findViewById(R.id.btnCheckout);
        rbMan.setChecked(true);
        selectSector();




    }


    public void selectSector(){
        spinnerSector = (Spinner) findViewById(R.id.spinnerSector);
        adapterSector = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterSector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSector.setAdapter(adapterSector);
        for(Batch bacth : event.batches)
        {
            adapterSector.add(bacth.sector);
        }

        spinnerSector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String posi = spinnerSector.getSelectedItem().toString();
                for(Batch batch : event.batches){

                    if(batch.sector.toString().equals(posi)){
                        txtMan.setText("R$: " + batch.price.man.toString());
                        txtWoman.setText("R$: " + batch.price.woman.toString());
                        txtOther.setText("R$: " + batch.price.other.toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void backDetailedEvent(View view)
    {
        Util.moviment.backView(this);
    }


    public void callCheckout(final View view) {


        String posi = spinnerSector.getSelectedItem().toString();
        List<Items> itemses = new ArrayList<>();
        Items items = new Items();
        Payer payer = new Payer();
        Preference preference = new Preference();

        for(Batch batch : event.batches){

            if(batch.sector.toString().equals(posi)){

                if(rbMan.isChecked()){
                    items.unit_price = batch.price.man.toString();
                }
                if(rbWoman.isChecked()){
                    items.unit_price = batch.price.woman.toString();
                }
                if(rbOther.isChecked()){
                    items.unit_price = batch.price.other.toString();
                }

                ticket.batch = batch;
                ticket._idCompany = event._idCompany;
                ticket._idEvent = event._id;
                ticket._idPerson = person._id;
            }
        }



        items.title = event.name;
        items.description = event.description;
        items.quantity = "1";
        items.currency_id = "BRL";
        payer.name = person.name;
        payer.email = person.email;

        itemses.add(items);
        preference.items = itemses;
        preference.payer = payer;

        Map<String, Object> preferenceMap = new HashMap<>();
        preferenceMap.put("preference", preference);


        Log.i(TAG, "Recebi os parametros ");

        // Envía para o servidor
        MerchantServer.createPreference(this, API_BASE_URL,
                "/mercado-pago/checkout", preferenceMap, new Callback<CheckoutPreference>() {

                    @Override
                    public void success(CheckoutPreference checkoutPreference) {
                        onCardStartButtonClicked(view, checkoutPreference);
                    }

                    @Override
                    public void failure(ApiException error) {
                        Log.e(TAG, "Erro no retorno " + error);
                    }
                });
    }

    public void onCardStartButtonClicked(View view, CheckoutPreference checkoutPreference) {


        DecorationPreference decorationPreference = new DecorationPreference();
        decorationPreference.setBaseColor("#FF4500");
        decorationPreference.enableDarkFont();

        //chamada do checkout do mercadopago
        new MercadoPago.StartActivityBuilder()
                .setActivity(this)
                .setPublicKey(publicKey)
                .setCheckoutPreferenceId(checkoutPreference.getId())
                .setDecorationPreference(decorationPreference)
                .startCheckoutActivity();
    }


    //Retorno do checkout do mercadopago
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {

        if (requestCode == MercadoPago.CHECKOUT_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {


                Payment payment = JsonUtil.getInstance()
                        .fromJson(data.getStringExtra("payment"), Payment.class);

                if (payment != null) {
                    Toast.makeText(this, "PaymentID: " + payment.getId() +
                            " - PaymentStatus: " + payment.getStatus(), Toast.LENGTH_LONG).show();
                    
                    if(payment.getStatus().equals("approved")) goQRCode();

                } else {
                    Toast.makeText(this, "Erro Payment", Toast.LENGTH_LONG).show();
                }

            } else {
                if ((data != null) && (data.hasExtra("mpException"))) {
                    MPException mpException = JsonUtil.getInstance()
                            .fromJson(data.getStringExtra("mpException"), MPException.class);
                }
            }
        }
    }

    public void goQRCode(){
        Presenter.loginAction.refreshTokenApi(token, person, ticket, event, null, this, Api.METHOD_POST);
    }

}
