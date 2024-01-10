package com.example.club.Communication.Noticification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.club.R;

public class register_club extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_club2);

        TextView name = findViewById(R.id.clubname);
        TextView key = findViewById(R.id.clubpassword);
        TextView mail = findViewById(R.id.clubemail);
        TextView type = findViewById(R.id.type);

        String clubname = getIntent().getStringExtra("clubname");
        String clubpassword = getIntent().getStringExtra("clubpassword");
        String clubemail = getIntent().getStringExtra("clubemail");
        String club_type = getIntent().getStringExtra("type");

        name.setText(clubname);
        key.setText(clubpassword);
        mail.setText(clubemail);
        type.setText(club_type);

    }
}