package com.mspr.arosaje.auth;

import android.content.Context;


import com.android.volley.Response;

import com.mspr.arosaje.database.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthManager {
    private static AuthManager instance;

    private Context ctx;

    private VolleySingleton db;

    private static Object User;

    private AuthManager(Context ctx) {
        this.ctx = ctx;
        this.db = VolleySingleton.getInstance(ctx);
    }

    public static synchronized AuthManager getInstance(Context ctx) {
        if (instance == null) {
            instance = new AuthManager(ctx);
        }
        return instance;
    }

    public void login(String mail, String pwd, Response.Listener<JSONObject> onSuccess) throws JSONException {
        String url = "http://172.20.10.4:8000/login";

        try {
            JSONObject respObj = new JSONObject();
            respObj.put("username", mail);
            respObj.put("password", pwd);

            this.db
                    .postData(url, respObj, onSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getUser() {
        return User;
    }

    public static void setUser(Object user) {
        User = user;
    }
}
