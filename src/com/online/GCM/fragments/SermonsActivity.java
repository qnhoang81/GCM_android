package com.online.GCM.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.view.ViewGroup;

import com.example.GCM.R;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/12/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class SermonsActivity extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sermons, root, false);

        return view;
    }
}