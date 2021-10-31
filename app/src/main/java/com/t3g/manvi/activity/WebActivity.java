package com.t3g.manvi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.widget.Toolbar;
import com.t3g.manvi.R;

import java.io.InputStream;

public class WebActivity extends AppCompatActivity {

    WebView content;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        content = findViewById(R.id.web_content);
        Intent data = getIntent();
        String title = data.getStringExtra("title");
//        String content = data.getStringExtra("content");
        toolbar.setTitle(title);
        String targetUrl = data.getStringExtra("targetUrl");

//        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
//        TextView contents = findViewById(R.id.contentTxt);
//        contents.setText(content);
        final String mimeType = "text/html";
        final String encoding = "UTF-8";

//        contentView.loadDataWithBaseURL("",content,mimeType,encoding,"");
        content.loadUrl(targetUrl);
        content.getSettings().setJavaScriptEnabled(true);
        content.getSettings().setDomStorageEnabled(true);
        content.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        content.setWebChromeClient(new WebChromeClient() {


        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
//    private void injectJS() {
//        try {
//            InputStream inputStream = getAssets().open("jscript.js");
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();
//            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
//            content.loadUrl("javascript:(function() {" +
//                    "var parent = document.getElementsByTagName('head').item(0);" +
//                    "var script = document.createElement('script');" +
//                    "script.type = 'text/javascript';" +
//                    "script.innerHTML = window.atob('" + encoded + "');" +
//                    "parent.appendChild(script)" +
//                    "})()");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }