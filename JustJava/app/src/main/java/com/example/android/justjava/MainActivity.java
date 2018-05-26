/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText Name = (EditText) findViewById(R.id.name_text_input);

        hasWhippedCream = whippedCreamCheckBox.isChecked();
        hasChocolate = chocolateCheckBox.isChecked();
        String name = Name.getText().toString();

        int price = calculatePrice(quantity, 5);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the "+" button is clicked.
     */
    public void increment(View view){
        if (quantity < 100) {
            quantity += 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the "0-" button is clicked.
     */
    public void decrement(View view){
        if (quantity > 1) {
            quantity -= 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method calculates the price of the order.
     *
     * @param quantity amount of coffee
     * @param pricePerCup the price per cup of coffee
     * @return total price
     */
    private int calculatePrice(int quantity, int pricePerCup) {
        if (hasWhippedCream) {
            pricePerCup += 1;
        }
        if (hasChocolate) {
            pricePerCup += 2;
        }
        int price = quantity * pricePerCup;
        return price;
    }

    /**
     * This method creates an order summary.
     *
     * @param price price of order
     * @param addWhippedCream check if whipped cream was selected or not
     * @return order summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String summary = "Name: " + name
                + "\nAdd Whipped Cream? " + addWhippedCream
                + "\nAdd Chocolate? " + addChocolate
                + "\nQuantity: " + quantity
                + "\nTotal: $" + price
                + "\nThank you!";
        return summary;
    }
}