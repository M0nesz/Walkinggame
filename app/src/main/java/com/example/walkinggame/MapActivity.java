package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to map_activity.xml
        setContentView(R.layout.map_activity);

        // Create an intent for the MainActivity class
        Intent mainIntent = new Intent(this, MainActivity.class);

        // Find the back button in the layout
        Button backButton = findViewById(R.id.back_button);

        // Set an onClickListener for the back button
        backButton.setOnClickListener(v -> startActivity(mainIntent));

        // Find the woods button in the layout
        Button woodsButton = findViewById(R.id.woods_button);

        // Set an onClickListener for the woods button
        woodsButton.setOnClickListener(v -> {
            // Create an intent for the MainActivity class with "location" extra set to "Woods"
            Intent intent = new Intent(MapActivity.this, MainActivity.class);
            intent.putExtra("location", "Woods");
            startActivity(intent);
        });
    }
}