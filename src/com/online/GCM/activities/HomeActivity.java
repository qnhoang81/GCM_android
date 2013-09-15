package com.online.GCM.activities;

import android.app.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import com.example.GCM.R;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/13/13
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeActivity extends Activity {

    private WebView mWebViewhome;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mNavigate;
    private ProgressBar mProgressBar;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.home);

        setLayout();

        /***** Webview implementation *****/
        if (savedInstanceState == null) {
                // Load a page
            //mWebViewhome.loadUrl("file:///android_asset/gracechurchmentor.html");
        }


        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        /***** Implemenation of Sliding Navigation Drawer *****/

        mNavigate = getResources().getStringArray(R.array.navigate_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLayout.setBackgroundColor(R.color.transparent);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mNavigate));
        mDrawerList.setBackgroundColor(R.color.transparent);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mTitle = mDrawerTitle = getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the WebView
        mWebViewhome.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state of the WebView
        mWebViewhome.restoreState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigate, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_websearch:
                // create intent to perform web search
                String string = "Grace Church of Mentor, OH directions";
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, string);
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebViewhome.canGoBack()) {
            mWebViewhome.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if( this.getFragmentManager().getBackStackEntryCount() != 0 ){
                this.getFragmentManager().popBackStack();
                return true;
            }
            // If there are no fragments on stack perform the original back button event
        }

        return super.onKeyDown(keyCode, event);

    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void setLayout() {
        Fragment fragment = new DefaultFragment();
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new ChoiceFragment();
        Bundle args = new Bundle();
        args.putInt(ChoiceFragment.ARG_ARRAY_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();


        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mNavigate[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public class DefaultFragment extends Fragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.webview_default, container, false);

            mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
            mWebViewhome = (WebView) view.findViewById(R.id.webViewDefault);


            mWebViewhome.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {

                    if(progress < 100 && mProgressBar.getVisibility() == ProgressBar.GONE){
                        mProgressBar.setVisibility(ProgressBar.VISIBLE);
                    }

                    if(progress == 100) {
                        mProgressBar.setVisibility(ProgressBar.GONE);
                    }

                    mProgressBar.setProgress(progress);
                }

            });

            mWebViewhome.loadUrl("https://gracechurchmentor.ccbchurch.com/mobile_login.php");
            mWebViewhome.setWebViewClient(new WebViewClient());
            setTitle("Grace Church of Mentor");
            return view;
        }

    }

    public class ChoiceFragment extends Fragment {

        public void updateWebView() {

            WebSettings mWebSettings = mWebViewhome.getSettings();
            CookieManager cookieManager = CookieManager.getInstance();

            mWebSettings.setJavaScriptEnabled(true);
            mWebSettings.setSupportZoom(true);
            mWebSettings.setBuiltInZoomControls(true);
            mWebSettings.setLoadsImagesAutomatically(true);

            mWebViewhome.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            mWebViewhome.setScrollbarFadingEnabled(true);
            mWebSettings.setPluginState(WebSettings.PluginState.ON);
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);


            cookieManager.setAcceptCookie(true);
        }

        public String getnews() throws IOException {
            URL url = new URL("http://www.gracechurchmentor.org/news");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String str = IOUtils.toString(in, encoding);

            return str;
        }

        public String gcmnews() throws IOException {
            String gcmnews = "";
            gcmnews = getnews().substring(getnews().indexOf("<h3 class=\"\">GCM News</h3>"), getnews().indexOf("About GCM"));
            return gcmnews;
        }

        public static final String ARG_ARRAY_NUMBER = "array_number";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        public ChoiceFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.webview_default, container, false);

            mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
            mWebViewhome = (WebView) view.findViewById(R.id.webViewDefault);


            mWebViewhome.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {

                    if(progress < 100 && mProgressBar.getVisibility() == ProgressBar.GONE){
                        mProgressBar.setVisibility(ProgressBar.VISIBLE);
                    }

                    if(progress == 100) {
                        mProgressBar.setVisibility(ProgressBar.GONE);
                    }

                    mProgressBar.setProgress(progress);
                }

            });


            mWebViewhome.setWebViewClient(new WebViewClient());

            int i = getArguments().getInt(ARG_ARRAY_NUMBER);
            String navigate = getResources().getStringArray(R.array.navigate_array)[i];

            switch (i) {
                case 0:
                    try {

                    InputStream st = getAssets().open("upcoming_events.html");
                    String header = IOUtils.toString(st);

                    mWebViewhome.loadDataWithBaseURL("file:///android_asset/upcoming_events.html", header, "text/html", "charset=UTF-8", null);
                    } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    break;
                case 1:

                    try {

                        InputStream st = getAssets().open("gcm_news.html");
                        String header = IOUtils.toString(st);

                        StringBuilder builder = new StringBuilder("");
                        builder.append(header + gcmnews() + "</div></body></html>");

                        mWebViewhome.loadDataWithBaseURL("file:///android_asset/gcm_news.html", builder.toString(), "text/html", "charset=UTF-8", null);
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    break;
                case 2:
                    mWebViewhome.loadUrl("https://gracechurchmentor.ccbchurch.com/mobile_login.php");
                    break;
                case 3:
                    try {
                        InputStream st = getAssets().open("header_calendar.html");
                        String header = IOUtils.toString(st);

                        mWebViewhome.loadDataWithBaseURL("file:///android_asset/header_calendar.html", header, "text/html", "charset=UTF-8", null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    mWebViewhome.loadUrl("http://www.gracechurchmentor.org/sermons");
                    break;
                case 5:
                    mWebViewhome.loadUrl("file:///android_asset/gracechurchmentor.html");
                    break;
                default:
                    finish();
            }

            mWebViewhome.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {

                    if(progress < 100 && mProgressBar.getVisibility() == ProgressBar.GONE){
                        mProgressBar.setVisibility(ProgressBar.VISIBLE);
                    }

                    if(progress == 100) {
                        mProgressBar.setVisibility(ProgressBar.GONE);
                    }

                    mProgressBar.setProgress(progress);
                }

            });

            updateWebView();

            if(container == null){
                return null;
            }

            getActivity().setTitle(navigate);
            return view;

        }

    }
}