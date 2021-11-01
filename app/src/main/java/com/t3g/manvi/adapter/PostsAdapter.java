package com.t3g.manvi.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3g.manvi.activity.WebActivity;
import com.t3g.manvi.modal.ProductModal;
import com.t3g.manvi.R;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {


    List<ProductModal> allPosts;

    public PostsAdapter(List<ProductModal> allPosts){
        this.allPosts = allPosts;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.postTitle.setText(allPosts.get(position).getTitle());
        holder.postOriginalPrice.setText(allPosts.get(position).getOriginalPrice());
        holder.postDiscountedPrice.setText(allPosts.get(position).getDiscountedPrice());
        holder.postRating.setRating(allPosts.get(position).getRating());
        Glide.with(holder.postImage.getContext()).load(allPosts.get(position).getFeature_image()).into(holder.postImage);
//        Picasso.get().load(allPosts.get(position).getFeature_image()).into(holder.postImage);

//        item OnclickListener
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = null;
                if(position==0){
                    text = position+1 +"st";
                }else if (position==1){
                    text = position+1 +"nd";
                }else if (position==2){
                    text = position+1 +"rd";
                }else if (position>=3){
                    text = position+1 +"th";
                }
                Toast.makeText(v.getContext(), text + " Post Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("title",allPosts.get(position).getTitle());
                intent.putExtra("targetUrl",allPosts.get(position).getTargetUrl());
//                intent.putExtra("content",allPosts.get(position).getContent());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postTitle;
        TextView postOriginalPrice;
        TextView postDiscountedPrice;
        RatingBar postRating;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            postImage = itemView.findViewById(R.id.post_image);
            postTitle = itemView.findViewById(R.id.postTitle);
            postOriginalPrice = itemView.findViewById(R.id.postOriginalPrice);
            postDiscountedPrice = itemView.findViewById(R.id.postDiscountPrice);
            postRating = itemView.findViewById(R.id.postRating);
            postOriginalPrice.setPaintFlags(postOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }
}
