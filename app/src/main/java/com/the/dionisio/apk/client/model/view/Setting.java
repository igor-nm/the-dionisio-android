package com.the.dionisio.apk.client.model.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.dao.api.Api;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.dto.Token;
import com.the.dionisio.apk.client.model.presenter.Presenter;
import com.the.dionisio.apk.client.util.Util;
import java.util.Locale;
import java.util.List;

/**
 * Created by Daniel on 03/06/2017.
 */

public class Setting extends AppCompatActivity
{
    private Spinner optLanguage;
    private String[] aLanguage = new String[]{"Portuguese(Brazil)","English(United Stated)","Spain(Spain)"};
    private Person person;
    private Token token;
    private EditText inputEmailSetting, inputNameSetting;
    private List<String> genres;
    private CheckBox checkRockSetting, checkElectronicSetting, checkSertanejoSetting, checkPagodeSetting;
    final private String CHECK = "CHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_setting);

        optLanguage = (Spinner) findViewById(R.id.spLanguage);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aLanguage);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optLanguage.setAdapter(spinnerArrayAdapter);

        optLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        setLocale("br");
                        break;
                    case 2:
                        setLocale("en");
                        break;
                    case 3:
                        setLocale("es");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkRockSetting = (CheckBox) findViewById(R.id.checkRockSetting);
        checkElectronicSetting = (CheckBox) findViewById(R.id.checkElectronicSetting);
        checkSertanejoSetting = (CheckBox) findViewById(R.id.checkSertanejoSetting);
        checkPagodeSetting = (CheckBox) findViewById(R.id.checkPagodeSetting);

        person = (Person) getIntent().getSerializableExtra("PERSON");
        token = (Token) getIntent().getSerializableExtra("TOKEN");

        inputEmailSetting = (EditText) findViewById(R.id.inputEmailSetting);
        inputNameSetting = (EditText) findViewById(R.id.inputNameSetting);

        inputEmailSetting.setText(person.email);
        inputNameSetting.setText(person.name);
        setGenre(person);
    }

    public void setGenre(Person person)
    {
        genres = person.genres;

        for (String genre : genres)
        {
            switch (genre)
            {
                case "rock":
                    checkRockSetting.setTag(CHECK);
                    checkRockSetting.setChecked(true);
                    checkRockSetting.setTextColor(Color.parseColor("#36b30c"));
                    break;
                case "electronic":
                    checkElectronicSetting.setTag(CHECK);
                    checkElectronicSetting.setChecked(true);
                    checkElectronicSetting.setTextColor(Color.parseColor("#36b30c"));
                    break;
                case "sertanejo":
                    checkSertanejoSetting.setTag(CHECK);
                    checkSertanejoSetting.setChecked(true);
                    checkSertanejoSetting.setTextColor(Color.parseColor("#36b30c"));
                    break;
                case "pagode":
                    checkPagodeSetting.setTag(CHECK);
                    checkPagodeSetting.setChecked(true);
                    checkPagodeSetting.setTextColor(Color.parseColor("#36b30c"));
                    break;

            }
        }
    }

    private void populateGenres(View view)
    {
        switch(view.getId())
        {
            case R.id.checkRockSetting:
                if(view.getTag() == CHECK)
                {
                    genres.add("rock");
                }
                else
                {
                    genres.remove("rock");
                }
                break;
            case R.id.checkElectronicSetting:
                if(view.getTag() == CHECK)
                {
                    genres.add("electronic");
                }
                else
                {
                    genres.remove("electronic");
                }
                break;
            case R.id.checkSertanejoSetting:
                if(view.getTag() == CHECK)
                {
                    genres.add("sertanejo");
                }
                else
                {
                    genres.remove("sertanejo");
                }
                break;
            case R.id.checkPagodeSetting:
                if(view.getTag() == CHECK)
                {
                    genres.add("pagode");
                }
                else
                {
                    genres.remove("pagode");
                }
                break;
        }
    }

    public void selectedCheckFilter(View view)
    {
        if(view.getTag() != CHECK && view.getTag() == null)
        {
            CheckBox checkBox = (CheckBox) view;
            checkBox.setTextColor(Color.parseColor("#36b30c"));
            checkBox.setTag(CHECK);
            populateGenres(checkBox);
        }
        else
        {
            CheckBox checkBox = (CheckBox) view;
            checkBox.setTextColor(Color.parseColor("#000000"));
            checkBox.setTag(null);
            populateGenres(checkBox);
        }
    }

    public void putPerson(View view)
    {
        Person person = new Person();
        person._id = this.person._id;
        person.name = inputNameSetting.getText().toString();
        person.genres = genres;
        Presenter.loginAction.refreshTokenApi(token, person, null, null, null, this, Api.METHOD_PUT);
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Setting.class);
        startActivity(refresh);
        finish();
    }

    public void backMenu(View view)
    {
        Util.moviment.backView(this);
    }
}
