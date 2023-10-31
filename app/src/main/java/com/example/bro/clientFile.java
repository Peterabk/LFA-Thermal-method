package com.example.bro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class clientFile extends AppCompatActivity {

    //FOR TAB
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MyFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_file);

        //ActionBar Setup

        ImageView backIcon = findViewById(R.id.backIcon);
        ImageView menuIcon = findViewById(R.id.menuIcon);
        TextView activityName = findViewById(R.id.activityName);

        menuIcon.setVisibility(View.VISIBLE);
        activityName.setText("Client File");

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


        //FOR TAB
        tabLayout = findViewById(R.id.clientFile_tabLayout);
        viewPager = findViewById(R.id.clientFile_viewpager);


        tabLayout.addTab(tabLayout.newTab().setText("General info"));
        tabLayout.addTab(tabLayout.newTab().setText("Interventions"));
        tabLayout.addTab(tabLayout.newTab().setText("Medical Records"));


        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new MyFragmentAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    //ActionBar go to Back and to Main Menu

    private void goBack() {
        Intent intent = new Intent(this, researchResults.class);
        startActivity(intent);
    }

    private void goToMain() {
        Intent intent = new Intent(this, main_Menu.class);
        startActivity(intent);
    }
}