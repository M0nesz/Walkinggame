package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        //backbutton
        Intent intent = new Intent(this, MainActivity.class);// creates an intent for the MainActivity class
        Button backButton = findViewById(R.id.back_button);// finds the back button in the layout
        backButton.setOnClickListener(v-> startActivity(intent));// sets an onClickListener for the back button and starts the MainActivity when the back button is clicked

        Button woodsButton = findViewById(R.id.woods_button);
        woodsButton.setOnClickListener(v -> {
            Intent mainIntent = new Intent(MapActivity.this, MainActivity.class);
            mainIntent.putExtra("location", "Woods");
            startActivity(mainIntent);
        });

    }

}
