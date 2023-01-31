package com.example.walkinggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    TextView textView;
    TextView textView_count;
    Button button;

    int randomPercentage;
    int coincount;

    // Initialize Button for accessing inventory
    Button inventorybutton;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to activity_main.xml
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            // Add "Item (i+1)" to the list
            data.add("Item " + (i + 1));
        }
        ArrayList<String> dataArray = new ArrayList<>(data);
        List<String> randomTexts = new ArrayList<>();
        List<Integer> percentages = new ArrayList<>();
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView_count = findViewById(R.id.Coinscounter);
        button = findViewById(R.id.button);
        inventorybutton = findViewById(R.id.inventorybutton);

        // Read the coin count from "coin_save.xml"
        int coinCountReader = XmlReader.readFromXml(getApplicationContext(), "coin_save.xml");
        textView_count.setText(String.valueOf(coinCountReader));



        // Initialize a list to store 25 items


        try {
            // Get the XML parser and set the input to be the "random_texts.xml" file
            XmlPullParser parser = getResources().getXml(R.xml.random_texts);
            // Parse the XML file until the end of the document is reached
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                // Check if the current event is a start tag and its name is "item"
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("item")) {
                    // If the start tag is an "item" tag, retrieve its "percentage" attribute value
                    int percentage = Integer.parseInt(parser.getAttributeValue(null, "percentage"));
                    // Add the percentage to the list of percentages
                    percentages.add(percentage);
                    // Retrieve the text value of the "item" tag
                    String text = parser.nextText();
                    // Add the text to the list of random texts
                    randomTexts.add(text);
                }
            }
        } catch (IOException | XmlPullParserException e) {
            // Print the stack trace if there is an error in reading the XML file
            e.printStackTrace();
        }
        // Read the current coin count from the XML file
        coincount = XmlReader.readFromXml(getApplicationContext(), "coin_save.xml");

        // Display the current coin count in the textView
        textView_count.setText(String.valueOf(coincount));

        // Set the onClickListener for the button
        button.setOnClickListener(v -> {
            randomPercentage = random.nextInt(100);
            // Initialize the accumulatedPercentage
            int accumulatedPercentage = 0;
            // Loop through all the percentages
            for (int i = 0; i < percentages.size(); i++) {
                accumulatedPercentage += percentages.get(i);
                if (randomPercentage <= accumulatedPercentage) {
                    textView.setText(randomTexts.get(i));
                    // If the text is "You found a coin on the ground", increment the coin count
                    if (randomTexts.get(i).equals("You found a coin on the ground")) {
                        coincount++;
                    }
                    break;
                }
            }
            textView_count.setText(String.valueOf(coincount));
            // Call the writeToXml method to save the updated coin count to the XML file
            XmlWriter.writeToXml(v.getContext(), coincount);
        });


        // Start new activity when inventory button is clicked
        Intent intent = new Intent(this, Inventory.class);

        intent.putStringArrayListExtra("data", dataArray);
        // Set an OnClickListener for the inventory button
        inventorybutton.setOnClickListener(v -> startActivity(intent));
        Button mapButton = findViewById(R.id.mapbutton);
        mapButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent1);
        });
        Button logButton = findViewById(R.id.log);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Log.class);
                startActivity(intent);
            }
        });
    }
}