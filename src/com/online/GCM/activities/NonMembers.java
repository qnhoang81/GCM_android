package com.online.GCM.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Window;
import com.online.GCM.fragments.PlanOfSalvation;
import com.example.GCM.R;
import com.online.GCM.fragments.SermonsActivity;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/11/13
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class NonMembers extends Activity {

    PagerTabStrip mPagerTabStrip;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.nonmembers);
        getWindow().setFeatureInt(R.layout.gcm_title, Window.FEATURE_CUSTOM_TITLE);

        mViewPager = (ViewPager) findViewById(R.id.viewpagernonmembers);
        TitleAdapter titleAdapter = new TitleAdapter(getFragmentManager());
        mViewPager.setAdapter(titleAdapter);
        mViewPager.setCurrentItem(0);

        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStripnonmembers);
        mPagerTabStrip.setBackgroundColor(getResources().getColor(
                R.color.pc_light_gray));
        mPagerTabStrip.setTabIndicatorColor(getResources().getColor(
                R.color.pc_blue));
        mPagerTabStrip.setDrawFullUnderline(true);

    }

    class TitleAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment nonmembertabs[] = new Fragment[titles.length];

        public TitleAdapter(FragmentManager fm) {
            super(fm);
            nonmembertabs[0] = new PlanOfSalvation();
            nonmembertabs[1] = new SermonsActivity();
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return titles[i];
        }

        @Override
        public Fragment getItem(int i) {
            return nonmembertabs[i];  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getCount() {
            return nonmembertabs.length;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private String[] getTitles() {
        String titleString[];

        titleString = new String[]{
                getString(R.string.plan_salvation),
                getString(R.string.sermons),
        };
        return titleString;  //To change body of created methods use File | Settings | File Templates.
    }
}