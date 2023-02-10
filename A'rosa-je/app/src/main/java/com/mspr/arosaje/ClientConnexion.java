package com.mspr.arosaje;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


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
                String mail, password;
                mail = String.valueOf(mail_conn.getText());
                password = String.valueOf(password_conn.getText());

                try {
                    loginAction(mail, password);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void loginAction(String mail, String pwd) throws JSONException {
        String url = "http://172.20.10.4:8000/login";
        if (mail.isEmpty()) {
            mail_conn.setError("Username or Email is required");
            mail_conn.requestFocus();
            return;
        }
        if (pwd.isEmpty()) {
            password_conn.setError("Password is required");
            password_conn.requestFocus();
            return;
        }
        RequestQueue queue = Volley.newRequestQueue(ClientConnexion.this);
        try {
            JSONObject respObj = new JSONObject();
            respObj.put("username", mail);
            respObj.put("password", pwd);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, respObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ClientConnexion.this, "Connexion effectuée", Toast.LENGTH_SHORT).show();
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
