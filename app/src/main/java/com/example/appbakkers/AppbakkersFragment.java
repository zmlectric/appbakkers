package com.example.appbakkers;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbakkers.Models.Bakker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppbakkersFragment extends Fragment {
    ImageButton contact_button;
    HomeFragment.HomeFragmentInterface homeFragmentInterface;
    Context mContext;

//    private ArrayList<String> mBakkerNaam = new ArrayList<>();
//    private ArrayList<String> mFunctieNaam = new ArrayList<>();
//    private ArrayList<String> mImageUrls = new ArrayList<>();

    private ArrayList<Bakker> BakkerInfo = new ArrayList<>();

    RecyclerView recyclerx_view;


    public AppbakkersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.appbakkers, container, false);

        String text= getString(R.string.appbakkers);
        TextView myTextView = view.findViewById(R.id.pagina_titel);
        myTextView.setText(text);

        recyclerx_view = view.findViewById(R.id.recyclerx_view);

        contact_button = view.findViewById(R.id.contact_button);

        contact_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                homeFragmentInterface.setSecondFragment(new ContactFragment());
            }
        });

//        initImageBitmaps();

        getData();

        return view;
    }

//    public class Bakker {
//        public String bakkernaam, functienaam, info, image1, image2;
//
//        public Bakker(String bakkernaam, String functienaam, String info, String image1, String image2) {
//            this.bakkernaam = bakkernaam;
//            this.functienaam = functienaam;
//            this.info = info;
//            this.image1 = image1;
//            this.image2 = image2;
//        }
//    }

    private void getData() {

//        final ProgressDialog progressDialog = new ProgressDialog(Activity);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Bakker.url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Bakker bakker = new Bakker();
                        bakker.title=jsonObject.getString("title");
                        bakker.content=jsonObject.getString("content");
                        bakker.functie=jsonObject.getString("functie");

                        JSONObject featured_image=jsonObject.getJSONObject("featured_image");

                        bakker.thumbnail=featured_image.getString("thumbnail");

                        String thumbnail = featured_image.getString("thumbnail");

                        BakkerInfo.add(bakker);
                    } catch (JSONException e) {
                        e.printStackTrace();
//                        progressDialog.dismiss();
                    }
                }
                initRecyclerView();
//                adapter.notifyDataSetChanged();
//                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
//                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(jsonArrayRequest);
    }

    private void initRecyclerView() {

        recyclerx_view.setAdapter(new RecyclerViewAdapterBakkers(getActivity(),BakkerInfo, new RecyclerViewAdapterBakkers.OnItemClickListener(){
            @Override public void onItemClick(Bakker item) {
                homeFragmentInterface.setSecondFragment(new AppbakkersInfoFragment(item));
            }
        }));
        recyclerx_view.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }


    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
        try {

            homeFragmentInterface = (HomeFragment.HomeFragmentInterface) context;
        } catch (Exception e) {
        }
    }

}
