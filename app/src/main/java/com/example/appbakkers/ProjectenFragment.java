package com.example.appbakkers;


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
public class ProjectenFragment extends Fragment {
    ImageButton contact_button;
    HomeFragment.HomeFragmentInterface homeFragmentInterface;


    public ProjectenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.projecten, container, false);

        String text= getString(R.string.projecten);
        TextView myTextView = view.findViewById(R.id.pagina_titel);
        myTextView.setText(text);

        contact_button = view.findViewById(R.id.contact_button);

        contact_button.setOnClickListener(new View.OnClickListener() {


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


            homeFragmentInterface = (HomeFragment.HomeFragmentInterface) context;
        } catch (Exception e) {
        }
    }


}
