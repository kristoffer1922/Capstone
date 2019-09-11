package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrelimFive extends AppCompatActivity {

    Button prelimfiveback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelim_five);

        prelimfiveback = (Button) findViewById(R.id.prelimfiveback);
        prelimfiveback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackFive();
            }
        });


    }

    public void openBackFive() {
        Intent intent =new Intent(this,Lecture_Prelim.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {

    }
}
