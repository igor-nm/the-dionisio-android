package com.the.dionisio.apk.client.model.view.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.view.maskForField.MaskForField;

import java.util.HashMap;
import java.util.List;

/**
 * Created by igorm on 10/06/2017.
 */

public class FilterAdapter extends BaseExpandableListAdapter
{
    private Context context;
    private HashMap<String, List<String>> filter_category;
    private List<String> filter_list;
    private Integer who_parent;
    private EditText edit_childBegin, edit_childEnd;
    static String filterDateBegin = "", filterDateEnd = "";
    private final String MASK_DATE = "##/##/####";

    public FilterAdapter(Context context, HashMap<String, List<String>> filter_category, List<String> filter_list, Integer who_parent)
    {
        this.context = context;
        this.filter_category = filter_category;
        this.filter_list = filter_list;
        this.who_parent = who_parent;
    }

    @Override
    public Object getChild(int parent, int child)
    {
        return filter_category.get(filter_list.get(parent)).get(child);
    }

    @Override
    public long getChildId(int parent, int child)
    {
        return child;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertView, ViewGroup parentView)
    {
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(whoLayoutChild(), parentView,false);
        }

        componentsBelongChild(convertView);

        return convertView;
    }

    @Override
    public int getChildrenCount(int arg0)
    {
        return filter_category.get(filter_list.get(arg0)).size();
    }

    @Override
    public Object getGroup(int arg0)
    {
        return filter_list.get(arg0);
    }

    @Override
    public int getGroupCount()
    {
        return filter_list.size();
    }

    @Override
    public long getGroupId(int arg0)
    {
        return arg0;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView)
    {
        String title_parent = (String) getGroup(parent);

        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.filter_parent, parentView,false);
        }

        TextView component_parent = (TextView) convertView.findViewById(R.id.parent_txt);
        component_parent.setTypeface(null, Typeface.BOLD);
        component_parent.setText(title_parent);
        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1)
    {
        return false;
    }

    public Integer whoLayoutChild()
    {
        switch(who_parent)
        {
            case R.string.filter_genre:
                return R.layout.filter_genre;
            case R.string.filter_date:
                return R.layout.filter_date;
            case R.string.filter_locale:
                return R.layout.filter_locale;
            default:
                return null;
        }
    }

    /**
     * VARIAVES DE CONTROLE DE ATRIBUIÇÃO DE VALOR NOS FILTRO DE DATA [begin, end]
     */
    private boolean NOP_GENRE =  true;
    private boolean NOP_DATE =  true;
    private boolean NOP_LOCATE =  true;
    private boolean CTRL_BEGIN =  true;
    private boolean CTRL_END=  true;

    public void componentsBelongChild(View view)
    {


            switch(who_parent)
        {
            case R.string.filter_genre:
                if(NOP_GENRE)
                {
                    NOP_GENRE = false;
                    CheckBox check_rock = (CheckBox) view.findViewById(R.id.child_rock);
                    CheckBox check_electronic = (CheckBox) view.findViewById(R.id.child_electronic);
                    CheckBox check_sertanejo = (CheckBox) view.findViewById(R.id.child_sertanejo);
                    CheckBox check_pagode = (CheckBox) view.findViewById(R.id.child_pagode);
                }
                break;
            case R.string.filter_date:
                if (NOP_DATE)
                {
                    NOP_DATE =false;
                    edit_childBegin = (EditText) view.findViewById(R.id.editTextDateStart);
                    edit_childEnd = (EditText) view.findViewById(R.id.editTextDateEnd);
                    edit_childBegin.addTextChangedListener(new MaskForField(MASK_DATE, edit_childBegin));
                    edit_childEnd.addTextChangedListener(new MaskForField(MASK_DATE, edit_childEnd));
                }

                //BEGIN
                if (edit_childBegin.getText().toString().length()<10 && !CTRL_BEGIN){CTRL_BEGIN=true;}
                if ( edit_childBegin.getText().toString().length()>=10 && CTRL_BEGIN)
                {
                    CTRL_BEGIN = false;
                    //sua ação
                    filterDateBegin = edit_childBegin.getText().toString();
                }

                //END
                if (edit_childEnd.getText().toString().length()<10 && !CTRL_END){CTRL_END=true;}
                if ( edit_childEnd.getText().toString().length()>=10 && CTRL_END)
                {
                    CTRL_END = false;
                    //sua ação
                    filterDateEnd = edit_childEnd.getText().toString();
                }

                 break;
            case R.string.filter_locale:
                if (NOP_LOCATE)
                {
                    NOP_LOCATE = false;
                    RadioButton radio_childActive = (RadioButton) view.findViewById(R.id.radioLocaleActivated);
                    RadioButton radio_childDeactivated = (RadioButton) view.findViewById(R.id.radioLocaleDeactivated);
                    radio_childDeactivated.setChecked(true);
                }
                break;
            default:
                Toast.makeText(context, "Trouxaaaa", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public String getFieldDateFilter(String date)
    {
        if(date.equals("begin"))
        {
            return filterDateBegin;
        }
        else if(date.equals("end"))
        {
            return filterDateEnd;
        }

        return null;
    }
}
