package com.example.walkinggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Declare TextView and Button
    TextView textView;
    TextView textView_count;
    Button button;

    // Initialize Button for accessing inventory
    Button inventorybutton;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to activity_main.xml
        setContentView(R.layout.activity_main);

        // Create a list of 25 items
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            // Add "Item (i+1)" to the list
            data.add("Item " + (i + 1));
        }

        // Convert the list to an ArrayList
        ArrayList<String> dataArray = new ArrayList<>(data);

        // Find the TextViews and Buttons in the layout
        textView = findViewById(R.id.textView);
        textView_count = findViewById(R.id.textView_count);
        button = findViewById(R.id.button);
        inventorybutton = findViewById(R.id.inventorybutton);

        // Get the location passed from the previous activity
        String location = getIntent().getStringExtra("location");
        TextView locationTextView = findViewById(R.id.location_text_view);
        locationTextView.setText(location);

        // Read the coin count from "coin_save.xml"
        int coinCountReader = XmlReader.readFromXml(getApplicationContext(), "coin_save.xml");
        textView_count.setText(String.valueOf(coinCountReader));

        // Generate random text and set it to textView
        PercentageEvent randomTextGenerator = new PercentageEvent(this);
        randomTextGenerator.generateRandomText(textView, textView_count, button);

        // Start new activity when inventory button is clicked
        Intent intent = new Intent(this, Inventory.class);
        intent.putStringArrayListExtra("data", dataArray);

        // Set an OnClickListener for the inventory button
        inventorybutton.setOnClickListener(v -> startActivity(intent));

        // Set an OnClickListener for the map button
        Button mapButton = findViewById(R.id.mapbutton);
        mapButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent1);
        });

        // Set an OnClickListener for the log button
        Button logButton = findViewById(R.id.log);
        logButton.setOnClickListener(v -> {
            Intent intent12 = new Intent(MainActivity.this, Log.class);
            startActivity(intent12);
        });


    }
}