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
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            // Add "Item (i+1)" to the list
            data.add("Item " + (i + 1));
        }

        ArrayList<String> dataArray = new ArrayList<>(data);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        inventorybutton = findViewById(R.id.inventorybutton);
        String location = getIntent().getStringExtra("location");
        TextView locationTextView = findViewById(R.id.location_text_view);
        locationTextView.setText(location);
        TextView textView_count = findViewById(R.id.textView_count);



        // Read the coin count from "coin_save.xml"
        int coinCountReader = XmlReader.readFromXml(getApplicationContext(), "coin_save.xml");
        textView_count.setText(String.valueOf(coinCountReader));



        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        PercentageEvent randomTextGenerator = new PercentageEvent(this);
        randomTextGenerator.generateRandomText(textView, textView_count, button);


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
        logButton.setOnClickListener(v -> {
            Intent intent12 = new Intent(MainActivity.this, Log.class);
            startActivity(intent12);
        });


    }
}