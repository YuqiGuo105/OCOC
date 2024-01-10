package com.example.club.Communication.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.club.Communication.Adapter.ListViewAdapter;
import com.example.club.Database.DatabaseHelper;
import com.example.club.Database.ManageActivityImple;
import com.example.club.R;
import com.example.club.Service.activites;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragmentc extends Fragment {
    private List<Map<String, Object>> lists;
    private ListView listView;

    private JSONArray allClub = new JSONArray();
    private Context context;

    public fragmentc (Context context){
        this.context = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.input_hp_activity_listview, container, false);
        listView = (ListView)view.findViewById(R.id.activity_listView);

        List<Map<String, Object>> list = null;
        try {
            list = getData("Community Service Clubs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setAdapter(new ListViewAdapter(getActivity(), list));

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private List<Map<String, Object>> getData(String type) throws JSONException {
        lists = new ArrayList<>();

        DatabaseHelper db = new DatabaseHelper(context);
        ManageActivityImple database = new ManageActivityImple(context);
        activites operation = new activites();

        allClub = database.getResults("ClubAccount");
        try {
            allClub = operation.category(allClub,"type", type);
        } catch (JSONException e){
            e.printStackTrace();
        }

        for (int i=0; i<allClub.length(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            JSONObject temp = allClub.getJSONObject(i);
            Bitmap photo = db.getImage((String) temp.get("clubid"));

            map.put("title", temp.get("clubid"));
            map.put("image", photo);
            lists.add(map);
        }

        return lists;
    }
}
