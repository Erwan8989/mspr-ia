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
                postData(get_post, get_code_post, get_ville, get_email, get_mdp, get_conf_mdp);
            }
        });
    }

    private void postData(String get_post, String get_code_post, String get_ville, String get_email, String get_mdp, String get_conf_mdp) {
        // url to post our data
//        String url = "http://192.168.1.136:8000/register/customer";
//        loadingPB.setVisibility(View.VISIBLE);

        // creating a new variable for our request queue
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.136:8000/register/customer/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(get_post, get_code_post, get_ville, get_email, get_mdp, get_conf_mdp);

        // calling a method to create a post and passing our modal class.
        Call<DataModal> call = retrofitAPI.createPost(modal);

        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, retrofit2.Response<DataModal> response) {
                // this method is called when we get response from our api.
                Toast.makeText(ClientInscription.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
//                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                /*jobEdt.setText("");
                nameEdt.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                DataModal responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getJob();

                // below line we are setting our
                // string to our text view.
                responseTV.setText(responseString);*/
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Log.e("Error found is : ", t.getMessage());
            }

        });
    }
}