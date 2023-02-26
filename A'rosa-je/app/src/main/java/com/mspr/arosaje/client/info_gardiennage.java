package com.mspr.arosaje.client;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class info_gardiennage extends AppCompatActivity {
    private String mId, mName, mDescription, mEspece, mDate;
    JSONArray mUrlPhoto;

    public info_gardiennage(String id, String name, String description, String espece, String date, JSONArray url_photo) {
        mId = id;
        mName = name;
        mDescription = description;
        mEspece = espece;
        mDate = date;
        mUrlPhoto = url_photo;
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

    public String getUrlPhoto() throws JSONException {
        return mUrlPhoto.getJSONObject(0).getString("src");
    }

    public static ArrayList<info_gardiennage> createList(JSONArray arrayList) throws JSONException {
        ArrayList<info_gardiennage> infoplants = new ArrayList<info_gardiennage>();

        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject jsonobject = arrayList.getJSONObject(i);
            infoplants.add(new info_gardiennage(jsonobject.getString("id"), jsonobject.getString("name"), jsonobject.getString("description"), jsonobject.getString("specie"), jsonobject.getString("createdAt"), jsonobject.getJSONArray("photos")));
        }

        return infoplants;
    }
}
