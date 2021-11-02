package com.t3g.manvi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.t3g.manvi.R;

public class MainActivity extends AppCompatActivity {

    SliderView imageSliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Setting Image Slider resource
        imageSliderView = findViewById(R.id.imageSlider);

//        End of Image Slider
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this);
        imageSliderAdapter.setCount(5);

        imageSliderView.setSliderAdapter(imageSliderAdapter);
        imageSliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        imageSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSliderView.setIndicatorSelectedColor(Color.WHITE);
        imageSliderView.setIndicatorUnselectedColor(Color.GRAY);
        imageSliderView.startAutoCycle();

        imageSliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int slidePosition) {
                imageSliderView.setCurrentPagePosition(slidePosition);
            }
        });


//        bla bla
//        End of Image Slider
    }
}