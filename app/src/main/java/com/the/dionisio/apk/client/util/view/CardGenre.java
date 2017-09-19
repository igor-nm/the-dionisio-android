package com.the.dionisio.apk.client.util.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.the.dionisio.apk.client.R;
import java.util.List;

/**
 * Created by igorm on 15/04/2017.
 */

public class CardGenre
{
    public View setAlpha(View view)
    {
        Drawable backgroundAlpha = view.getBackground();
        backgroundAlpha.setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
        backgroundAlpha.setAlpha(150);

        return view;
    }

    public List<String> checkCard(View v, CardView cardView, TextView textView, ImageView imageView, String controlCheck, List<String> genres)
    {
        final String electronic = "ELECTRONIC", rock = "ROCK", sertanejo = "SERTANEJO", pagode = "PAGODE";
        switch (v.getId())
        {
            case R.id.cardElectronics:
                if(cardView.getTag() == controlCheck)
                {
                    imageView.setImageResource(R.drawable.ic_eletro_checked);
                    genres.add(electronic);
                }
                else
                {
                    imageView.setImageResource(R.drawable.ic_eletro_not_check);
                    genres.remove(electronic);
                }
                break;
            case R.id.cardRock:
                if(cardView.getTag() == controlCheck)
                {
                    imageView.setImageResource(R.drawable.ic_rock_checked);
                    genres.add(rock);
                }
                else
                {
                    imageView.setImageResource(R.drawable.ic_rock_not_check);
                    genres.remove(rock);
                }
                break;
            case R.id.cardSertanejo:
                if(cardView.getTag() == controlCheck)
                {
                    imageView.setImageResource(R.drawable.ic_sertanejo_checked);
                    genres.add(sertanejo);
                }
                else
                {
                    imageView.setImageResource(R.drawable.ic_sertanejo_not_check);
                    genres.remove(sertanejo);
                }
                break;
            case R.id.cardPagode:
                if(cardView.getTag() == controlCheck)
                {
                    imageView.setImageResource(R.drawable.ic_pagode_checked);
                    genres.add(pagode);
                }
                else
                {
                    imageView.setImageResource(R.drawable.ic_pagode_not_check);
                    genres.remove(pagode);
                }
                break;
        }

        if(cardView.getTag() == controlCheck)
        {
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            cardView.setTag("CHECKED");
        }
        else
        {
            textView.setTextColor(Color.parseColor("#A0FFFFFF"));
            cardView.setTag(controlCheck);
        }

        return genres;
    }

    public Boolean validGenre(List<String> genres, Context context)
    {
        if(genres.size() != 0)
        {
            return true;
        }

        Toast.makeText(context, R.string.field_genre, Toast.LENGTH_SHORT).show();
        return false;
    }
}
