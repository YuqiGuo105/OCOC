package com.example.club.Communication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.club.Communication.Fragment.fragmentType1;
import com.example.club.Communication.Fragment.fragmentType2;
import com.example.club.Communication.Fragment.fragmentType3;
import com.example.club.Communication.Fragment.fragmentType4;
import com.example.club.Communication.Fragment.fragmenta;
import com.example.club.Communication.Fragment.fragmentb;
import com.example.club.Communication.Fragment.fragmentc;
import com.example.club.Communication.Fragment.fragmentd;
import com.example.club.Communication.Fragment.fragmente;
import com.example.club.Communication.Fragment.fragmentf;
import com.example.club.Communication.Fragment.fragmentg;
import com.example.club.Database.DatabaseHelper;
import com.example.club.Database.ManageActivityImple;
import com.example.club.Objects.Activity;
import com.example.club.Objects.Club;
import com.example.club.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class activity extends AppCompatActivity {
    private FragmentManager fm=null ;
    private FragmentTransaction transaction =null ;
    private Button mBtnconfirm4,mBtnCancel4;
    private Button b1=null;
    private Button b2=null;
    private Button b3=null;
    private Button b4=null;
    private Button b5=null;
    private Button b6=null;
    private Button b7=null;
    private Button bt1=null;
    private Button bt2=null;
    private Button bt3=null;
    private Button bt4=null;


    private fragmenta f1;
    private fragmentb f2;
    private fragmentc f3;
    private fragmentd f4;
    private fragmente f5;
    private fragmentf f6;
    private fragmentg f7;

    private fragmentType1 ft1;
    private fragmentType2 ft2;
    private fragmentType3 ft3;
    private fragmentType4 ft4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_activity);

        //fragment
        //club type
        b1=(Button)findViewById(R.id.bt1);
        b2=(Button)findViewById(R.id.bt2);
        b3=(Button)findViewById(R.id.bt3);
        b4=(Button)findViewById(R.id.bt4);
        b5=(Button)findViewById(R.id.bt5);
        b6=(Button)findViewById(R.id.bt6);
        b7=(Button)findViewById(R.id.bt7);
        //activity type
        bt1=(Button)findViewById(R.id.type_bt1);
        bt2=(Button)findViewById(R.id.type_bt2);
        bt3=(Button)findViewById(R.id.type_bt3);
        bt4=(Button)findViewById(R.id.type_bt4);
        fm = getSupportFragmentManager();

        setDefaultFragment();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                f1 = new fragmenta(activity.this);
                transaction.replace(R.id.fl_club,f1);
                transaction.commit();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transaction = fm.beginTransaction();
                f2 = new fragmentb(activity.this);
                transaction.replace(R.id.fl_club,f2);
                transaction.commit();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                f3=new fragmentc(activity.this);
                transaction.replace(R.id.fl_club,f3);
                transaction.commit();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                f4=new fragmentd(activity.this);
                transaction.replace(R.id.fl_club,f4);
                transaction.commit();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transaction = fm.beginTransaction();
                f5=new fragmente(activity.this);
                transaction.replace(R.id.fl_club,f5);
                transaction.commit();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                f6=new fragmentf(activity.this);
                transaction.replace(R.id.fl_club,f6);
                transaction.commit();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                f7=new fragmentg(activity.this);
                transaction.replace(R.id.fl_club,f7);
                transaction.commit();
            }
        });

        // type
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                ft1=new fragmentType1(activity.this);
                transaction.replace(R.id.fl_club,ft1);
                transaction.commit();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transaction = fm.beginTransaction();
                ft2=new fragmentType2(activity.this);
                transaction.replace(R.id.fl_club,ft2);
                transaction.commit();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                ft3=new fragmentType3(activity.this);
                transaction.replace(R.id.fl_club,ft3);
                transaction.commit();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fm.beginTransaction();
                ft4=new fragmentType4(activity.this);
                transaction.replace(R.id.fl_club,ft4);
                transaction.commit();
            }
        });


        //below
        mBtnCancel4=(Button)findViewById(R.id.radio_button_home);
        mBtnCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.this, aHomePage.class);
                startActivity(intent);

            }
        });

        mBtnCancel4=(Button)findViewById(R.id.radio_button_profile);
        mBtnCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.this, UserAccount.class);
                startActivity(intent);

            }
        });

    }


    private void setDefaultFragment()
    {
        transaction = fm.beginTransaction();
        f1=new fragmenta(activity.this);
        transaction.replace(R.id.fl_club,f1);
        transaction.commit();
    }

}

