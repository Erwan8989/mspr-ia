package com.mspr.arosaje.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private final Context ctx;

    private static final String TAG = "VolleySingleton";

    private VolleySingleton(Context context) {
        this.ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void postData(String url, JSONObject data, Response.Listener<JSONObject> onSuccess) {
        // ********** METTRE SYSTEMATIQUEMENT SA PROPRE IP **********
//        RequestQueue queue = Volley.newRequestQueue(ctx);
        Log.d(TAG, "postData: " + data);

        try {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, data,
                    onSuccess,
                    error -> Toast.makeText(ctx, "Fail to get response = " + error, Toast.LENGTH_SHORT).show());
            addToRequestQueue(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
