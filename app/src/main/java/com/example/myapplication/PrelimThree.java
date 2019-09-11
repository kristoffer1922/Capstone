package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrelimThree extends AppCompatActivity {

    Button prelimthreeback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelim_three);

        prelimthreeback = (Button) findViewById(R.id.prelimthreeback);
        prelimthreeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbackthree();
            }
        });
    }

    public void openbackthree() {
        Intent intent =new Intent(this,Lecture_Prelim.class);
        startActivity(intent);
        finish();
    }
    public void onBackPressed() {

    }
}
