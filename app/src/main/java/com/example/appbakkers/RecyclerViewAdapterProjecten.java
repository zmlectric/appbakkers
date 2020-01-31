package com.example.appbakkers;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.Image;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.appbakkers.Models.Project;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterProjecten extends RecyclerView.Adapter<RecyclerViewAdapterProjecten.ViewHolder> {

    HomeFragment.HomeFragmentInterface homeFragmentInterface;

//    private static final String TAG = "RecyclerViewAdapterBakkers";

    //    private ArrayList<String> mBakkerNaam = new ArrayList<>();
//    private ArrayList<String> mFunctieNaam = new ArrayList<>();
//    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private List<Project> ProjectInfo;



    private final OnItemClickListener listener;

    public RecyclerViewAdapterProjecten(Context mContext,List<Project> items, OnItemClickListener listener) {
        this.ProjectInfo= items;
        this.listener = listener;
        this.mContext = mContext;
    }

    @NonNull
    @Override



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projecten_layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.context = mContext;

//        mmAdapter = new Adapter(msgList);
//        mrecyclerView.setAdapter(mmAdapter);mmAdapter = new Adapter(msgList);

//        RecyclerViewAdapterProjecten = new RecyclerView.Adapter<>()



        return holder;
    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(ProjectInfo.get(position), listener);




    }

    @Override
    public int getItemCount() {
        return ProjectInfo.size();
        // + return mFunctieNaam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textview;
        ImageView thumbnail;
        TextView projectNaam;
        Context context;

//        WebView projectBeschrijving;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image1 = itemView.findViewById(R.id.image1);

            projectNaam = itemView.findViewById(R.id.projectNaam);
//            projectBeschrijving = itemView.findViewById(R.id.project_beschrijving);
            parentLayout = itemView.findViewById(R.id.parent_layout);


            textview = (TextView) itemView.findViewById(R.id.project_beschrijving);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

//            webview.getSettings().setJavaScriptEnabled(true);

//            webview.setWebViewClient(new WebViewClient() {
//
//
//                @Override
//                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                    super.onReceivedError(view, request, error);
//                    Log.e("Volley", error.toString());
//                }
//
//                @Override
//                public void onPageFinished(WebView view, String url) {
//
//                        Log.e("Volley", "test");
//
//                }
//            });

//            webview.loadUrl("https://www.google.nl");
//            webview.setHorizontalScrollBarEnabled(false);
//            webview.setVerticalScrollBarEnabled(false);

        }


        public void bind(final Project item, final OnItemClickListener listener) {

            try {
                InputStream is = context.getAssets().open("projecten.html");

                int size = is.available();

                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                String str = new String(buffer);
//                str = str.replace("test", item.content);


                textview.setText(Html.fromHtml(item.content, 0));



            } catch (Exception ex) {
                Log.e("Volley", ex.toString());
            }



            projectNaam.setText(item.title);




            Glide.with(mContext)
                    .load(item.thumbnail)
                    .into(thumbnail);

            // iets met item net als voor title en content


//            projectBeschrijving.setText(item.content);
//            if(item.image1=="") {
//                image1.setVisibility(View.GONE);
//                return;
//            }
//
//            image1.setVisibility(View.VISIBLE);
//
//            Glide.with(mContext)
//                    .asBitmap()
//                    .load(item.image1)
//                    .into(image1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Project item);
    }
}
