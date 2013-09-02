package com.online.GCM.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.example.GCM.R;
import com.online.GCM.activities.MainActivity;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 9/1/13
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarActivity extends Fragment {

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
        MainActivity.mWebView.loadUrl("http://www.gracechurchmentor.org/calendar");

        MainActivity.updateWebView();

        return view;
    }
}