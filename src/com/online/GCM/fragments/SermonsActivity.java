package com.online.GCM.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import com.example.GCM.R;
import com.online.GCM.activities.MainActivity;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 9/1/13
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class SermonsActivity extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        MainActivity.mWebView = (WebView) view.findViewById(R.id.webviewhome);
        MainActivity.mWebView.loadUrl("http://www.gracechurchmentor.org/sermons");

        MainActivity.updateWebView();

        MainActivity.mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

        return view;
    }
}
