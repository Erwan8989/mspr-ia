package com.mspr.arosaje.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Contact {
    private String mId, mName, mDescription;

    public Contact(String id, String name, String description) {
        mId = id;
        mName = name;
        mDescription = description;
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

    private static int lastContactId = 0;

    public static ArrayList<Contact> createContactsList(JSONArray arrayList) throws JSONException {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        String tt = null, ttt = null;
        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject jsonobject = arrayList.getJSONObject(i);
            contacts.add(new Contact(jsonobject.getString("id"), jsonobject.getString("name"), jsonobject.getString("description")));
        }

//        String tt = (String) arrayList.get(1);


        return contacts;
    }
}
