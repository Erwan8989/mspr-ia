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
                login();
            }
        });
    }

    private void login() {
        //Getting values from edit texts
        final String email = mail_conn.getText().toString().trim();
        final String password = password_conn.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://172.20.10.2:8000/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response ", response);
                        //If we are getting success from server
                        if (response.equalsIgnoreCase("200")) {
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = ClientConnexion.this.getSharedPreferences("test", Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            /*//Adding values to editor
                            editor.putBoolean("test", true);
                            editor.putString(Config.EMAIL_SHARED_PREF, email);*/

                            //Saving values to editor
                            editor.commit();
                            //Starting profile activity
                            Intent intent = new Intent(getApplicationContext(), ClientAccueil.class);
                            startActivity(intent);
                            Log.e("Success", response);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(ClientConnexion.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", String.valueOf(error));
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("username", email);
                params.put("password", password);

                Log.e("params1 ", String.valueOf(params));
                //returning parameter
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                String creds = String.format("%s:%s","username","password");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);

                Log.e("params2 ", String.valueOf(params));
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.e("Request queue ", String.valueOf(requestQueue));
        Log.e("String", String.valueOf(stringRequest));
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
