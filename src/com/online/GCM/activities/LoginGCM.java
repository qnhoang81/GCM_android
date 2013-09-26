package com.online.GCM.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.GCM.R;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 9/22/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */


public class LoginGCM extends Activity {

    private Button login,clear;
    private AlertDialog dialog;
    private EditText username,password;
    private TextView guestView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_gcm);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);
        dialog=new AlertDialog.Builder(this).create();
        dialog.setTitle("Unable to Login");
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                postLoginData();
            }

        });

        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
            }
        });

        guestView = (TextView) findViewById(R.id.guest);
        guestView.setMovementMethod(LinkMovementMethod.getInstance());
        guestView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
                }
            });
    }

    public void postLoginData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();

        /* login.php returns true if username and password is equal to saranga */
        HttpPost httppost = new HttpPost("https://gracechurchmentor.ccbchurch.com/login.php");

                try {
                    // Add user name and password

                    String mUsername = username.getText().toString();
                    String mPassword = password.getText().toString();
                    String hidden1 = "login";
                    String hidden2 = "";

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("ax", hidden1));
                    nameValuePairs.add(new BasicNameValuePair("rurl", hidden2));
                    nameValuePairs.add(new BasicNameValuePair("form[login]", mUsername));
                    nameValuePairs.add(new BasicNameValuePair("form[password]", mPassword));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    Log.w("GCM", "Execute HTTP Post Request");
                    HttpResponse response = httpclient.execute(httppost);

                    String str = inputStreamToString(response.getEntity().getContent()).toString();
                    Log.w("GCM", str);

                    String value = str.substring(str.indexOf("W3C"), str.indexOf("//EN"));

                    if (value.toString().equalsIgnoreCase("W3C//DTD XHTML 1.0 Transitional")) {

                        Intent intent = new Intent(getApplicationContext(), GcmOnline.class);
                        startActivity(intent);

                    } else {

                        if (mUsername.equals("") || mPassword.equals("")) {
                            dialog=new AlertDialog.Builder(this).create();
                            dialog.setMessage("Enter Username and Password");
                        } else {
                            dialog=new AlertDialog.Builder(this).create();
                            dialog.setMessage("Invalid Username or Password");
                        }

                        dialog.setTitle("Unable To Login:");
                        dialog.show();
                    }

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

            private StringBuilder inputStreamToString(InputStream is) {
                String line = "";
                StringBuilder total = new StringBuilder();
                // Wrap a BufferedReader around the InputStream
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                // Read response until the end
                try {
                    while ((line = rd.readLine()) != null) {
                        total.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Return full string
                return total;
            }

}




