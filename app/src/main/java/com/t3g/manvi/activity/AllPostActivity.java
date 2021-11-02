package com.t3g.manvi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.t3g.manvi.modal.ProductModal;
import com.t3g.manvi.adapter.PostsAdapter;
import com.t3g.manvi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllPostActivity extends AppCompatActivity {

    RecyclerView products_list;
    List<ProductModal> allProducts;
    int pageNumber;  // new
    PostsAdapter adapter;
    TextView resT;
    String base_url, api, products, orderBy, category, quantity, pageId, consumerKey, consumerSecret;

//    new
    Boolean isScrolling  = false;
    int currentItems,totalItems,scrollOutItems;


    String fullUrl;
//    String webUrl[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Manvi");

        base_url = getResources().getString(R.string.baseUrl);
        api = getResources().getString(R.string.api);
        products = getResources().getString(R.string.products);
        orderBy = "?" + getResources().getString(R.string.orderBy); // + title / date / price
        category = "&" + getResources().getString(R.string.category); // + category id
        quantity = "?" + getResources().getString(R.string.quantity); // + value of products per page
        pageId = "&" + getResources().getString(R.string.pageId); // + page no
        consumerKey = "&consumer_key=" + getResources().getString(R.string.consumerKey);
        consumerSecret = "&consumer_secret=" + getResources().getString(R.string.consumerSecret);
        pageNumber = pageNumber+1;



        allProducts = new ArrayList<>();
        products_list = findViewById(R.id.recyclerView);

        extractPosts();
        GridLayoutManager manager = new GridLayoutManager(this,2);
        products_list.setLayoutManager(manager);
        adapter = new PostsAdapter(allProducts);
        products_list.setAdapter(adapter);

        products_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public  void onScrolled(RecyclerView products_list, int dx, int dy){
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems+scrollOutItems==totalItems)){
                    isScrolling = false;

                    Toast.makeText(getApplicationContext(), "Hang on, loading more products", Toast.LENGTH_SHORT).show();
//                    extractPosts(fullUrl);
                    pageNumber = pageNumber + 1;
                    extractPosts();
                }
            }
        });

//        test api response
        resT = findViewById(R.id.responseTxt);
    }

    public void extractPosts(){

        fullUrl =
                base_url
                        + api
                        + products
                        + orderBy + "date"
                        + category + "67"
                        + quantity + "20"
                        + pageId + pageNumber
                        + consumerKey
                        + consumerSecret ;
//        use volley to extract data
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, fullUrl, null, new Response.Listener<JSONArray>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(AllPostActivity.this, "loading..", Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "onResponse: " + response.toString());
                for(int i = 0; i < response.length(); i++){
                    try {
                        ProductModal p = new ProductModal();
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
                        allProducts.add(p);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AllPostActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(request);
    }

}