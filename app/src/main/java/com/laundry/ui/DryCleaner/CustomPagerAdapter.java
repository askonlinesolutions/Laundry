package com.laundry.ui.DryCleaner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.laundry.R;
import com.laundry.Utils.Constant;
import com.laundry.ui.DryCleaner.vo.BannerResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<BannerResponse.DataEntity> bannerList;

    CustomPagerAdapter(Context context, ArrayList<BannerResponse.DataEntity> bannerList) {
        this.bannerList = bannerList;
        mContext = context;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) layout.findViewById(R.id.cloth);

        if (bannerList.get(position).getBanner_image()!= null) {
            Picasso.with(mContext).
                    load(Constant.IMAGE_BASE_URL + bannerList.get(position).getBanner_image()) // URL or file
                    .into(imageView);

//            Glide.with(mContext)
//                    .load("http://webdevelopmentreviews.net/laundry" + bannerList.get(position).getBanner_image())
//                    .into(imageView);

//            Picasso.with(mContext).
//                    load("http://webdevelopmentreviews.net/laundry" + bannerList.get(position).banner_image) // URL or file
//                    .into(imageView);

        }
        else {

        }



        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        ImageView cloth =(ImageView) ite
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
////        ModelObject customPagerEnum = ModelObject.values()[position];
//        return mContext.getPackageName();
//    }

}
