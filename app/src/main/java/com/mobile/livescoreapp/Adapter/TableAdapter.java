package com.mobile.livescoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.livescoreapp.R;
import com.mobile.livescoreapp.class_table;
import com.mobile.livescoreapp.eventsItem;

import java.util.List;

/**
 * Created by JohnDoe on 5/31/2017.
 */

public class TableAdapter extends BaseAdapter {
    public Context mContext;
    public LayoutInflater inflater;
    public List<class_table> mItem;
    public static int i;

    public TableAdapter(Context mContext, List<class_table> mItem) {
        this.mContext = mContext;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItem = mItem;
        i=1;
    }

    @Override
    public int getCount() {
        return mItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view ==null){
            view = inflater.inflate(R.layout.table_item,null);
        }
        TextView number = (TextView)view.findViewById(R.id.text_number);
        TextView team_name = (TextView)view.findViewById(R.id.text_team_name);
        TextView total_play = (TextView)view.findViewById(R.id.text_play);
        TextView goal = (TextView)view.findViewById(R.id.text_goal);
        TextView point = (TextView)view.findViewById(R.id.text_point);

        number.setText(mItem.get(position).position);
        team_name.setText(mItem.get(position).team_name);
        total_play.setText(mItem.get(position).play);
        goal.setText(mItem.get(position).goal);
        point.setText(mItem.get(position).point);
        i++;


        return view;
    }
    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

}
