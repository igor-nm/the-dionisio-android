package com.the.dionisio.apk.client.util.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 6/5/17.
 */

public class TextHelp {
    private String patternValue = new String();
    private String textValue = new String();
    private StringBuilder result = new StringBuilder();

    /**
     * Trata texto de acordo com o padrão informado.
     * Exemplo: entrada => format("aaaaa","aAa")
     *          saída => aAaAa
     * @param text
     * @param pattern
     * @return
     */
    public String format(String text, String pattern)
    {
        result = new StringBuilder();
        textValue =text;
        patternValue=pattern;

        if (!textValidation())
        {
            return "text error";
        }
        if (!patternValidation())
        {
            return "pattern error";
        }

        String[] frase = this.textValue.split(" ");
        List<String> treatTex = new ArrayList<String>();
        for(int i =0; i<frase.length; i++)
        {
            treatTex.add(formatBlock(frase[i]));
        }

        for(String t : treatTex)
        {
            result.append(t+" ");
        }

        return result.deleteCharAt(result.length()-1).toString();

    }


    private String formatBlock(String text){

        this.textValue =text;
        switch (this.patternValue)
        {
            case "aaa":
                return aaa();
            case "aaA":
                return aaA();
            case "aAa":
                return aAa();
            case "AAA":
                return AAA();
            case "AAa":
                return AAa();
            case "Aaa":
                return Aaa();
            case "AaA":
                return AaA();
            default:
                return text;
        }
    }

    private boolean textValidation() {
        return this.textValue != null &&
               this.textValue != "";
    }

    private boolean patternValidation() {
        return this.patternValue != null &&
               this.patternValue != "" &&
               this.patternValue.length()==3;
    }

    private String aaa()
    {
        return this.textValue.toLowerCase();
    }

    private String aaA()
    {
        char[] chars = this.textValue.toLowerCase().toCharArray();
        int index = chars.length - 1;
        chars[index] = Character.toUpperCase(chars[index]);
        return String.valueOf(chars);
    }

    private String aAa()
    {
        char[] chars = this.textValue.toLowerCase().toCharArray();
        int size = chars.length;
        boolean change = false;
        for (int i =0; i<size; i++)
        {
            if (change)
            {
                chars[i] = Character.toUpperCase(chars[i]);
                change =false;
            }
            else
            {
                change =true;
            }
        }

        return String.valueOf(chars);
    }


    private String AAA()
    {
        return textValue.toUpperCase();
    }


    private String AAa() {
        char[] chars = this.textValue.toUpperCase().toCharArray();
        int index = chars.length - 1;
        chars[index] = Character.toLowerCase(chars[index]);
        return String.valueOf(chars);
    }

    private String Aaa() {
        char[] chars = this.textValue.toLowerCase().toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return String.valueOf(chars);
    }

    private String AaA()
    {
        char[] chars = this.textValue.toUpperCase().toCharArray();
        int size = chars.length;
        boolean change = false;
        for (int i =0; i<size; i++)
        {
            if (change)
            {
                chars[i] = Character.toLowerCase(chars[i]);
                change =false;
            }
            else
            {
                change =true;
            }
        }

        return String.valueOf(chars);
    }

}