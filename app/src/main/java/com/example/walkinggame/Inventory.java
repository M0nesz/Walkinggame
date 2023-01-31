package com.example.walkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class Inventory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory); // sets the layout for the activity
        ArrayList<String> data = getIntent().getStringArrayListExtra("data"); // gets data passed from previous activity

        MyGridAdapter adapter = new MyGridAdapter(this, data); // creates a new adapter with the data passed from previous activity
        GridView gridView = findViewById(R.id.gridView);// finds the gridview in the layout
        gridView.setAdapter(adapter); // sets the adapter for the gridview
        //Backbutton
        Intent intent = new Intent(this, MainActivity.class);// creates an intent for the MainActivity class
        Button backButton = findViewById(R.id.back_button);// finds the back button in the layout
        backButton.setOnClickListener(v-> startActivity(intent));// sets an onClickListener for the back button and starts the MainActivity when the back button is clicked

    }

}