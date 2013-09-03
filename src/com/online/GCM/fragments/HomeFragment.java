package com.online.GCM.fragments;

import android.app.Activity;
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
 * Date: 9/2/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeFragment extends Fragment {

    private static WebView mWebView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        mWebView = (WebView) view.findViewById(R.id.webviewhome);
        mWebView.loadUrl("http://www.gracechurchmentor.org");

        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        return view;
    }
}