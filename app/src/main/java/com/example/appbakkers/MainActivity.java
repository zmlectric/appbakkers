package com.example.appbakkers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeFragmentInterface {
//        private static int SPLASH_TIME_OUT = 4000;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new HomeFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("FRAGMENT"); //  addToBackStack is voor back button
        fragmentTransaction.commit();
    }

    @Override
    public void setSecondFragment(Fragment fragment) {
        setFragment(fragment);
    }

    public void previous(View view) {
        super.onBackPressed(); // app sluit af

    }



}
