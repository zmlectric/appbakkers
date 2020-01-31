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
import com.example.appbakkers.Models.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectenFragment extends Fragment {
    ImageButton contact_button;
    HomeFragment.HomeFragmentInterface homeFragmentInterface;
    Context mContext;

    private ArrayList<Project> ProjectInfo = new ArrayList<>();

    RecyclerView recyclerx_view;

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

        recyclerx_view = view.findViewById(R.id.recyclerx_view);

        ArrayList<Bakker> bakkerList = new ArrayList<Bakker>();
        RecyclerViewAdapterBakkers adapter = new RecyclerViewAdapterBakkers(this.getContext(), bakkerList, null);
        recyclerx_view.setAdapter(adapter);

        contact_button = view.findViewById(R.id.contact_button);

        contact_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                homeFragmentInterface.setSecondFragment(new ContactFragment());
            }
        });

        getData();

//        initImageBitmaps();

        return view;
    }

       private void getData() {

//        final ProgressDialog progressDialog = new ProgressDialog(Activity);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Project.url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Project project = new Project();
                        project.title=jsonObject.getString("title");
                        project.content=jsonObject.getString("content");


                        JSONObject featured_image=jsonObject.getJSONObject("featured_image");

                        project.thumbnail=featured_image.getString("thumbnail");

                        String thumbnail = featured_image.getString("thumbnail");

                       ProjectInfo.add(project);
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



//    private void initImageBitmaps() {
//
//        ProjectInfo.clear();
//        ProjectInfo.add(new Project("Zwolle Onderneemt! De app voor alle starters in Zwolle", "Zwolle Onderneemt! is de app voor alle starters in Zwolle", "Op vrijdag 16 november werd tijdens de Dag van de Ondernemer de app Zwolle Onderneemt! gelanceerd in het MAC3PARK stadion (PEC Zwolle). De app is een hulpmiddel voor startende ondernemers in de gemeente. De speciale startersapp is een initiatief van de gemeente Zwolle, om startende ondernemers een centrale plek te bieden waar ze alle informatie vinden om hun bedrijf een vliegende start te geven.\n" +
//                "\n" +
//                "In de app kan een startende onderneming op eenvoudige manier informatie vinden over organisaties die starters ondersteunen, interessante evenementen, contactpersonen bij de gemeente en relevant nieuws. De gemeente Zwolle werkt bij dit initiatief samen met de gemeente Raalte. De gebruiker van de app kan daarom ook relevante informatie vinden van de gemeente Raalte en eventueel door middel van filters voorkeuren instellen, zodat snel de meest relevante informatie zichtbaar is.\n" +
//                "\n" +
//                "De gemeente Raalte en Zwolle kunnen zelf alle content in de app beheren en dus ook gebruik maken van elkaars content, waarmee de samenwerking op het vlak van startende ondernemers in de regio verder wordt ingevuld.\n" +
//                "\n" +
//                "Lees hier verder over het initiatief:", "https://appbakkers.nl/wp-content/uploads/2019/04/gemeenteZwolle-home-700x400.jpg", "https://appbakkers.nl/wp-content/uploads/2019/03/gemeenteZwolle-organisaties-300x192.jpg", "https://appbakkers.nl/wp-content/uploads/2019/03/gemeenteZwolle-evenementen-300x192.jpg", ""));
//
//        ProjectInfo.add(new Project("Appscape - de app voor escape room spelers", "Ben je fanatieke escape room speler en wil je graag weten hoe goed jij bent in het kraken van de rooms? Dan is Appscape de app die je moet hebben", "Twee jonge ondernemers uit Zwolle, zelf fanatiek escape room spelers, zagen een gat in de markt voor escape room eigenaren én spelers. Om hun idee te toetsen hebben ze eerst uitgebreid onderzoek gedaan onder eigenaren en spelers. Die input hebben ze omgezet naar Appscape, de app voor fanatieke spelers van escape rooms.\n" +
//                "\n" +
//                "Naast een compleet overzicht van alle escape rooms in Nederland (en Belgie) geeft de app je de mogelijkheid om de tijd die je nodig had om een room uit te komen te noteren. Op basis van beacons die bij de escape rooms liggen weet de app waar je bent ingecheckt om te spelen. De escape rooms kunnen aan deze beacons acties koppelen, zodat ze spelers kunnen overhalen binnen te komen en die ene kamer die ze nog niet hebben gespeeld ook nog te proberen.\n" +
//                "\n" +
//                "Voor de echte competitieve spelers is er uiteraard een ranglijst met de snelste spelers en zij kunnen daarnaast ook badges verzamelen door speciale kamers te spelen of alle kamers met een bepaald thema uit te spelen.\n" +
//                "\n" +
//                "Lees hier het verhaal van Appscape:", "https://appbakkers.nl/wp-content/uploads/2019/03/appscape-badges-1-700x400.jpg", "https://appbakkers.nl/wp-content/uploads/2019/03/appscape-menu-300x192.jpg", "https://appbakkers.nl/wp-content/uploads/2019/03/appscape-badges-300x192.jpg", ""));
//
//        initRecyclerView();
//    }

    private void initRecyclerView() {

        recyclerx_view.setAdapter(new RecyclerViewAdapterProjecten(getActivity(),ProjectInfo, new RecyclerViewAdapterProjecten.OnItemClickListener() {
            @Override public void onItemClick(Project item) {
                homeFragmentInterface.setSecondFragment(new ProjectenInfoFragment(item));
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
