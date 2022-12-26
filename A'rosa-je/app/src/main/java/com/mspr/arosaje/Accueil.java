package com.mspr.arosaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.zip.Inflater;


public class Accueil extends Activity {

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        b1 = (Button) findViewById(R.id.btn_gardienner);
        b2 = (Button) findViewById(R.id.btn_monprofil);
        b3 = (Button) findViewById(R.id.btn_addPlant);

        // ***************** Changement de page au clic *****************

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                openActivityGardienner();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                openActivityProfil();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                openActivityAjouterPlante();
            }
        });

    }

    public void openActivityGardienner(){
        Intent gardienner = new Intent(this, Gardiennage.class);
        startActivity(gardienner);
        finish();
    }

    public void openActivityProfil(){
        Intent monProfil = new Intent(this, Profil.class);
        startActivity(monProfil);
        finish();
    }

    public void openActivityAjouterPlante(){
        Intent ajouterPlante = new Intent(this, AjouterPlante.class);
        startActivity(ajouterPlante);
        finish();
    }
}
