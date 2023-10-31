package com.example.bro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class myAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);


        //ActionBar Setup

        ImageView backIcon = findViewById(R.id.backIcon);
        ImageView menuIcon = findViewById(R.id.menuIcon);
        TextView activityName = findViewById(R.id.activityName);

        menuIcon.setVisibility(View.INVISIBLE);
        activityName.setText("Account Management");

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