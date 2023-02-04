package com.example.walkinggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView location_text_view;
    TextView textView;
    TextView textView_count;
    Button button;
    Button inventorybutton;
    private int coinCount;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            data.add("Item " + (i + 1));
        }

        ArrayList<String> dataArray = new ArrayList<>(data);

        textView = findViewById(R.id.textView);
        textView_count = findViewById(R.id.textView_count);
        button = findViewById(R.id.button);
        inventorybutton = findViewById(R.id.inventorybutton);
        location_text_view = findViewById(R.id.location_text_view);

        Intent intent = new Intent(this, Inventory.class);
        Button mapButton = findViewById(R.id.mapbutton);
        Button logButton = findViewById(R.id.log);
        intent.putStringArrayListExtra("data", dataArray);
        int coinCountReader;
        textView_count = findViewById(R.id.textView_count);
        Button button = findViewById(R.id.button);

        coinCount = XmlReader.readFromXml(getApplicationContext(), "coin_count");
        textView_count.setText(String.valueOf(coinCount));

        String choosen_location = getIntent().getStringExtra("choosen_location");
        location_text_view.setText(choosen_location != null ? choosen_location : "Woods");

        coinCountReader = XmlReader.readFromXml(getApplicationContext(), "coin_save.xml");
        textView_count.setText(String.valueOf(coinCountReader));

        PercentageEvent generator = new PercentageEvent(this, textView_count, button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String generatedText = generator.generateRandomText();
                textView_count.setText(generatedText);
                coinCount = generator.getCoinCount();
                textView_count.setText(String.valueOf(coinCount));
            }
        });

        inventorybutton.setOnClickListener(v -> startActivity(intent));

        mapButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent1);
        });

        logButton.setOnClickListener(v -> {
            Intent intent12 = new Intent(MainActivity.this, Log.class);
            startActivity(intent12);
        });


    }
}