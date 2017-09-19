package com.the.dionisio.apk.client.model.view.fragments;

import android.content.Context;
import com.the.dionisio.apk.client.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by igorm on 10/06/2017.
 */

public class PopulateData
{
    public static HashMap<String, List<String>> getData(Integer filter, Context context)
    {
        HashMap<String, List<String>> type_filter = new HashMap<>();
        List<String> components = new ArrayList<>();

        switch(filter)
        {
            case R.string.filter_genre:
                components.add("");
                type_filter.put(context.getString(filter), components);
                return type_filter;
            case R.string.filter_date:
                components.add("");
                type_filter.put(context.getString(filter), components);
                return type_filter;
            case R.string.filter_locale:
                components.add("");
                type_filter.put(context.getString(filter), components);
                return type_filter;
            default:
                return null;
        }
    }
}
