package com.example.ikephuhayi.datalibrary;

import android.app.DownloadManager;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
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

        if (savedInstanceState != null) {

            webView.restoreState(savedInstanceState);

        }

        else {

            webView.loadUrl("http://www.amityarenalibrary.com/");

        }

        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        registerForContextMenu(webView);
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

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);

        if (webView.getHitTestResult().getType() == WebView.HitTestResult.IMAGE_TYPE || webView.getHitTestResult().getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {

            contextMenu.add(0, 1, 0, "Download Image").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(webView.getHitTestResult().getExtra()));
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);

                    return false;

                }

            });

        }

        else if (webView.getHitTestResult().getType() == WebView.HitTestResult.UNKNOWN_TYPE) {

            contextMenu.add(0, 1, 0, "Copy Page Url").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("", webView.getUrl());
                    clipboard.setPrimaryClip(clip);

                    return false;
                }

            });

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

}
