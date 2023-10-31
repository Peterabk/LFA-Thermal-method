package com.example.bro;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapter extends FragmentStateAdapter {

    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position ==1){
            return new clientFile_interventions_Fragment();
        } else if (position==2) {
            return new clientFile_medical_records_Fragment();
        }

        return new clientFile_general_info_Fragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
