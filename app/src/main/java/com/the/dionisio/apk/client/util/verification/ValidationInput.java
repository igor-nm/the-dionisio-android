package com.the.dionisio.apk.client.util.verification;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import com.the.dionisio.apk.client.R;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by igorm on 03/05/2017.
 */

public class ValidationInput
{
    public Boolean emptyInput(EditText editText, TextInputLayout textInputLayout)
    {
        final int inputEmail = 33,
                  inputBirth = 20,
                  inputName = 97,
                  inputPassword = 129;

        if(editText.getText().toString().trim().isEmpty())
        {
            switch (editText.getInputType())
            {
                case inputName:
                    textInputLayout.setError(editText.getResources().getString(R.string.field_nameFull));
                    break;
                case inputEmail:
                    textInputLayout.setError(editText.getResources().getString(R.string.field_email));
                    break;
                case inputPassword:
                    textInputLayout.setError(editText.getResources().getString(R.string.field_password));
                    break;
                case inputBirth:
                    textInputLayout.setError(editText.getResources().getString(R.string.field_birth));
                    break;
            }

            return validGeneric(editText, textInputLayout);
        }
        else if(editText.getInputType() == inputEmail)
        {
            if(!isValidEmail(editText))
            {
                textInputLayout.setError(editText.getResources().getString(R.string.field_email));
                return validGeneric(editText, textInputLayout);
            }
        }
        else if (editText.getInputType() == inputBirth)
        {
            try
            {
                Calendar calendar = GregorianCalendar.getInstance();

                String[] date = editText.getText().toString().split("/");
                Integer day = Integer.parseInt(date[0]);
                Integer month = Integer.parseInt(date[1]);
                Integer year = Integer.parseInt(date[2]);
                Integer yearNow = calendar.get(Calendar.YEAR), yearMin = (calendar.get(Calendar.YEAR)-150);

                if(day > 31 || month > 12 || year >= yearNow || year <= yearMin)
                {
                    textInputLayout.setError(editText.getResources().getString(R.string.field_birth));
                    return validGeneric(editText, textInputLayout);
                }
             }
            catch (Exception exception)
            {
                textInputLayout.setError(editText.getResources().getString(R.string.field_birth));
                return validGeneric(editText, textInputLayout);
            }
        }

        editText.setError(null);
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    @NonNull
    private Boolean isValidEmail(EditText editText)
    {
        String email = editText.getText().toString().trim();

        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @NonNull
    private Boolean validGeneric(EditText editText, TextInputLayout textInputLayout)
    {
        textInputLayout.setErrorEnabled(true);
        editText.setError(editText.getResources().getString(R.string.field_required));
        editText.requestFocus();
        return false;
    }
}
