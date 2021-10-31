package com.t3g.manvi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.t3g.manvi.R;


public class OnBoardingSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public OnBoardingSliderAdapter(Context context){
        this.context =context;
    }

//  Arrays
    public int[] slide_images ={
        R.drawable.pic_4,
        R.drawable.pic_1,
        R.drawable.pic_2
};
//    public String[] slide_headings ={
//          "EAT",
//          "SLEEP",
//          "Code"
//    };
//
//    public String[] slide_descriptions ={
//            "Lorem ipsum dolor sit amet, 1",
//            "Lorem ipsum dolor sit amet, 2",
//            "Lorem ipsum dolor sit amet, 3"
//    };

//    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.on_boarding_slide_layout, container, false);


        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
//        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
//        TextView slideDescription = (TextView) view.findViewById(R.id.slide_description);

        slideImageView.setImageResource(slide_images[position]);
//        slideHeading.setText(slide_headings[position]);
//        slideDescription.setText(slide_descriptions[position]);
        container.addView(view);
     return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);
    }
}
