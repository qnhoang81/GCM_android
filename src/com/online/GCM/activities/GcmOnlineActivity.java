package com.online.GCM.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
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

    private static WebView mWebView;

    public static void updateWebView() {

        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcm_online);

        mWebView = (WebView) findViewById(R.id.webviewgcm);
        mWebView.loadUrl("https://gracechurchmentor.ccbchurch.com/login.php");

        updateWebView();

        if (savedInstanceState == null) {
            // Load a page
            mWebView.loadUrl("https://gracechurchmentor.ccbchurch.com/login.php");
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the WebView
        mWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state of the WebView
        mWebView.restoreState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        Fragment frag = null;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.calendar:
                frag = new CalendarFragment();
                break;
            case R.id.Sermons:
                frag = new SermonsFragment();
                break;
            default:
                frag = new CalendarFragment();
        }

        transaction.replace(R.id.webviewhome, frag);
        transaction.commit();

        return super.onOptionsItemSelected(item);
    }
}
