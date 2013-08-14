package com.online.GCM.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Window;
import com.example.GCM.R;
import com.online.GCM.fragments.CalendarActivity;
import com.online.GCM.fragments.NotificationsActivity;
import com.online.GCM.fragments.SermonsActivity;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/11/13
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Members extends Activity {

    PagerTabStrip mPagerTabStrip2;
    ViewPager mViewPager2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.members);
        getWindow().setFeatureInt(R.layout.gcm_title, Window.FEATURE_CUSTOM_TITLE);

        mViewPager2 = (ViewPager) findViewById(R.id.viewPagermembers);
        TitleAdapter titleAdapter = new TitleAdapter(getFragmentManager());
        mViewPager2.setAdapter(titleAdapter);
        mViewPager2.setCurrentItem(0);

        mPagerTabStrip2 = (PagerTabStrip) findViewById(R.id.pagerTabStripmembers);
        mPagerTabStrip2.setBackgroundColor(getResources().getColor(
                R.color.pc_light_gray));
        mPagerTabStrip2.setTabIndicatorColor(getResources().getColor(
                R.color.pc_blue));
        mPagerTabStrip2.setDrawFullUnderline(true);

    }

    class TitleAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment membertabs[] = new Fragment[titles.length];

        public TitleAdapter(FragmentManager fm) {
            super(fm);
            membertabs[0] = new NotificationsActivity();
            membertabs[1] = new SermonsActivity();
            membertabs[2] = new CalendarActivity();
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return titles[i];
        }

        @Override
        public Fragment getItem(int i) {
            return membertabs[i];  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getCount() {
            return membertabs.length;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private String[] getTitles() {
        String titleString[];

        titleString = new String[]{
                getString(R.string.notifications),
                getString(R.string.sermons),
                getString(R.string.Calendar),
        };
        return titleString;  //To change body of created methods use File | Settings | File Templates.
    }
}