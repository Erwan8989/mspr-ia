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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClientInscription extends AppCompatActivity {

    android.widget.EditText post, code_post, ville, email, mdp, conf_mdp;
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
        mdp = findViewById(R.id.password);
        conf_mdp = findViewById(R.id.confirm_password);

        String get_post, get_code_post, get_ville, get_email, get_mdp, get_conf_mdp;
        get_post = String.valueOf(post.getText());
        get_code_post = String.valueOf(code_post.getText());
        get_ville = String.valueOf(ville.getText());
        get_email = String.valueOf(email.getText());
        get_mdp = String.valueOf(mdp.getText());
        get_conf_mdp = String.valueOf(conf_mdp.getText());

        b1 = findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                /*if (nameEdt.getText().toString().isEmpty() && jobEdt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                // calling a method to post the data and passing our name and job.
                postDataUsingVolley(get_post, get_code_post, get_ville, get_email, get_mdp, get_conf_mdp);
            }
        });
    }

    private void postDataUsingVolley(String get_post, String get_code_post, String get_ville, String get_email, String get_mdp, String get_conf_mdp) {
        // url to post our data
        String url = "http://192.168.1.79:8000/register/customer";
//        loadingPB.setVisibility(View.VISIBLE);

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(ClientInscription.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty
//                loadingPB.setVisibility(View.GONE);

                post.setText("");
                code_post.setText("");
                ville.setText("");
                email.setText("");
                mdp.setText("");
                conf_mdp.setText("");

                // on below line we are displaying a success toast message.
                Toast.makeText(ClientInscription.this, "Data added to API", Toast.LENGTH_SHORT).show();
                try {
                    // on below line we are parsing the response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    // below are the strings which we
                    // extract from our json object.
                    String name = respObj.getString("name");
                    String job = respObj.getString("job");

                    // on below line we are setting this string s to our text view.
//                    responseTV.setText("Name : " + name + "\n" + "Job : " + job);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(ClientInscription.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("street", "tsdf");
                params.put("zipCode", "tsdf");
                params.put("city", "tsdf");
                params.put("email", "tsdf");
                params.put("password", "tsdf");
                params.put("firstname", "tsdf");
                params.put("lastname", "sfdcfscdc");
                params.put("streetNumber", "sdfcsd");
                //params.put("get_conf_mdp", get_conf_mdp);

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

}