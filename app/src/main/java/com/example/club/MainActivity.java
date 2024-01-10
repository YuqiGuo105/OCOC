package com.example.club;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.club.Communication.LogIn;
import com.example.club.Database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private static String CALENDER_URL = "content://com.android.calendar/calendars"; //日历用户的URL
    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";//事件的URL
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders"; //事件提醒URL

    private static int SPLASH_SCREEN=3000;
    Animation topAnim, bottomAnim;
    ImageView image1, image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation

        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        //hooks
        image1=findViewById(R.id.logoo);
        image2=findViewById(R.id.box);

        image1.setAnimation(topAnim);
        image2.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}