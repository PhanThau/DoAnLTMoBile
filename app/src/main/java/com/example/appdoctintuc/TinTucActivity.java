package com.example.appdoctintuc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TinTucActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        String link = getIntent().getStringExtra("link");
        mWebView = findViewById(R.id.activityTinTuc_WebView);
        mWebView.loadUrl(link);
        mWebView.setWebViewClient(new WebViewClient());
    }
}