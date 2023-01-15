package com.mspr.arosaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ClientAccueil extends AppCompatActivity {

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_accueil);

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

    /*Page d'acceuil donc le bouton retour quitte l'app? */
    /*D'apres l'interWEB un bouton exit app n'est pas une bonne pratique*/
    /*@Override
    public void onBackPressed() {
        Intent intentBack = new Intent(this, saisir.class);
        startActivity(intentBack);
    }*/

    public void openActivityGardienner() {
        Intent gardienner = new Intent(this, ClientGardiennage.class);
        startActivity(gardienner);
        finish();
    }

    public void openActivityProfil() {
        Intent monProfil = new Intent(this, ClientProfil.class);
        startActivity(monProfil);
        finish();
    }

    public void openActivityAjouterPlante() {
        Intent ajouterPlante = new Intent(this, ClientAjouterPlante.class);
        startActivity(ajouterPlante);
        finish();
    }

    // ********** App bar **********

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_deco, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.deco) {
            Intent accueil = new Intent(this, ClientConnexion.class);
            startActivity(accueil);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
