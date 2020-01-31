package com.example.appbakkers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appbakkers.Models.Bakker;

public class AppbakkersInfoFragment extends Fragment {
    ImageButton contact_button;
    Bakker item;
    TextView name, function, info;
    ImageView imageView;
    HomeFragment.HomeFragmentInterface homeFragmentInterface;

    public AppbakkersInfoFragment(Bakker item) {

       this.item = item;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.appbakkers_info, container, false);

        String text= getString(R.string.appbakkers);
        TextView myTextView = view.findViewById(R.id.pagina_titel);
        myTextView.setText(text);

        TextView functie = view.findViewById(R.id.functie);
        functie.setText(item.functie);

        TextView bakkernaam = view.findViewById(R.id.bakkernaam);
        bakkernaam.setText(item.title);





        contact_button = view.findViewById(R.id.contact_button);

        contact_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                homeFragmentInterface.setSecondFragment(new ContactFragment());

                WebView bakkerInfo = view.findViewById(R.id.bakker_info);

                bakkerInfo.loadData(item.content, "text/html", null);
            }
        });

//        name=view.findViewById(R.id.bakkernaam);
//        function=view.findViewById(R.id.functienaam);
//        info=view.findViewById(R.id.project_info);
//        imageView=view.findViewById(R.id.image1);
//        imageView=view.findViewById(R.id.image2);
//
//        name.setText(item.bakkernaam);
//        function.setText(item.functienaam);
//        info.setText(item.info);
//        Glide.with(getActivity())
//                .asBitmap()
//                .load(item.image2)
//                .into(imageView);

       return view;

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
