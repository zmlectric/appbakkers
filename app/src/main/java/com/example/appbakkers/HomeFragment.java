package com.example.appbakkers;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment {

    ImageButton projecten_button;
    ImageButton appbakkers_button;
    ImageButton contact_button_home;
    ImageButton contact_button;
    HomeFragmentInterface homeFragmentInterface;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home, container, false);

        projecten_button = view.findViewById(R.id.projecten_button);

        appbakkers_button = view.findViewById(R.id.appbakkers_button);

        contact_button_home = view.findViewById(R.id.contact_button_home);

        contact_button = view.findViewById(R.id.contact_button);

        projecten_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                homeFragmentInterface.setSecondFragment(new ProjectenFragment());
            }
        });

        appbakkers_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                homeFragmentInterface.setSecondFragment(new AppbakkersFragment());
            }
        });


        contact_button_home.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                homeFragmentInterface.setSecondFragment(new ContactFragment());
            }
        });





        return view;

    }

    public interface HomeFragmentInterface {
        public void setSecondFragment(Fragment fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {


            homeFragmentInterface = (HomeFragmentInterface) context;
        } catch (Exception e) {
        }
    }


}



