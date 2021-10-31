package com.t3g.manvi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t3g.manvi.adapter.OnBoardingSliderAdapter;
import com.t3g.manvi.R;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private OnBoardingSliderAdapter sliderAdapter;
    private int mCurrentPage;
    CardView cardBtn;
    TextView cardText;
    TextView heading;
    TextView subHeading;
    String[] headingText ={
            "Look Good, Feel Good",
            "Find Your Outfits",
            "Your Style, Your Way"
    };
    String[] subHeadingText ={
            "Hating the clothes in your wardrobe?\nExplore hundreds of outfit ideas",
            "Confused about your look? Don't Worry!\nFind the best outfit here!",
            "Create your individual & unique style and\nlook amazing everyday"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        heading = findViewById(R.id.tvHeading);
        subHeading = findViewById(R.id.tvSubHeading);
        heading.setText(headingText[0]);
        subHeading.setText(subHeadingText[0]);

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if(!firstStart){
            startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
        }


        mSlideViewPager = (ViewPager)findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        cardBtn = findViewById(R.id.cardBtn);
        cardText = findViewById(R.id.cardText);

        sliderAdapter = new OnBoardingSliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPage == 2) {
                    SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("firstStart",false);
                    editor.apply();
                    startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));

                }else {
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                }
            }
        });

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }
    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i=0; i< mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.grey));
            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.teal_200));
        }
    }
    ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int positionOffsetPixels) {

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage=i;
            heading.setText(headingText[i]);
            subHeading.setText(subHeadingText[i]);
            if(i == mDots.length - 1){
                cardText.setText("Start Shopping");
                cardBtn.setCardBackgroundColor(getResources().getColor(R.color.teal_200));
                cardBtn.setRadius(90);
            }else{
                cardText.setText("Next");
                cardBtn.setCardBackgroundColor(getResources().getColor(R.color.grey));
                cardBtn.setRadius(90);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
