package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MapActivity extends AppCompatActivity {
    public static String choosen_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        // Create an intent for the MainActivity class
        Intent mainIntent = new Intent(this, MainActivity.class);

        Button backButton = findViewById(R.id.back_button);
        Button castle_button = findViewById(R.id.castle_button);
        Button woodsButton = findViewById(R.id.woods_button);
        TextView textView3 = findViewById(R.id.textView3);

        // Set an onClickListener for the back button
        backButton.setOnClickListener(v -> startActivity(mainIntent));

        woodsButton.setOnClickListener(v -> {
            Intent woodsIntent = new Intent(MapActivity.this, MainActivity.class);
            woodsIntent.putExtra("location", "Woods");
            choosen_location = "Woods";
            startActivity(woodsIntent);
        });
        castle_button.setOnClickListener(v -> {
            Intent mainCastleActivityIntent = new Intent(MapActivity.this, MainActivity.class);
            mainCastleActivityIntent.putExtra("choosen_location", choosen_location);
            startActivity(mainCastleActivityIntent);
            choosen_location = "Castle";
        });
    }
}