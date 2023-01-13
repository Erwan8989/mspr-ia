package com.mspr.arosaje;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class AjouterPlante extends Activity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_ajouter_article);

        b1 = (Button) findViewById(R.id.btn_resgisterPlant);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Ajout_plante();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intentBack = new Intent(this, Accueil.class);
        startActivity(intentBack);
    }

    private void Ajout_plante(){

    }

}
