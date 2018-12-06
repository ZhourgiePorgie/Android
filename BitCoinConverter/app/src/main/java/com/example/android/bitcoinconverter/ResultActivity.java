package com.example.android.bitcoinconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private String currency;
    private TextView message_view;
    private String message;
    private double rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        Intent intent = getIntent();
//        currency = intent.getExtras().toString();
//        //Log.d("State", "currency");
//        Bundle b = intent.getExtras();
//        rate = b.getDouble("rate");
        currency = "hi";
        message_view = (TextView) findViewById(R.id.message);
        message = "One bitcoint is ... in this currency " + currency;
        message_view.setText(message);
    }
}
