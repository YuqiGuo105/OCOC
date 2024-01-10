package com.example.club.Communication.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.club.Communication.Adapter.ListViewAdapter;
import com.example.club.Database.ManageActivityImple;
import com.example.club.Objects.Activity;
import com.example.club.R;
import com.example.club.Service.activites;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragmentType3 extends Fragment {
    private List<Map<String,Object>> lists;
    private ListView listView;
    private JSONArray allActivity = new JSONArray();
    private Context context;

    public fragmentType3 (Context context) {
        this.context = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.input_hp_activity_listview, container, false);
        listView = (ListView)view.findViewById(R.id.activity_listView);

        List<Map<String, Object>> list = null;
        try {
            list = getData("Match");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setAdapter(new ListViewAdapter(getActivity(), list));
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private List<Map<String, Object>> getData(String type) throws JSONException {
        lists = new ArrayList<>();

        ManageActivityImple database = new ManageActivityImple(context);
        activites operation = new activites();

        allActivity = database.getResults("Activity");
        try {
            allActivity = operation.category(allActivity,"type", type);
        } catch (JSONException e){
            e.printStackTrace();
        }

        for (int i=0; i<allActivity.length(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            JSONObject temp = allActivity.getJSONObject(i);
            String activityID = (String) temp.get("activityid");

            Activity activity = database.search(activityID);

            map.put("title", temp.get("activityName"));
            map.put("image", activity.getImage());
            lists.add(map);
        }

        return lists;
    }
}