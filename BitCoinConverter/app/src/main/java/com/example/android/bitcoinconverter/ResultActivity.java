package com.example.android.bitcoinconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private String currency;
    private TextView message_view;
    private String message;
    private float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Receives the currency and rate and updates the message
        Intent intent = getIntent();
        currency = intent.getExtras().get("currency").toString();
        rate = intent.getFloatExtra("rate", 0f);
        message_view = (TextView) findViewById(R.id.message);
        message = "One bitcoin is worth " + rate +  " in " + currency;
        message_view.setText(message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
