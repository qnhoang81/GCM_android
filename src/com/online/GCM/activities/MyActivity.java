package com.online.GCM.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.example.GCM.R;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(R.layout.gcm_title, Window.FEATURE_CUSTOM_TITLE);
    }

    public void membersOptions(View v){
        startActivity(new Intent(this, Members.class));
    }

    public void nonMembersOptions(View v){
        startActivity(new Intent(this, NonMembers.class));
    }
}
