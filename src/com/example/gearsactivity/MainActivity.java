package com.example.gearsactivity;

import us.costan.chrome.ChromeSettings;
import us.costan.chrome.ChromeView;
import us.costan.chrome.ChromeViewClient;
//import us.costan.chrome.ChromeViewClient;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

public class MainActivity extends Activity {
    boolean mBound = false;
    ChromeView chromeView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove title bar as we already have the sugar toolbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        // Configure the webview setup in the xml layout
        chromeView = (ChromeView)findViewById(R.id.chrome_view);

        // Allow javascript
        ChromeSettings webSettings = chromeView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // This setting defaults to false since API level 16
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
        }

        // Make sure links in the webview is handled by the webview
        // and not sent to a full browser
//        webView.setChromeViewClient(new ChromeViewClient());

        // Finally, load the Sugar activity
        chromeView.loadUrl("file:///android_asset/index.html");
    }
}
