package com.example.bro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bro.databinding.ActivityCreateClientBinding;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;

public class createClient extends AppCompatActivity {

    //STORE PATIENT IN FIREBASE
    //Variables
    ActivityCreateClientBinding binding;
    Firebase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);


        //ActionBar Setup

        ImageView backIcon = findViewById(R.id.backIcon);
        ImageView menuIcon = findViewById(R.id.menuIcon);
        TextView activityName = findViewById(R.id.activityName);

        menuIcon.setVisibility(View.INVISIBLE);
        activityName.setText("Client File Creation");

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        goToMain();
            }
        });



    }


    //ActionBar go to Main Menu

    private void goToMain() {
        Intent intent = new Intent(this, main_Menu.class);
        startActivity(intent);
    }



}
