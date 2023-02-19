package com.mspr.arosaje.client;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Contact extends AppCompatActivity {
    private String mId, mName, mDescription, mEspece, mDate;

    public Contact(String id, String name, String description, String espece, String date) {
        mId = id;
        mName = name;
        mDescription = description;
        mEspece = espece;
        mDate = date;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getEspece() {
        return mEspece;
    }

    public String getDate() {
        return mDate;
    }

    private static int lastContactId = 0;

    public static ArrayList<Contact> createContactsList(JSONArray arrayList) throws JSONException {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        String tt = null, ttt = null;
        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject jsonobject = arrayList.getJSONObject(i);
            contacts.add(new Contact(jsonobject.getString("id"), jsonobject.getString("name"), jsonobject.getString("description"), jsonobject.getString("specie"), jsonobject.getString("createdAt")));
        }

//        String tt = (String) arrayList.get(1);


        return contacts;
    }
}
