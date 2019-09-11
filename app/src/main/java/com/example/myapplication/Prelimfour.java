package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Prelimfour extends AppCompatActivity {

    Button prelimfourback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelimfour);

        prelimfourback = (Button) findViewById(R.id.prelimfourback);
        prelimfourback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackFour();
            }
        });
    }

    public void openBackFour() {
        Intent intent = new Intent(this, Lecture_Prelim.class);
        startActivity(intent);
        finish();

    }
    public void onBackPressed() {

    }
}
