package com.example.walkinggame;

import android.content.Context;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class XmlReader {

    public static int readFromXml(Context context, String fileName) {
        int coincount = 0;
        try {
            // Create XmlPullParser object and set the input file
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(context.openFileInput(fileName), "UTF-8");

            // Get the type of the first event
            int eventType = parser.getEventType();

            // Loop until the end of the document
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // If the event type is a start tag and the name is "value"
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("value")) {
                        // Parse the text as an integer and set it as the value of "coincount"
                        coincount = Integer.parseInt(parser.nextText());
                    }
                }
                // Move to the next event
                eventType = parser.next();
            }
        } catch (IOException | XmlPullParserException e) {
            // Print the stack trace in case of an error
            e.printStackTrace();
        }
        return coincount;
    }
}
