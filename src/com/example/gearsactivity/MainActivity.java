package com.example.gearsactivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.ConsoleMessage;

public class MainActivity extends Activity {
    boolean mBound = false;
    WebView webView;

    @SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove title bar as we already have the sugar toolbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        // Configure the webview setup in the xml layout
        webView = (WebView) findViewById(R.id.webview);

        // Allow javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // This setting defaults to false since API level 16
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
    }

        // Make sure links in the webview is handled by the webview
        // and not sent to a full browser
        webView.setWebViewClient(new WebViewClient());

        // Send javascript console messages to Eclipse LogCat
        webView.setWebChromeClient(new WebChromeClient() {
          public boolean onConsoleMessage(ConsoleMessage cm) {
            Log.d("Sugar Activity", cm.message() + " -- From line "
                  + cm.lineNumber() + " of "
                  + cm.sourceId() );
            return true;
          }
        });

        // Finally, load the Sugar activity
        webView.loadUrl("file:///android_asset/index.html");
    }
}
