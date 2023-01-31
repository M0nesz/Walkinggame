package com.example.walkinggame;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Backbutton extends AppCompatActivity {
    Button backButton;

    @ Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());
    }
}