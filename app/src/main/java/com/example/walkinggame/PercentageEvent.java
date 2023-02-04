package com.example.walkinggame;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PercentageEvent {
    private List<Integer> percentages = new ArrayList<>();
    private List<String> actionTexts = new ArrayList<>();
    private final Random random = new Random();
    private TextView textView;
    private Button button;
    private int coinCount;

    public PercentageEvent(Context context, TextView textView, Button button) {
        this.textView = textView;
        this.button = button;
        this.coinCount = XmlReader.readFromXml(context, "coin_count");

        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.action_texts);
            String choosenLocation = "woods"; // assuming this value will come from an Intent in the onCreate method of the Activity

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("item")) {
                    String location = parser.getAttributeValue(null, "location");
                    int percentage = Integer.parseInt(parser.getAttributeValue(null, "percentage"));
                    if (location.equals(choosenLocation)) {
                        percentages.add(percentage);
                        actionTexts.add(parser.nextText());
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
    public int getCoinCount() {
        return coinCount;
    }


    public String generateRandomText() {
        int randomPercentage = random.nextInt(100);
        int accumulatedPercentage = 0;
        String generatedText = "";
        for (int i = 0; i < percentages.size(); i++) {
            accumulatedPercentage += percentages.get(i);
            if (randomPercentage <= accumulatedPercentage) {
                generatedText = actionTexts.get(i);
                if (actionTexts.get(i).equals("You found a coin on the ground")) {
                    coinCount++;
                }
                break;
            }
        }
        XmlWriter.writeToXml(button.getContext(), coinCount);
        return generatedText;
    }
}