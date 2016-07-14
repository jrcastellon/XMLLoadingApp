package com.jorgecastellonjr.xmlloadingapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadXmlData();
        outputDataToLog();
    }

    public void outputDataToLog(){
        for (Person person : persons){
            Log.i("Test", "Name: " + person.name);
            Log.i("Test", "Age: " + person.age);
            Log.i("Test", "Limbs: " + person.limbs);
        }
    }

    public void loadXmlData(){
        try{
            Resources res = this.getResources();
            XmlPullParser xpp = res.getXml(R.xml.resources);
            String tagname = new String();
            String name = new String();
            String age = new String();
            String limbs = new String();

            int evenType = xpp.getEventType();
            while (evenType != XmlPullParser.END_DOCUMENT){
                tagname = xpp.getName();
                if (evenType == XmlPullParser.START_DOCUMENT){
                    Log.i("test", "XML Document Start");
                }else if (evenType == XmlPullParser.START_TAG){
                    Log.i("test", "XML Tag Start " + tagname);
                    if ("name".equals(tagname)){
                        xpp.next();
                        evenType = xpp.getEventType();
                        if (evenType == XmlPullParser.TEXT){
                            name = xpp.getText();
                        }
                    } else if ("age".equals(tagname)){
                        xpp.next();
                        evenType = xpp.getEventType();
                        if (evenType == XmlPullParser.TEXT){
                            age = xpp.getText();
                        }
                    } else if ("limbs".equals(tagname)){
                        xpp.next();
                        evenType = xpp.getEventType();
                        if (evenType == XmlPullParser.TEXT){
                            limbs = xpp.getText();
                            Person person = new Person(name,age,limbs);
                            persons.add(person);
                        }
                    }
                }
                evenType = xpp.next();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
