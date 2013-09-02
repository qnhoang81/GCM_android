package com.online.GCM.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.*;
import android.webkit.*;
import com.example.GCM.R;
import com.online.GCM.activities.MainActivity;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/13/13
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeActivity extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        MainActivity.mWebView = (WebView) view.findViewById(R.id.webviewhome);
        MainActivity.mWebView.loadUrl("http://www.gracechurchmentor.org");

        MainActivity.updateWebView();

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.navigate, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        Fragment frag = null;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                frag = new HomeActivity();
                break;
            case R.id.calendar:
                frag = new CalendarActivity();
                break;
            case R.id.Sermons:
                frag = new SermonsActivity();
                break;
            default:
                frag = new HomeActivity();
        }

        transaction.replace(R.id.webviewhome, frag);
        transaction.commit();

        return super.onOptionsItemSelected(item);
    }
}
