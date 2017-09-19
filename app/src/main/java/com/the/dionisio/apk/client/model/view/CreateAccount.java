package com.the.dionisio.apk.client.model.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.dto.Person;
import com.the.dionisio.apk.client.model.view.maskForField.MaskForField;
import com.the.dionisio.apk.client.util.Util;

/**
 * Created by igorm on 23/04/2017.
 */

public class CreateAccount extends AppCompatActivity
{
    private TextInputLayout inputLayoutNameFullCreateAccount, inputLayoutEmailCreateAccount, inputLayoutPasswordCreateAccount, inputLayoutBirthCreateAccount;
    private EditText inputNameFullCreateAccount, inputEmailCreateAccount, inputPasswordCreateAccount, inputBirthCreateAccount;
    private RadioButton radioManCreateAccount, radioWomanCreateAccount;
    private final String MASK_DATE = "##/##/####";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_create_account);

        inputLayoutNameFullCreateAccount = (TextInputLayout) findViewById(R.id.inputLayout);
        inputLayoutEmailCreateAccount = (TextInputLayout) findViewById(R.id.inputLayoutEmailCreateAccount);
        inputLayoutPasswordCreateAccount = (TextInputLayout) findViewById(R.id.inputLayoutPasswordCreateAccount);
        inputLayoutBirthCreateAccount = (TextInputLayout) findViewById(R.id.inputLayoutBirthCreateAccount);

        inputNameFullCreateAccount = (EditText) findViewById(R.id.inputNameFullCreateAccount);
        inputEmailCreateAccount = (EditText) findViewById(R.id.inputEmailCreateAccount);
        inputPasswordCreateAccount = (EditText) findViewById(R.id.inputPasswordCreateAccount);
        inputBirthCreateAccount = (EditText) findViewById(R.id.inputBirthCreateAccount);

        radioManCreateAccount = (RadioButton) findViewById(R.id.radioManCreateAccount);
        radioWomanCreateAccount = (RadioButton) findViewById(R.id.radioWomanCreateAccount);

        inputBirthCreateAccount.addTextChangedListener(new MaskForField(MASK_DATE, inputBirthCreateAccount));
    }

    public void backCreateAccount(View v)
    {
        Util.moviment.backView(this);
    }

    public void goCreateAccountStepGenre(View v)
    {

        if(!Util.validationInput.emptyInput(inputNameFullCreateAccount, inputLayoutNameFullCreateAccount))
        {
            return;
        }
        else if(!Util.validationInput.emptyInput(inputEmailCreateAccount, inputLayoutEmailCreateAccount))
        {
            return;

        }
        else if(!Util.validationInput.emptyInput(inputPasswordCreateAccount, inputLayoutPasswordCreateAccount))
        {
            return;
        }
        else if(!Util.validationInput.emptyInput(inputBirthCreateAccount, inputLayoutBirthCreateAccount))
        {
            return;
        }
        else
        {
            Person person = new Person();
            person.name = inputNameFullCreateAccount.getText().toString();
            person.email = inputEmailCreateAccount.getText().toString();
            person.password = inputPasswordCreateAccount.getText().toString();
            person.picture = "";
            person.birth = person.setBirth(inputBirthCreateAccount.getText().toString());

            if(radioManCreateAccount.isChecked())
            {
                person.sex = "MAN";
            }
            else if(radioWomanCreateAccount.isChecked())
            {
                person.sex = "WOMAN";
            }
            else
            {
                person.sex = "OTHERS";
            }

            Util.moviment.goViewWithData(this, CreateAccountStepGenre.class, person, null);
        }
    }
}
