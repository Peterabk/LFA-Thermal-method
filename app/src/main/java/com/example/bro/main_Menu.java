package com.example.bro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class main_Menu extends AppCompatActivity {

    //VARIABLES
    protected  static final String TAG = "MainMenu";
    protected ImageView searchIcon;
    protected TextView searchTextView;
    protected CardView searchCardView;
    protected ImageView createIcon;
    protected TextView createTextView;
    protected CardView createCardView;
    protected ImageView myAccountIcon;
    protected TextView myAccountTextView;
    protected CardView myAccountCardView;
    protected ImageView logoutIcon;
    protected TextView logoutTextView;
    protected CardView logoutCardView;

    //FUNCTIONS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Log.d(TAG, "The onCreate() event");
        setupUI();
    }

    protected void setupUI(){
       //set variables
        searchIcon= (ImageView) findViewById(R.id.searchIcon);
        searchTextView = (TextView) findViewById(R.id.searchTextView);
        searchCardView = (CardView) findViewById(R.id.searchCardView);

        createIcon= (ImageView) findViewById(R.id.createIcon);
        createTextView = (TextView) findViewById(R.id.createTextView);
        createCardView = (CardView) findViewById(R.id.createCardView);

        myAccountIcon= (ImageView) findViewById(R.id.accountIcon);
        myAccountTextView = (TextView) findViewById(R.id.accountTextView);
        myAccountCardView = (CardView) findViewById(R.id.accountCardView);

        logoutIcon= (ImageView) findViewById(R.id.logoutIcon);
        logoutTextView = (TextView) findViewById(R.id.logoutTextView);
        logoutCardView = (CardView) findViewById(R.id.logoutCardView);

        //set on click listeners
        searchIcon.setOnClickListener(onClickSearchIcon);
        searchTextView.setOnClickListener(onClickSearchIcon);
        searchCardView.setOnClickListener(onClickSearchIcon);

        createIcon.setOnClickListener(onClickCreateIcon);
        createTextView.setOnClickListener(onClickCreateIcon);
        createCardView.setOnClickListener(onClickCreateIcon);

        myAccountIcon.setOnClickListener(onClickMyAccountIcon);
        myAccountTextView.setOnClickListener(onClickMyAccountIcon);
        myAccountCardView.setOnClickListener(onClickMyAccountIcon);

        logoutIcon.setOnClickListener(onClickLogoutIcon);
        logoutTextView.setOnClickListener(onClickLogoutIcon);
        logoutCardView.setOnClickListener(onClickLogoutIcon);

    }

    //GO TO NEXT ACTIVITY FUNCTIONS
    private void gotoSearchClientActivity(){
        Intent searchIntent = new Intent(this,searchClient.class);
        startActivity(searchIntent);
    }
    private void gotoCreateClientActivity(){
        Intent createIntent = new Intent(this,createClient.class);
        startActivity(createIntent);
    }
    private void gotoMyAccountActivity(){
        Intent accountIntent = new Intent(this,myAccount.class);
        startActivity(accountIntent);
    }
    private void gotoLogOutActivity(){
        //return to login_signup page
        Intent logoutIntent = new Intent(this,login_signup.class);
        startActivity(logoutIntent);
    }


    //ON CLICK LISTENER FUNCTIONS
    private View.OnClickListener onClickSearchIcon = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            gotoSearchClientActivity();
        }
    };

    private View.OnClickListener onClickCreateIcon = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            gotoCreateClientActivity();
        }
    };

    private View.OnClickListener onClickMyAccountIcon = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            gotoMyAccountActivity();
        }
    };
    private View.OnClickListener onClickLogoutIcon = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            gotoLogOutActivity();
        }
    };





    //ON START FOR MAIN MENU FUNCTION


    protected void onStart(){
        super.onStart();
        Log.d(TAG, "The onStart() event");
    }


}
