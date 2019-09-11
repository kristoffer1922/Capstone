package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lecture extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnPrelim,btnMidterm, btnFinal ;
    EditText editPDFname;
    Button btn_upload;

    StorageReference storageReference;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        editPDFname = (EditText) findViewById(R.id.txt_pdfName);
        btn_upload = (Button) findViewById(R.id.btn_upload);

        btnFinal = (Button) findViewById(R.id.btnFinal);
        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFinal();
            }
        });
        btnMidterm = (Button) findViewById(R.id.btnMidterm);
        btnMidterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMidterm();
            }
        });
        btnPrelim = (Button) findViewById(R.id.btnPrelim);
        btnPrelim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrelim();
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");




        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selectPDFFile();
            }
        });
    }

    private void selectPDFFile() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File "),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == 1 && resultCode == RESULT_OK
                && data !=null && data.getData()!=null){


            uploadPDFFile(data.getData());

        }
    }

    private void uploadPDFFile(Uri data) {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading ...");
        progressDialog.show();




        StorageReference reference = storageReference.child("uploads/"+System.currentTimeMillis()+ ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uri.isComplete());
                        Uri url = uri.getResult();

                        uploadPDF uploadPDF = new uploadPDF(editPDFname.getText().toString(),url.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                        Toast.makeText(Lecture.this,"File Uploaded ",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();



                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {


                double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getBytesTransferred();
                progressDialog.setMessage("Uploaded"+ (int)progress+"%");

            }
        });
    }

    public void btn_action(View view) {

        startActivity(new Intent(getApplicationContext(), View_PDF_Files.class));

    }





    public void openFinal() {
        Intent intent = new Intent(this, Lecture_Final.class);
        startActivity(intent);
        finish();
    }

    public void openMidterm() {
        Intent intent = new Intent(this, Lecture_Midterm.class);
        startActivity(intent);
        finish();

    }

    public void openPrelim() {
        Intent intent = new Intent(this,Lecture_Prelim.class);
        startActivity(intent);
        finish();



    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lecture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(Lecture.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_lecture:
                Intent i= new Intent(Lecture.this,Lecture.class);
                startActivity(i);
                break;
            case R.id.nav_assessment:
                Intent g= new Intent(Lecture.this,AssessmentTask.class);
                startActivity(g);
                break;

            // after this lets start copying the above.
            // FOLLOW MEEEEE>>>
            //copy this now.
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
