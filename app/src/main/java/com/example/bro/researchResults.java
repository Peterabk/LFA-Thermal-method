package com.example.bro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class researchResults extends AppCompatActivity {

    protected  static final String TAG = "Research Results";
    protected TableRow row1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_results);
        Log.d(TAG, "The onCreate() event");
        setupUI();


        //ActionBar Setup

        ImageView backIcon = findViewById(R.id.backIcon);
        ImageView menuIcon = findViewById(R.id.menuIcon);
        TextView activityName = findViewById(R.id.activityName);

        menuIcon.setVisibility(View.VISIBLE);
        activityName.setText("Search Results");

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain();
            }
        });
    }



    protected void setupUI(){
        row1=(TableRow) findViewById(R.id.row1PatientResult);
        row1.setOnClickListener(onClickrow1Cfile);
    }


    private void gotoClientFileActivity(){
        Intent CFileIntent = new Intent(this,clientFile.class);
        startActivity(CFileIntent);
    }

    private View.OnClickListener onClickrow1Cfile = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            gotoClientFileActivity();
        }
    };

    private void goBack() {   //Action Bar Return Arrow
        Intent intent = new Intent(this, researchResults.class);
        startActivity(intent);
    }

    private void goToMain() {   //Action Bar Return to Menu
        Intent intent = new Intent(this, main_Menu.class);
        startActivity(intent);
    }
}