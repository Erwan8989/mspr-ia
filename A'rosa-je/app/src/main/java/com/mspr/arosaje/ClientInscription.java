package com.mspr.arosaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import androidx.appcompat.app.AppCompatActivity;

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

        b1 = findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String get_post, get_code_post, get_ville, get_email, get_mdp, get_conf_mdp;
                get_post = String.valueOf(post.getText());
                get_code_post = String.valueOf(code_post.getText());
                get_ville = String.valueOf(ville.getText());
                get_email = String.valueOf(email.getText());
                get_mdp = String.valueOf(mdp.getText());
                get_conf_mdp = String.valueOf(conf_mdp.getText());

                // Verification of the type of the value entered in the EditText
                if (!get_post.equals("") && !get_code_post.equals("") && !get_ville.equals("") && !get_email.equals("") && !get_mdp.equals("") && !get_conf_mdp.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
//                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[6];
                            field[0] = "post";
                            field[1] = "code_post";
                            field[2] = "ville";
                            field[3] = "email";
                            field[4] = "mdp";
                            field[5] = "conf_mdp";

                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = "post";
                            data[1] = "code_post";
                            data[2] = "ville";
                            data[3] = "email";
                            data[4] = "mdp";
                            data[5] = "conf_mdp";

                            PutData putData = new PutData("http://127.0.0.1/register/customer", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);

                                    String result = putData.getResult();

//                                    if (result.equals("Sign Up Success")) {
//                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                                        Intent identification = new Intent(getApplicationContext(), visiteur_medecin.class);
//                                        startActivity(identification);
//                                        finish();
                                  /*  } else {

                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                    }*/
                                }
                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /*Bouton retour*/
    @Override
    public void onBackPressed() {
        Intent intentBack = new Intent(this, ClientAccueil.class);
        startActivity(intentBack);
    }


}
