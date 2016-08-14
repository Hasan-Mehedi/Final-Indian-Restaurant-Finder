package com.example.shaon.finalindianrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewDetails extends AppCompatActivity {

    WebView webView;
    Results result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView= (WebView) findViewById(R.id.webView);
        result = getIntent().getParcelableExtra("result");
        String weburl=result.restauranturl;
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(weburl);

    }
}
