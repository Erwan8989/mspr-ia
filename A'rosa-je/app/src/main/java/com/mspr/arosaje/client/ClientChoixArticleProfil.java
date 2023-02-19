package com.mspr.arosaje.client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mspr.arosaje.R;
import com.mspr.arosaje.database.VolleySingleton;
import com.mspr.arosaje.img.ImageManager;

import org.json.JSONObject;

public class ClientChoixArticleProfil extends AppCompatActivity {
    TextView textView_nom, textView_espece, textView_date_ajout, textView_description;
    ImageView img_view;

    EditText commentaire;

    Button btn_ajout_commentaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_choisir_article_profil);

        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        String espece = intent.getStringExtra("espece");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String url_photo = intent.getStringExtra("url_photo");

        textView_nom = (TextView) findViewById(R.id.nom_choisir);
        textView_espece = (TextView) findViewById(R.id.espece_choisir);
        textView_date_ajout = (TextView) findViewById(R.id.date_ajout_choisir);
        textView_description = (TextView) findViewById(R.id.descriptif_choisir);
        img_view = (ImageView) findViewById(R.id.image_profil);
        btn_ajout_commentaire = (Button) findViewById(R.id.btn_ajout_commentaire);
        commentaire = findViewById(R.id.champ_commentaire);

        textView_nom.setText(nom);
        textView_espece.setText(espece);
        textView_date_ajout.setText(date);
        textView_description.setText(description);
        Glide.with(this).load(url_photo).into(img_view);

        btn_ajout_commentaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                try {
                    JSONObject respObj = new JSONObject();
                    respObj.put("commentaire", String.valueOf(commentaire.getText()));

                    VolleySingleton
                            .getInstance(ClientChoixArticleProfil.this)
                            .postData("/plant", respObj, response -> Toast
                                    .makeText(ClientChoixArticleProfil.this, "Plante ajoutée", Toast.LENGTH_SHORT)
                                    .show());
                } catch (Exception e) {
                    e.printStackTrace();
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

    // ********** App bar **********

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_sans_deco, menu);
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
