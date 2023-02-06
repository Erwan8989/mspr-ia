package com.mspr.arosaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientInscription extends AppCompatActivity {

    android.widget.EditText post, code_post, ville, email, mdp, conf_mdp, numero_rue, prenom, nom;
    Button b1;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_inscription);

        post = findViewById(R.id.adress_postal);
        code_post = findViewById(R.id.code_postal);
        ville = findViewById(R.id.ville);
        email = findViewById(R.id.email_address);
        mdp = findViewById(R.id.password_insc);
        conf_mdp = findViewById(R.id.confirm_password);
        numero_rue = findViewById(R.id.numero_rue);
        prenom = findViewById(R.id.prenom_insc);
        nom = findViewById(R.id.nom_insc);

        Log.e("post", String.valueOf(post));

        b1 = findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String get_post, get_code_post, get_ville, get_email, get_mdp, get_conf_mdp, get_numero_rue, get_prenom, get_nom;
                get_post = String.valueOf(post.getText());
                get_code_post = String.valueOf(code_post.getText());
                get_ville = String.valueOf(ville.getText());
                get_email = String.valueOf(email.getText());
                get_mdp = String.valueOf(mdp.getText());
                get_conf_mdp = String.valueOf(conf_mdp.getText());
                get_numero_rue = String.valueOf(numero_rue.getText());
                get_prenom = String.valueOf(prenom.getText());
                get_nom = String.valueOf(nom.getText());
                postDataUsingVolley(get_post, get_code_post, get_ville, get_email, get_mdp, get_conf_mdp, get_numero_rue, get_prenom, get_nom);
            }
        });
    }

    private void postDataUsingVolley(String get_post, String get_code_post, String get_ville, String get_email, String get_mdp, String get_conf_mdp, String get_numero_rue, String get_prenom, String get_nom) {
        // ********** METTRE SYSTEMATIQUEMENT SA PROPRE IP **********
        String url = "http://192.168.1.136:8000/register/customer";

        RequestQueue queue = Volley.newRequestQueue(ClientInscription.this);

        try {
            JSONObject respObj = new JSONObject();
            respObj.put("street", get_post);
            respObj.put("zipCode", get_code_post);
            respObj.put("city", get_ville);
            respObj.put("email", get_email);
            respObj.put("password", get_mdp);
            respObj.put("firstname", get_prenom);
            respObj.put("lastname", get_nom);
            respObj.put("streetNumber", get_numero_rue);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, respObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ClientInscription.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                    Intent identification = new Intent(getApplicationContext(), ClientConnexion.class);
                    startActivity(identification);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ClientInscription.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}