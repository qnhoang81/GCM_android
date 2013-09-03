package com.online.GCM.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.*;
import com.example.GCM.R;
import com.online.GCM.fragments.CalendarFragment;
import com.online.GCM.fragments.HomeFragment;
import com.online.GCM.fragments.SermonsFragment;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/13/13
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeActivity extends Activity {

    private static WebView mWebView;

    public static void updateWebView() {

        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mWebView = (WebView) findViewById(R.id.webviewhome);
        mWebView.loadUrl("http://www.gracechurchmentor.org");

        updateWebView();

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
            case R.id.home:
                frag = new HomeFragment();
                break;
            case R.id.calendar:
                frag = new CalendarFragment();
                break;
            case R.id.Sermons:
                frag = new SermonsFragment();
                break;
            default:
                frag = new HomeFragment();
        }

        transaction.replace(R.id.webviewhome, frag);
        transaction.commit();

        return super.onOptionsItemSelected(item);
    }


}
