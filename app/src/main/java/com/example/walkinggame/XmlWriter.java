package com.example.walkinggame;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.StringWriter;

public class XmlWriter {

    // Method to save the coin count to a XML file
    public static void writeToXml(Context context, int coinCount) {
        try {
            // Open the XML file for writing
            FileOutputStream fileout = context.openFileOutput("coin_save.xml", MODE_PRIVATE);

            // Create an XML serializer
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);

            // Start writing the XML document
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "root");
            xmlSerializer.startTag(null, "value");
            xmlSerializer.text(String.valueOf(coinCount));
            xmlSerializer.endTag(null, "value");
            xmlSerializer.endTag(null, "root");
            xmlSerializer.endDocument();

            // Write the contents of the XML document to the file
            fileout.write(writer.toString().getBytes());

            // Close the file
            fileout.close();
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
