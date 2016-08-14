package com.example.shaon.finalindianrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FavoriteWebView extends AppCompatActivity {

    WebView webView;
    ResultsFavorite result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_web_view);
        webView= (WebView) findViewById(R.id.webView3);
        result = getIntent().getParcelableExtra("result");
        String weburl=result.restauranturl;
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(weburl);
    }
}
