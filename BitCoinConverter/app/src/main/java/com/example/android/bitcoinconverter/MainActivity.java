package com.example.android.bitcoinconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.Date;

import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;

public class MainActivity extends AppCompatActivity {

//    private PersistentCookieStore cookieStore;
//    private AsyncHttpClient client;
    private final String MY_PREFS_NAME = "myPreferenceFile";
    private SharedPreferences sharedpreferences;
    private String namekey = "namekey";
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        if (sharedpreferences.contains(namekey)) {
            name = sharedpreferences.getString(namekey, null);
            Intent intent = new Intent(this, OptionsActivity.class);
            intent.putExtra(namekey, name);
            startActivity(intent);
        }

//        client = new AsyncHttpClient();
//        cookieStore = new PersistentCookieStore(this);
//        client.setCookieStore(cookieStore);
//        CookieManager cm = new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL);
    }

    /* Saves the name as a cookie and moves to the next screen */
    public void submitName(View view) {
        sharedpreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        EditText nameInput = findViewById(R.id.name_input);
        name = nameInput.getText().toString();
        SharedPreferences.Editor edit = sharedpreferences.edit();
        // Log.d("nametag", name);
        edit.putString(namekey, name);
        edit.commit();


//        BasicClientCookie nameCookie = new BasicClientCookie("name", name);
//        cookieStore.addCookie(nameCookie);

//        TextView greetView = (TextView) findViewById(R.id.test);
//        greetView.setText(name);

        Intent intent = new Intent(this, OptionsActivity.class);
        intent.putExtra(namekey, name);
        startActivity(intent);
    }
}
