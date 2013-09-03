package com.online.GCM.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.GCM.R;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 9/1/13
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarFragment extends Fragment {
    private static WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        mWebView = (WebView) view.findViewById(R.id.webviewhome);
        mWebView.loadUrl("http://www.gracechurchmentor.org/calendar");

        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        return view;
    }
}