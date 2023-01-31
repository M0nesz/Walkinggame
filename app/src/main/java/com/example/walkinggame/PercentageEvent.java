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
    private ArrayList<Integer> percentages = new ArrayList<>();
    private ArrayList<String> randomTexts = new ArrayList<>();
    private Random random = new Random();
    private int coincount;

    public PercentageEvent(Context context) {
        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.random_texts);
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("item")) {
                    int percentage = Integer.parseInt(parser.getAttributeValue(null, "percentage"));
                    percentages.add(percentage);
                    String text = parser.nextText();
                    randomTexts.add(text);
                }
            }
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        coincount = XmlReader.readFromXml(context, "coin_save.xml");
    }

    public void generateRandomText(TextView textView, TextView textView_count, Button button) {
        button.setOnClickListener(v -> {
            int randomPercentage = random.nextInt(100);
            int accumulatedPercentage = 0;
            for (int i = 0; i < percentages.size(); i++) {
                accumulatedPercentage += percentages.get(i);
                if (randomPercentage <= accumulatedPercentage) {
                    textView.setText(randomTexts.get(i));
                    if (randomTexts.get(i).equals("You found a coin on the ground")) {
                        coincount++;
                    }
                    break;
                }
            }
            textView_count.setText(String.valueOf(coincount));
            XmlWriter.writeToXml(v.getContext(), coincount);
        });
    }
}
