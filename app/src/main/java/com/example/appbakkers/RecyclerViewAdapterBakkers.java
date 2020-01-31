package com.example.appbakkers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.appbakkers.Models.Bakker;

import java.util.List;

public class RecyclerViewAdapterBakkers extends RecyclerView.Adapter<RecyclerViewAdapterBakkers.ViewHolder> {

    HomeFragment.HomeFragmentInterface homeFragmentInterface;

//    private static final String TAG = "RecyclerViewAdapterBakkers";

    //    private ArrayList<String> mBakkerNaam = new ArrayList<>();
//    private ArrayList<String> mFunctieNaam = new ArrayList<>();
//    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private List<Bakker> BakkerInfo;


    private final OnItemClickListener listener;

    public RecyclerViewAdapterBakkers(Context mContext, List<Bakker> items, OnItemClickListener listener) {
        this.BakkerInfo = items;
        this.listener = listener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appbakkers_layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);


        holder.context = mContext;

//        bakkerNaam = itemView.findViewById(R.id.bakkernaam);

        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(BakkerInfo.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return BakkerInfo.size();
        // + return mFunctieNaam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView bakkerNaam;
        TextView functieTextView;
        LinearLayout parentLayout;
        Context context;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bakkerNaam = itemView.findViewById(R.id.bakkernaam);
            functieTextView = itemView.findViewById(R.id.functie);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }


        public void bind(final Bakker item, final OnItemClickListener listener) {

//            try {
//                InputStream is = context.getAssets().open("bakkers.html");
//
//                int size = is.available();v
//
//                byte[] buffer = new byte[size];
//                is.read(buffer);
//                is.close();
//
//                String str = new String(buffer);
//                String temp = item.content.split("<!--more-->")[0];
////                str = str.replace("test", item.content);
//
//
//                textview.setText(Html.fromHtml(temp, 0));
//
//            } catch (Exception ex) {
//                Log.e("Volley", ex.toString());
//            }


            bakkerNaam.setText(item.title);
            functieTextView.setText(item.functie);


            Glide.with(mContext)
                    .load(item.thumbnail)
                    .into(thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

//        @Override
//        public void onClick(View view) {
//
//            homeFragmentInterface.setSecondFragment(new ContactFragment());
//
//        }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Bakker item);
    }

}


