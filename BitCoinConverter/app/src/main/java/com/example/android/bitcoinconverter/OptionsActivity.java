package com.example.android.bitcoinconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    private String currency;
    private String name;
    private String greeting;
    private double rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Saves name from previous call
        Intent intent = getIntent();
        name = intent.getExtras().getString("namekey");
        greeting = "Hello " + name + "!\nWhich currency would you like to convert to today?";
        TextView greetView = (TextView) findViewById(R.id.greeting_screen);
        greetView.setText(greeting);

        // Creates dropdown menu
        Spinner dropdown = findViewById(R.id.spinner);
        String[] choices = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, choices);
        dropdown.setAdapter(adapter);
    }

    /* Remembers the chosen currency and moves to next screen */
    public void convert(View view) {
//        currency = "USD"; //choosen currency
//        //find exchange rate with client and pass as extra
//        rate = 0;
//        Bundle b = new Bundle();
//        b.putDouble("rate", rate);

        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra("currency", currency);
//        intent.putExtras(b);
        startActivity(intent);
    }

}
