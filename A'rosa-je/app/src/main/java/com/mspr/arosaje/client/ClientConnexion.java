package com.mspr.arosaje.client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mspr.arosaje.R;
import com.mspr.arosaje.database.VolleySingleton;

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

        btn_insc.setOnClickListener(v2 -> openActivityClientCInscription());

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
        String url = "http://172.20.10.2:8000/login";
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
        try {
            JSONObject respObj = new JSONObject();
            respObj.put("username", mail);
            respObj.put("password", pwd);

            VolleySingleton
                    .getInstance(ClientConnexion.this)
                    .postData(url, respObj, response -> {
                        Toast.makeText(ClientConnexion.this, "Connexion effectuée", Toast.LENGTH_SHORT).show();
                        Log.d("USER", "onResponse: " + response);
                        Intent identification = new Intent(getApplicationContext(), ClientAccueil.class);
                        startActivity(identification);
                        finish();
                    });
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
