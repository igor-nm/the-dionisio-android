package com.the.dionisio.apk.client.model.view.fragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import com.bumptech.glide.Glide;
import com.the.dionisio.apk.client.R;
import com.the.dionisio.apk.client.model.dto.Event;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by igorm on 30/05/2017.
 */

public class EventListAdapter extends BaseAdapter
{
    private Context context;
    private List<Event> eventList;
    private TextView txtName, txtDescription;
    private CircleImageView circleImageBanner;

    public EventListAdapter(Context context, List<Event> eventList)
    {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public int getCount()
    {
        return eventList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return eventList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = View.inflate(context, R.layout.list_event, null);

        txtName = (TextView) view.findViewById(R.id.txtEventName);
        txtDescription = (TextView) view.findViewById(R.id.txtEventDescription);
        circleImageBanner = (CircleImageView) view.findViewById(R.id.circleImageBanner);

        txtName.setText(eventList.get(position).name);
        txtDescription.setText(eventList.get(position).description);
        if(eventList.get(position).urlBanners != null)
        {
            Glide.with(context).load(eventList.get(position).urlBanners.get(0)).into(circleImageBanner);
        }

        view.setTag(eventList.get(position));

        return view;
    }
}
