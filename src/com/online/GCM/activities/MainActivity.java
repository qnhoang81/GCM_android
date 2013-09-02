package com.online.GCM.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.GCM.R;

import com.online.GCM.fragments.CalendarActivity;
import com.online.GCM.fragments.GcmOnlineActivity;
import com.online.GCM.fragments.HomeActivity;
import com.online.GCM.fragments.SermonsActivity;

public class MainActivity extends FragmentActivity {

    public static WebView mWebView;

    public static void updateWebView() {

        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
    }

    PagerTabStrip mPagerTabStrip;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(R.layout.gcm_title, Window.FEATURE_CUSTOM_TITLE);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TitleAdapter titleAdapter = new TitleAdapter(getFragmentManager());
        mViewPager.setAdapter(titleAdapter);
        mViewPager.setCurrentItem(0);

        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        mPagerTabStrip.setBackgroundColor(getResources().getColor(
                R.color.pc_light_gray));
        mPagerTabStrip.setTabIndicatorColor(getResources().getColor(
                R.color.pc_blue));
        mPagerTabStrip.setDrawFullUnderline(true);

    }

    class TitleAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment tabs[] = new Fragment[titles.length];

        public TitleAdapter(FragmentManager fm) {
            super(fm);
            tabs[0] = new HomeActivity();
            //To do...
            /* tabs[1] = new CalendarActivity();
            tabs[2] = new SermonsActivity(); */
            tabs[1] = new GcmOnlineActivity();


        }

        @Override
        public CharSequence getPageTitle(int i) {
            return titles[i];
        }

        @Override
        public Fragment getItem(int i) {
            return tabs[i];  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getCount() {
            return tabs.length;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private String[] getTitles() {
        String titleString[];

        titleString = new String[]{
                getString(R.string.home),
                //To do...
                /* getString(R.string.Calendar),
                getString(R.string.Sermons),*/
                getString(R.string.gcm_online),

        };
        return titleString;  //To change body of created methods use File | Settings | File Templates.
    }


}
