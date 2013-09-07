package com.online.GCM.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.*;
import com.example.GCM.R;
import com.online.GCM.fragments.CalendarFragment;
import com.online.GCM.fragments.SermonsFragment;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/12/13
 * Time: 12:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class GcmOnlineActivity extends Activity {

    private WebView mWebViewGcm;

    public void updateWebView() {

        WebSettings webSettings = mWebViewGcm.getSettings();
        CookieManager cookieManager = CookieManager.getInstance();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        mWebViewGcm.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                CookieSyncManager.getInstance().sync();
                // Get the cookie from cookie jar.
                String cookie = CookieManager.getInstance().getCookie(url);
                if (cookie == null) {
                    return;
                }
                // Cookie is a string like NAME=VALUE [; NAME=VALUE]
                String[] pairs = cookie.split(";");
                for (int i = 0; i < pairs.length; ++i) {
                    String[] parts = pairs[i].split("=", 2);
                    // If token is found, return it to the calling activity.
                    if (parts.length == 2 &&
                            parts[0].equalsIgnoreCase("oauth_token")) {
                        Intent result = new Intent();
                        result.putExtra("token", parts[1]);
                        setResult(RESULT_OK, result);
                        finish();
                    }
                }
            }
        });

        mWebViewGcm.loadUrl("file:///android_asset/header.html");
        mWebViewGcm.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebViewGcm.setScrollbarFadingEnabled(true);


        cookieManager.setAcceptCookie(true);

    }

    public void homescreen() {
        Intent homeIntent= new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode != RESULT_OK || data == null) {
                    return;
                }
                // Get the token.
                String token = data.getStringExtra("token");
                if (token != null) {
                /* Use the token to access data */
                }
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebViewGcm.canGoBack()) {
            mWebViewGcm.goBack();
            return true;
        }  else if (keyCode == KeyEvent.KEYCODE_BACK) {
            homescreen();
            return true;
        }

        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcm_online);

        mWebViewGcm = (WebView) findViewById(R.id.webviewgcm);
        //mWebViewGcm.loadUrl("https://gracechurchmentor.ccbchurch.com/login.php");

        new WebViewTask().execute();
        updateWebView();

        if (savedInstanceState == null) {
            // Load a page
            //mWebViewGcm.loadUrl("https://gracechurchmentor.ccbchurch.com/login.php");
        }

        CookieSyncManager.createInstance(getApplicationContext());


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the WebView
        mWebViewGcm.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state of the WebView
        mWebViewGcm.restoreState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigate, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                mWebViewGcm.loadUrl("http://www.gracechurchmentor.org");
                break;
            case R.id.calendar:
                mWebViewGcm.loadUrl("http://www.gracechurchmentor.org/calendar");
                break;
            case R.id.Sermons:
                mWebViewGcm.loadUrl("http://www.gracechurchmentor.org/sermons");
                break;
            case R.id.exit:
                homescreen();
                break;
            default:
                mWebViewGcm.loadUrl("https://gracechurchmentor.ccbchurch.com/login.php");
        }

        return super.onOptionsItemSelected(item);
    }    */

    private class WebViewTask extends AsyncTask<Void, Void, Boolean> {

        protected Boolean doInBackground(Void... param) {
            /* this is very important - THIS IS THE HACK */
            SystemClock.sleep(1000);
            return false;
        }
    }

}
