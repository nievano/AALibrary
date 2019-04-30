package com.example.ikephuhayi.datalibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.amityarenalibrary.com/");

        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {

            webView.goBack();

        }

        else {

            super.onBackPressed();

        }


    }
}
