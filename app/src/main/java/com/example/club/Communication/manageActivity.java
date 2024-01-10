package com.example.club.Communication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.club.Communication.Adapter.MyAdapter;
import com.example.club.Database.ManageActivityImple;
import com.example.club.Objects.Activity;
import com.example.club.R;

import java.util.ArrayList;
import java.util.List;


public class manageActivity extends AppCompatActivity {

    private List<Activity> activityList = new ArrayList<>();

    ManageActivityImple db = new ManageActivityImple(this);
    private SharedPreferences sprfMain;
    private SharedPreferences.Editor editorMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        sprfMain = getSharedPreferences("config",0);

        initActivity();
        MyAdapter adapter = new MyAdapter(manageActivity.this, R.layout.manage_activity, activityList);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activity activity = activityList.get(position);

                showNormalDialog(activity.getActivityID());
            }

        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = sprfMain.getInt("type",0);
                if (type == 1) {
                    Intent club = new Intent(manageActivity.this, ClubAccount.class);
                    startActivity(club);
                    finish();
                } else {
                    Intent club = new Intent(manageActivity.this, AdminAccount.class);
                    startActivity(club);
                    finish();
                }
            }
        });

    }

    private void showNormalDialog(String ID) {
        AlertDialog.Builder dialog = new AlertDialog.Builder (this);

        dialog.setTitle("Warning").setMessage("Are you sure to delete?");
        dialog.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete(ID);

                Intent manage = new Intent(manageActivity.this, manageActivity.class);
                startActivity(manage);
                finish();
            }
        });
        //如果取消，就什么都不做，关闭对话框
        dialog.setNegativeButton ("No",null);
        dialog.show ();
    }

    private void initActivity() {
        String username = sprfMain.getString("username","");
        int type = sprfMain.getInt("type",0);

        if (type == 1) {
            activityList = db.query(username);
        } else {
            activityList = db.queryAll();
        }

    }
}