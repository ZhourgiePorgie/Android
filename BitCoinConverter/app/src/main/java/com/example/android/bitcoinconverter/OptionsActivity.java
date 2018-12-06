package com.example.android.bitcoinconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class OptionsActivity extends AppCompatActivity {

    private AsyncHttpClient client;
    private final String MY_PREFS_NAME = "myPreferenceFile";
    private SharedPreferences sharedpreferences;
    private String currency;
    private String name;
    private String greeting;
    private float rate;

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

//        // Creates dropdown menu
//        Spinner dropdown = findViewById(R.id.spinner);
//        String[] choices = new String[]{"1", "2", "3", "4"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, choices);
//        dropdown.setAdapter(adapter);
    }

    /* Remembers the chosen currency and moves to next screen */
    public void convert(View view) throws JSONException{
        //Takes user input for currency choice
        EditText currencyInput = findViewById(R.id.currency_input);
        currency = currencyInput.getText().toString();

        //Fetches rate from with the API
        client = new AsyncHttpClient();
        client.get("https://apiv2.bitcoinaverage.com/constants/exchangerates/global", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    super.onSuccess(statusCode, headers, response);
                    if (response.get("rates") instanceof JSONObject) {
                        Log.i("check", "JSON");
                    }
                    String jsonResponse = response.getJSONObject("rates").getJSONObject(currency).get("rate").toString();
                    rate = Float.parseFloat(jsonResponse);
                    Log.i("tag", "onSuccess: " + jsonResponse);
                    Log.i("tag", "onSuccess: " + rate);

                    //Moves to next screen passing currency and rate as extras
                    Intent intent = new Intent(OptionsActivity.this, ResultActivity.class);
                    intent.putExtra("currency", currency);
                    intent.putExtra("rate", rate);
                    startActivity(intent);
                }
                catch (JSONException e) {
                    Log.d("tag", e.toString());
                }
            }
        });
    }

}
