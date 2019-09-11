package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lecture_Prelim extends AppCompatActivity {


    Button btnPrelimBack,prelimone,prelimtwo,prelimthree,prelimfour,prelimfive,prelimsix,prelimseven,prelimeight,prelimnine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture__prelim);

        btnPrelimBack = (Button) findViewById(R.id.btnPrelimBack);
        btnPrelimBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLecture();
            }
        });

        prelimone = (Button) findViewById(R.id.prelimone);
        prelimone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrelimone();
            }
        });

        prelimtwo = (Button) findViewById(R.id.prelimtwo);
        prelimtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrelimTwo();
            }
        });

        prelimthree = (Button) findViewById(R.id.prelimthree);
        prelimthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrelimThree();
            }
        });

        prelimfour = (Button) findViewById(R.id.prelimfour);
        prelimfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrelimFour();
            }
        });

        prelimfive = (Button) findViewById(R.id.prelimfive);
        prelimfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFive();
            }
        });

    }

    public void openFive() {
        Intent intent = new Intent(this,PrelimFive.class);
        startActivity(intent);
        finish();
    }

    public void openPrelimFour() {
        Intent intent = new Intent(this, Prelimfour.class);
        startActivity(intent);
        finish();
    }

    public void openPrelimThree() {
        Intent intent = new Intent(this, PrelimThree.class);
        startActivity(intent);
        finish();
    }

    public void openPrelimTwo() {
        Intent intent = new Intent(this,prelimtwo.class);
        startActivity(intent);
        finish();
    }

    public void openPrelimone() {
        Intent intent = new Intent(this,Prelimone.class);
        startActivity(intent);
        finish();
    }

    public void openLecture() {
        Intent intent = new Intent(this, Lecture.class);
        startActivity(intent);
        finish();

    }

    public void onBackPressed() {

    }
}
