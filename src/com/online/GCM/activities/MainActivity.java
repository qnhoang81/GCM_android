package com.online.GCM.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.webkit.WebView;
import android.widget.TabHost;
import android.widget.TabHost.*;
import com.example.GCM.R;

public class MainActivity extends TabActivity {

    public static WebView mWebView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(R.layout.main, Window.FEATURE_CUSTOM_TITLE);

        TabHost mTabHost = getTabHost();

        // Tab for Home Page
        TabSpec home = mTabHost.newTabSpec("Home");
        // setting Title and Icon for the Tab
        home.setIndicator("Home"); //, getResources().getDrawable(R.drawable.ic_launcher));
        Intent homeIntent = new Intent(this, HomeActivity.class);
        home.setContent(homeIntent);

        // Tab for GCM-Online
        TabHost.TabSpec gcm = mTabHost.newTabSpec("GCM Online");
        gcm.setIndicator("GCM Online"); //, getResources().getDrawable(R.drawable.ic_launcher));
        Intent gcmIntent = new Intent(this, GcmOnlineActivity.class);
        gcm.setContent(gcmIntent);

        // Adding all TabSpec to TabHost
        mTabHost.addTab(home); // Adding home tab
        mTabHost.addTab(gcm); // Adding gcm tab

    }

}
