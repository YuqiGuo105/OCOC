package com.example.club.Communication.DetailPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.club.R;

public class detailClub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_club);

        String clubname = getIntent().getStringExtra("clubid");

        System.out.println(clubname);
    }
}