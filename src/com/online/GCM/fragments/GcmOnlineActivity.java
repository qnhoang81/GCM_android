package com.online.GCM.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import com.example.GCM.R;
import com.online.GCM.activities.MainActivity;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/12/13
 * Time: 12:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class GcmOnlineActivity extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        MainActivity.mWebView = (WebView) view.findViewById(R.id.webviewhome);
        MainActivity.mWebView.loadUrl("https://gracechurchmentor.ccbchurch.com/mobile_login.php");

        MainActivity.updateWebView();

        return view;
    }
}
