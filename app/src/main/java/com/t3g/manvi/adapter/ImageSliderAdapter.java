package com.t3g.manvi.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.t3g.manvi.R;

public class ImageSliderAdapter extends
        SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH> {

    private int mCount;

    public ImageSliderAdapter(Context context) {
//        required empty constructor
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oneforall.page.link/link_" + position));
                v.getContext().startActivity(i);
            }
        });


//        switch (position) {
//            case 0:
//                Glide.with(viewHolder.itemView)
//                        .load("https://oneforall.page.link/slide_0")
//                        .signature(new ObjectKey(String.valueOf(System.currentTimeMillis()/(60*60*1000))))
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//
//                break;
//            case 1:
//                Glide.with(viewHolder.itemView)
//                        .load("https://oneforall.page.link/slide_1")
//                        .signature(new ObjectKey(String.valueOf(System.currentTimeMillis()/(60*60*1000))))
////          Refresh Images every hour
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                break;
//            case 2:
//                Glide.with(viewHolder.itemView)
//                        .load("https://oneforall.page.link/slide_2")
//                        .signature(new ObjectKey(String.valueOf(System.currentTimeMillis()/(60*60*1000))))
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                break;
//            case 3:
//                Glide.with(viewHolder.itemView)
//                        .load("https://oneforall.page.link/slide_3")
//                        .signature(new ObjectKey(String.valueOf(System.currentTimeMillis()/(60*60*1000))))
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                break;
////            case 4:
////                Glide.with(viewHolder.itemView)
////                        .load("https://oneforall.page.link/slide_4")
////                        .signature(new ObjectKey(String.valueOf(System.currentTimeMillis()/(60*60*1000))))
////                        .fitCenter()
////                        .into(viewHolder.imageViewBackground);
////                break;
//
//            default:
//                Glide.with(viewHolder.itemView)
//                        .load(R.drawable.slider_default)
//                        .signature(new ObjectKey(String.valueOf(System.currentTimeMillis()/(60*60*1000))))
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                break;
//
//        }
        for(int i=0; i<=position; i++){
            Glide.with(viewHolder.itemView)
                    .load("https://oneforall.page.link/slide_" + i)
                    .signature(new ObjectKey(String.valueOf(System.currentTimeMillis()/(60*60*1000))))
                    .fitCenter()
                    .into(viewHolder.imageViewBackground);
        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}

