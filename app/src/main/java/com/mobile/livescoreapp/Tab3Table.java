package com.mobile.livescoreapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.livescoreapp.Adapter.FavoiteItemAdapter;
import com.mobile.livescoreapp.Adapter.TableAdapter;
import com.mobile.livescoreapp.DB.DatabaseHelper;
import com.mobile.livescoreapp.LivescoreItem;
import com.mobile.livescoreapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JohnDoe on 5/6/2017.
 */

public class Tab3Table extends Fragment  {

    private static final String tag = "ListDataActivity";
    ListView mListView;

    Button mButton;
    static List<class_table> mItem;
    static class_table table;
    static FavoiteItemAdapter mAdapter;
    static Cursor data;
    public TableAdapter adapter;
    RequestQueue requestQueue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_table, container, false);

        mListView = (ListView) view.findViewById(R.id.listview_table);
        requestQueue = Volley.newRequestQueue(getContext());

        mItem = new ArrayList<>();

        downloadData();
        return view;
    }
    private void downloadData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.8.101/table.json",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("table");
                            mItem.clear();
                            for(int i = 0; i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                String localteam_name = jsonObject1.getString("team_name");
                                String play = jsonObject1.getString("overall_gp");
                                String points = jsonObject1.getString("points");
                                String goal = jsonObject1.getString("gd");
                                String position = jsonObject1.getString("position");





                                table = new class_table(localteam_name,points,goal,play,position);
                                mItem.add(table);
                            }

                            adapter = new TableAdapter(getContext(),mItem);

                            mListView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Error",error.toString());
                    }
                }

        );
        requestQueue.add(jsonObjectRequest);

    }


}
