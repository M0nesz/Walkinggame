package com.example.walkinggame;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PercentageEvent {
    // List to store percentage values for each random text
    private ArrayList<Integer> percentages = new ArrayList<>();
    // List to store random text values
    private ArrayList<String> randomTexts = new ArrayList<>();
    // Object to generate random numbers
    private Random random = new Random();
    // Counter for number of coins
    private int coincount;

    // Constructor for the PercentageEvent class
    public PercentageEvent(Context context) {
        try {
            // Get the XML parser for the random_texts file
            XmlPullParser parser = context.getResources().getXml(R.xml.random_texts);
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                // If the current event is a START_TAG and its name is "item"
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("item")) {
                    // Get the percentage attribute value from the item tag
                    int percentage = Integer.parseInt(parser.getAttributeValue(null, "percentage"));
                    // Add the percentage to the list
                    percentages.add(percentage);
                    // Get the text value from the item tag
                    String text = parser.nextText();
                    // Add the text to the list
                    randomTexts.add(text);
                }
            }
        } catch (IOException | XmlPullParserException e) {
            // Print the stack trace of the exception
            e.printStackTrace();
        }
        // Read the coin count from the XML file
        coincount = XmlReader.readFromXml(context, "coin_save.xml");
    }

    // Method to generate a random text and update the coin count
    public void generateRandomText(TextView textView, TextView textView_count, Button button) {
        // Set an onClickListener for the button
        button.setOnClickListener(v -> {
            // Generate a random number between 0 and 100
            int randomPercentage = random.nextInt(100);
            // Accumulated percentage value
            int accumulatedPercentage = 0;
            // Loop through the list of percentages
            for (int i = 0; i < percentages.size(); i++) {
                // Add the current percentage to the accumulated percentage
                accumulatedPercentage += percentages.get(i);
                // If the random percentage is less than or equal to the accumulated percentage
                if (randomPercentage <= accumulatedPercentage) {
                    // Set the textView to the corresponding random text
                    textView.setText(randomTexts.get(i));
                    // increment the coin count when "You found a coin on the ground" is generated
                    if (randomTexts.get(i).equals("You found a coin on the ground")) {
                        coincount++;
                    }
                    // break the loop after finding the corresponding text
                    break;
                }
            }
            // set the text of the text view for the coin count to the current coin count
            textView_count.setText(String.valueOf(coincount));
            // write the current coin count to XML
            XmlWriter.writeToXml(v.getContext(), coincount);
        });
    }
}