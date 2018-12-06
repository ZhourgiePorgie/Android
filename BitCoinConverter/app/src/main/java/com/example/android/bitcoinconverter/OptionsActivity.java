package com.example.android.bitcoinconverter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class OptionsActivity extends AppCompatActivity {

    private AsyncHttpClient client;
    private String currency;
    private String name;
    private String greeting;
    private float rate;
    private String baseURL1 = "https://apiv2.bitcoinaverage.com/convert/global?from=BTC&to=";
    private String baseURL2 = "&amount=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //Saves name from previous call
        Intent intent = getIntent();
        name = intent.getExtras().getString("namekey");
        greeting = "Hello " + name + "!\nWhich currency would you like to convert to today?";
        TextView greetView = (TextView) findViewById(R.id.greeting_screen);
        greetView.setText(greeting);
    }

    /* Opens the browser to a list of currency codes */
    public void openBrowser(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iban.com/currency-codes"));
        startActivity(browserIntent);
    }

    /* Remembers the chosen currency and moves to next screen */
    public void convert(View view) throws JSONException{
        //Takes user input for currency choice
        EditText currencyInput = findViewById(R.id.currency_input);
        currency = currencyInput.getText().toString();
        String URL = baseURL1 + currency + baseURL2;

        //Fetches rate from with the API
        client = new AsyncHttpClient();
        client.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    super.onSuccess(statusCode, headers, response);
                    String jsonResponse = response.get("price").toString();
                    rate = Float.parseFloat(jsonResponse);

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
