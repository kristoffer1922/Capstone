package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Prelimone extends AppCompatActivity {


    Button prelimoneback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelimone);

        prelimoneback = (Button) findViewById(R.id.prelimoneback);
        prelimoneback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBack();
            }
        });
    }
        public void openBack() {
            Intent intent = new Intent(this,Lecture_Prelim.class);
            startActivity(intent);
            finish();
    }

    public void onBackPressed() {

    }
}
