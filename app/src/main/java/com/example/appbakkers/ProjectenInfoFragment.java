package com.example.appbakkers;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.example.appbakkers.Models.Project;

import org.json.JSONArray;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProjectenInfoFragment extends Fragment {
    ImageButton contact_button;


    Project item;
    TextView name, function, info;
    ImageView imageView;
    HomeFragment.HomeFragmentInterface homeFragmentInterface;
//    ViewPager viewPager;
    private Context mContext;

    public ProjectenInfoFragment(Project item) {

        this.item = item;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.projecten_info, container, false);

        String text = getString(R.string.projecten);
        TextView myTextView = view.findViewById(R.id.pagina_titel);
        myTextView.setText(text);

        TextView projecttNaam = view.findViewById(R.id.projectnaam);
        projecttNaam.setText(item.title);

        WebView projectInfo = view.findViewById(R.id.project_info);

        projectInfo.loadData(item.content, "text/html", null);



//        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getActivity(), new String[]{item.imageslide1, item.imageslide2, item.imageslide3
//        });
//        viewPager.setAdapter(viewPagerAdapter);
//
//        contact_button = view.findViewById(R.id.contact_button);
//
//        contact_button.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View view) {
//                homeFragmentInterface.setSecondFragment(new ContactFragment());
//            }
//        });
//
//        name = view.findViewById(R.id.projectnaam);
//        function = view.findViewById(R.id.project_beschrijving);
//        info = view.findViewById(R.id.info);
//
//        name.setText(item.projectnaam);
//        function.setText(item.project_beschrijving);
//        info.setText(item.info);

//        private RequestQueue mQueue;
//
//        mQueue = Volley.newRequestQueue(this);
//
//        jsonParse();


        return view;

    }

//    private void jsonParse() {
//
//        String url = "https://localhost/appbakkers-site/wp-json/appbakkers/v1/posts";
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("title");
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject employee = jsonArray.getJSONObject(i);
//
//                                String firstName = title.getString("title");
////                                int age = employee.getInt("age");
////                                String mail = employee.getString("mail");
//
////                                mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//        mQueue.add(request);
//}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {


            homeFragmentInterface = (HomeFragment.HomeFragmentInterface) context;
        } catch (Exception e) {
        }
    }

//    public void bind(final Project item, final RecyclerViewAdapterProjecten.OnItemClickListener listener) {
//
//        Glide.with(mContext)
//                .load(item.medium)
//                .into(thumbnail);
//    }
}
