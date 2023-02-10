package com.mspr.arosaje.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

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
        Log.d(TAG, "postData: " + data);

        try {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, data,
                    onSuccess,
                    error -> Toast.makeText(ctx, "Fail to get response = " + error, Toast.LENGTH_SHORT).show())
            {
                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    assert response.headers != null;
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    Log.d("cookies",rawCookies);
                    return super.parseNetworkResponse(response);
                }
            }
            ;
            addToRequestQueue(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
