package com.example.appbakkers;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "ImageViewPage";
    Context mContext;
    LayoutInflater mLayoutInflater;
    String[] mResources;

    public ViewPagerAdapter(Context context, String[] resources) {
        mContext = context;
        mResources = resources;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG,
                "instantiateItem() called with: " + "container = [" + container + "], position = [" + position + "]");

        View itemView = mLayoutInflater.inflate(R.layout.item_pager, container, false);

        Log.d(TAG, "load in gallery:" + mResources[position] + "#end");
        final ImageView ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);

        if (!mResources[position].equals("")){

//            Glide.with(mContext).load("http://10.240.0.116/appbakkers-site/wp-json/appbakkers/v1/posts")
//                    .thumbnail(Glide.with(mContext).load("http://10.240.0.116/appbakkers-site/wp-json/appbakkers/v1/posts"))
//                    .apply(requestOptions).into(ivPhoto);

            Glide.with(mContext)
                    .load("http://10.240.0.116/appbakkers-site/wp-json/appbakkers/v1/posts")
                    .into(ivPhoto);


//            Glide.with(this).load(image_url).apply(options).into(imageView);

//            .getthumbnail();


//            Glide.with(mContext)
//
//                    .load(mResources[position].trim())
//                    .into(ivPhoto);
//
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d(TAG, "destroyItem() called with: " + "container = [" + container + "], position = [" + position
                + "], object = [" + object + "]");
        container.removeView((LinearLayout) object);
    }
}


//    private Context context;
//    private LayoutInflater layoutInflater;
//    private ArrayList<String> image;
//
//
//    @Override
//    public int getCount() {
//        return image.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    public ViewPagerAdapter(Context mContext, ArrayList<String> items) {
//        this.image = items;
//
//    }
//
//    public Object instantiateItem(ViewGroup container, int position) {
//
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.projecten_info, null);
//        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//        imageView.setImageResource(image.get(position)); // glider
//
//
////        image1.setVisibility(View.VISIBLE);
////
//        Glide.with(mContext)
//                .asBitmap()
//                .load(image.get(position))
//                .into(imageslide);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                listener.onItemClick(item);
//            }
//        });
//
//        ViewPager vp = (ViewPager) container;
//        vp.addView(view, 0);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//
//        ViewPager vp = (ViewPager) container;
//        View view = (View) object;
//        vp.removeView(view);
//    }
//}
