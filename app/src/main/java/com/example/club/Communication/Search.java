package com.example.club.Communication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.club.Communication.Adapter.ResultAdapter;
import com.example.club.Database.DatabaseHelper;
import com.example.club.Database.ManageActivityImple;
import com.example.club.MainActivity;
import com.example.club.Objects.Activity;
import com.example.club.R;
import com.example.club.Service.activites;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private ImageButton mBtnPop;
    private Button mBtnDialog;
    private PopupWindow mPop;
    private ImageButton m4;
    private Button m5;
    private SearchView sv;
    private String[] mStrs = {"kk", "kk", "wskx", "wksx"};
    private EditText mSearch;

    private String category = "activityName";
    private ArrayList<Activity> result = new ArrayList<Activity>();
    private String search = "";

    static final String[] PROJECTION = new String[] { ContactsContract.RawContacts._ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY };

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //
        mSearch = (EditText) findViewById(R.id.search1);
        m5=(Button)findViewById(R.id.btn_next);
        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search =  mSearch.getText().toString().trim();

                result = new ArrayList<Activity>();
                try {
                    result = search(search, category);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ListView lv = (ListView)findViewById(R.id.lv);

                System.out.println(result.size());

                ResultAdapter adapter = new ResultAdapter(Search.this, R.layout.moban,result);
                lv.setAdapter(adapter);
            }
        });
        m4=(ImageButton)findViewById(R.id.return1);
        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, aHomePage.class);
                startActivity(intent);
            }
        });

        //pop button
        mBtnPop=(ImageButton)findViewById(R.id.btn_popa);
        mBtnPop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                View view=getLayoutInflater().inflate(R.layout.layout_pop,null);
                mBtnDialog=(Button)view.findViewById(R.id.club);
                mBtnDialog.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        category = "postUserID";
                    }
                });
                mBtnDialog=(Button)view.findViewById(R.id.act);
                mBtnDialog.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        category = "activityName";
                    }
                });

                mPop=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPop.setOutsideTouchable(true);
                mPop.setFocusable(true);
                mPop.showAsDropDown(mBtnPop);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private ArrayList<Activity> search (String category, String key) throws JSONException {
        ArrayList<Activity> activities = new ArrayList<Activity>();

        ManageActivityImple database = new ManageActivityImple(Search.this);
        activites operation = new activites();

        JSONArray result = database.getResults("Activity");
        result = operation.search(result, key, category);

        for (int i=0; i<result.length(); i++) {
            JSONObject temp = result.getJSONObject(i);

            Bitmap photo = database.search(temp.getString("activityid")).getImage();

            Activity activity = new Activity(temp.getString("activityid"), temp.getInt("postByType"),
                    temp.getString("postUserID"), temp.getString("activityName"), temp.getString("location"),
                    temp.getString("date"), temp.getString("time"), temp.getString("type"),
                    temp.getString("description"), temp.getInt("capacity"), photo,
                    temp.getInt("likes"), temp.getString("detailsurl")
            );

            activities.add(activity);
        }

        return activities;
    }

}