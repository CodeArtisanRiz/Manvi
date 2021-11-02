package com.t3g.manvi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.t3g.manvi.R;

import java.io.InputStream;

public class WebActivity extends AppCompatActivity {

    WebView contentView;
    Dialog errorDialog;
//    ShimmerFrameLayout frameLayout;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Toolbar toolbarWeb = findViewById(R.id.toolbar_web);
        setSupportActionBar(toolbarWeb);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.brittany_signature);
//

        contentView = findViewById(R.id.web_content);
        Intent data = getIntent();
        String title = data.getStringExtra("title");
        toolbarWeb.setTitle(title);
//        String content = data.getStringExtra("content");
        String targetUrl = data.getStringExtra("targetUrl");

//        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
//        TextView contents = findViewById(R.id.contentTxt);
//        contents.setText(content);
        final String mimeType = "text/html";
        final String encoding = "UTF-8";

//        contentView.loadDataWithBaseURL("",content,mimeType,encoding,"");
        LoadView(targetUrl);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void LoadView(String targetUrl) {
        // Load the url in web view
        contentView.setInitialScale(1);
        contentView.getSettings().setAllowContentAccess(true);
        contentView.getSettings().setAllowFileAccess(true);
//        contentView.getSettings().setBuiltInZoomControls(true);
//        contentView.getSettings().setBuiltInZoomControls(false);
        contentView.getSettings().setAppCacheEnabled(false);
        contentView.getSettings().setDomStorageEnabled(true);
        contentView.getSettings().setLoadWithOverviewMode(true);
        contentView.getSettings().setUseWideViewPort(true);
        contentView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView content, int errorCode, String description, String failingUrl) {
                content.loadUrl("about:blank");
                errorDialog = new Dialog(WebActivity.this,R.style.Theme_Manvi);
                errorDialog.setContentView(R.layout.no_internet);
                errorDialog.setCancelable(false);
                Button button = errorDialog.findViewById(R.id.refresh);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
//                        frameLayout.setVisibility(View.VISIBLE);
//                        frameLayout.startShimmer();
                        content.setVisibility(View.GONE);
                        content.goBack();
                        errorDialog.dismiss();
                    }
                });
                errorDialog.show();
                super.onReceivedError(content, errorCode, description, failingUrl);
            }



            //            @Override
//            public  void onPageStarted(){
//                if(contentView.getUrl().toString().equals("https://manvi.ml/shop-2/")){
//                    Intent intent = new Intent(WebActivity.this, NavActivity.class);
//                    startActivity(intent);
//                }
//                super.onPageStarted();
//            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(contentView.getUrl().contains("https://manvi.ml/shop-2/")){
                    Toast.makeText(getApplicationContext(), "if", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "else", Toast.LENGTH_SHORT).show();
                }
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url)
            {
                injectJS();
//                frameLayout.setVisibility(View.GONE);
//                frameLayout.stopShimmer();
                contentView.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });

        contentView.loadUrl(targetUrl);

        contentView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        contentView.getSettings().setJavaScriptEnabled(true);
        contentView.getSettings().setDomStorageEnabled(true);
        contentView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        contentView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged (WebView view, int newProgress){
//                progressBar.setProgress(newProgress);
//                if (newProgress==100){
//                    frameLayout.setVisibility(View.GONE);
//                    frameLayout.stopShimmer();
//                    contentView.setVisibility(View.VISIBLE);
//                }else{
//                    frameLayout.setVisibility(View.VISIBLE);
//                    frameLayout.startShimmer();
//                    contentView.setVisibility(View.GONE);
//                }
//            }
        });

    }
    private void injectJS() {
        try {
            InputStream inputStream = getAssets().open("jscript.js");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            contentView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var script = document.createElement('script');" +
                    "script.type = 'text/javascript';" +
                    "script.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(script)" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }