package com.the.dionisio.apk.client.model.view.maskForField;

/**
 * Created by igorm on 06/05/2017.
 */

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.util.HashSet;
import java.util.Set;

public class MaskForField implements TextWatcher
{
    private String mMask;
    private EditText mEditText;
    private Set<String> symbolMask = new HashSet<>();
    private boolean isUpdating;
    private String old = "";

    public MaskForField(String mask, EditText editText)
    {
        mMask = mask;
        mEditText = editText;
        initSymbolMask();
    }

    private void initSymbolMask()
    {
        for (int i=0; i < mMask.length(); i++)
        {
            char ch = mMask.charAt(i);
            if (ch != '#')
                symbolMask.add(String.valueOf(ch));
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        String str = unmask(s.toString(), symbolMask);
        String mascara = "";

        if (isUpdating)
        {
            old = str;
            isUpdating = false;
            return;
        }

        if(str.length() > old.length())
            mascara = mask(mMask,str);
        else
            mascara = s.toString();

        isUpdating = true;
        mEditText.setText(mascara);
        mEditText.setSelection(mascara.length());
    }

    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("["+symbol+"]","");

        return s;
    }

    public static String mask(String format, String text){
        String maskedText="";
        int i =0;
        for (char m : format.toCharArray()) {
            if (m != '#') {
                maskedText += m;
                continue;
            }
            try {
                maskedText += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskedText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }
}