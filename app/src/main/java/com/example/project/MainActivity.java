package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Aloitetaan kotinäkymästä
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new HomeFragment()).commit();

        //alapalkkivalikko
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        //klikkaamalla alavalikosta eri kohteita aukea oma ikkuna
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.profile:
                    fragment = new ProfileFragment();
                    break;
                case R.id.statistics:
                    fragment = new StatisticsFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, fragment).commit();
            return true;
        });
    }
}