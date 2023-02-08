package com.mspr.arosaje;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.MailTo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ClientConnexion extends AppCompatActivity {

    Button btn_insc, btn_conn;
    android.widget.EditText mail_conn, password_conn;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_connexion);
        btn_insc = findViewById(R.id.btn_insc);
        btn_conn = findViewById(R.id.btn_conn);
        mail_conn = findViewById(R.id.mail_conn);
        password_conn = findViewById(R.id.password_conn);

        btn_insc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                openActivityClientCInscription();
            }
        });

        btn_conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                String Mail, Password;
                Mail = String.valueOf(mail_conn.getText());
                Password = String.valueOf(password_conn.getText());

                /*if (!Mail.equals("") && !Password.equals("")) {

                } else {
                    Toast.makeText(getApplicationContext(), "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
                }*/
                try {
                    loginAction();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void loginAction() throws JSONException {
        String url = "http://172.20.10.2:8000/login";
        final String user = mail_conn.getText().toString();
        final String pswd = password_conn.getText().toString();
        /*if (userr.isEmpty()) {
            user.setError("Username or Email is required");
            user.requestFocus();
            return;
        }
        if (pswd.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }*/
        RequestQueue queue = Volley.newRequestQueue(ClientConnexion.this);
        try {
            JSONObject respObj = new JSONObject();
            respObj.put("username", user);
            respObj.put("password", pswd);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, respObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ClientConnexion.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                    Intent identification = new Intent(getApplicationContext(), ClientAccueil.class);
                    startActivity(identification);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", String.valueOf(error));
                    Toast.makeText(ClientConnexion.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openActivityClientCInscription() {
        Intent ClientInscription = new Intent(this, ClientInscription.class);
        startActivity(ClientInscription);
        finish();
    }

    /*Bouton retour*/
    @Override
    public void onBackPressed() {
        Intent intentBack = new Intent(this, ClientAccueil.class);
        startActivity(intentBack);
    }

}
