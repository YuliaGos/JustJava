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
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class  MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void increment (View view){
        if (quantity == 100){
            //Show an error message as a toast
            Toast.makeText(this,"You cannot have more than 100 coffees", Toast.LENGTH_LONG).show();
            //Exit this method early because there's nothing left to do
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);

    }

    public void decrement (View view){
        //Show an error message as a toast
        Toast.makeText(this,"You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        //Exit this method early because there's nothing left to do
        if (quantity == 1){
            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);
    }

    private void displayQuantity (int numberOfCoffees){
        TextView quantityTextView=(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayMessage (String message){
        TextView orderSummaryTextView=(TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void submitOrder(View view){
        EditText nameView = (EditText) findViewById(R.id.name_view);
        String name = nameView.getText().toString();
        Log.v("MainActivity", "Name: " + name);

        CheckBox whippedCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox=(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate=chocolateCheckBox.isChecked();

        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage=createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);

        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setData(Uri.parse("geo:47.6,-122.3"));
        //if (intent.resolveActivity(getPackageManager()) != null) {
            //startActivity(intent);
    }



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
    }


    /**
     * Calculates the price of the order.
     *@param name of the customer
     *@param price of the order
     *@param addWhippedCream is whether or not
     *@param addChocolate is whether or not
     *@return text summary
     */

    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage="Name: " + name;
        priceMessage=priceMessage + "\n Quantity: " + quantity;
        priceMessage=priceMessage + "\n Add whipped cream? " + addWhippedCream;
        priceMessage=priceMessage + "\n Add chocolate? " + addChocolate;
        priceMessage=priceMessage + "\n Total:$" + price;
        priceMessage=priceMessage + "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice=5;
        if (addWhippedCream){
            basePrice=basePrice+1;
        }

        if (addChocolate){
            basePrice=basePrice+2;
        }

        return quantity * basePrice;
    }

}