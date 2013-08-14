package com.online.GCM.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.example.GCM.R;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 8/13/13
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class MembersAuthenticate extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.members_authenticate);
        getWindow().setFeatureInt(R.layout.gcm_title, Window.FEATURE_CUSTOM_TITLE);
    }

}
