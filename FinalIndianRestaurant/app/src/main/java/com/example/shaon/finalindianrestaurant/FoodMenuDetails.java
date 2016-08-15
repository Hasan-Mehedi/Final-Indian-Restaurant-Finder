package com.example.shaon.finalindianrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FoodMenuDetails extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu_details);
        webView = (WebView) findViewById(R.id.webView2);
        String menuurl = getIntent().getStringExtra("menuurl");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(menuurl);
    }
}
