package com.t3g.manvi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.t3g.manvi.modal.Post;
import com.t3g.manvi.adapter.PostsAdapter;
import com.t3g.manvi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView post_list;
    List<Post> posts;
    PostsAdapter adapter;
    TextView resT;
    String base_url, api, products, consumer_key, consumer_secret;
    String webUrl[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Manvi");


        base_url = getResources().getString(R.string.base_url);
        api = getResources().getString(R.string.api);
        products = getResources().getString(R.string.products);
        consumer_key = "&consumer_key=" + getResources().getString(R.string.consumer_key);
        consumer_secret = "&consumer_secret=" + getResources().getString(R.string.consumer_secret);

        posts = new ArrayList<>();
        post_list = findViewById(R.id.recyclerView);
        extractPosts(getResources().getString(R.string.web_url));
        GridLayoutManager manager = new GridLayoutManager(this,2);
        post_list.setLayoutManager(manager);
        adapter = new PostsAdapter(posts);
        post_list.setAdapter(adapter);
        resT = findViewById(R.id.responseTxt);
    }

    public void extractPosts(String URL){
//        use volley to extract data
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this, "loading..", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onResponse: " + response.toString());
                for(int i = 0; i < response.length(); i++){
                    try {
                        Post p = new Post();
                        JSONObject jsonObjectData = response.getJSONObject(i);
//                    extract date
//                        p.setDate(jsonObjectData.getString("date"));
//                    extract title
                        p.setTitle(jsonObjectData.getString("name"));
                        p.setTargetUrl(jsonObjectData.getString("permalink"));
                        p.setDiscountedPrice("₹"+jsonObjectData.getInt("price"));
                        if(jsonObjectData.getString("regular_price").equals("")) {
                            p.setOriginalPrice("");
                        }else {
                            p.setOriginalPrice("₹" + jsonObjectData.getString("regular_price"));
                        }
                        p.setRating(jsonObjectData.getInt("average_rating"));

                        JSONArray ja = response.getJSONObject(i).getJSONArray("images");
                        for(int j=0; j<ja.length(); j++){
                            JSONObject jsonObjectData2 = ja.getJSONObject(j);
//                            Toast.makeText(getApplicationContext(), jsonObjectData2.getString("src"), Toast.LENGTH_SHORT).show();
                            p.setFeature_image(jsonObjectData2.getString("src"));
                        }
                        posts.add(p);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

}