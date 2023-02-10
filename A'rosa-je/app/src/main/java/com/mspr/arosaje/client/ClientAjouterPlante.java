package com.mspr.arosaje.client;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mspr.arosaje.R;

import org.json.JSONObject;

public class ClientAjouterPlante extends AppCompatActivity {

    Button btn_ajout_image, btn_enregistrer_plante;
    ImageView click_image_id;
    android.widget.EditText nom, espece, description;

    private static final int pic_id = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_ajouter_article);

        btn_enregistrer_plante = (Button) findViewById((R.id.btn_enregistrer_plante));
        btn_ajout_image = (Button) findViewById(R.id.btn_ajout_image);
        click_image_id = (ImageView) findViewById(R.id.click_image);
        nom = findViewById(R.id.nom_ajout_plante);
        espece = findViewById(R.id.espece_ajout_plante);
        description = findViewById(R.id.description_ajout_plante);

        btn_ajout_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id);
            }
        });

        btn_enregistrer_plante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                String get_user, get_nom, get_espece, get_description;
                get_nom = String.valueOf(nom.getText());
                get_espece = String.valueOf(espece.getText());
                get_description = String.valueOf(description.getText());
                get_user = "1";
                postDataUsingVolley(get_user, get_nom, get_espece, get_description);
            }
        });
    }

    private void postDataUsingVolley(String get_user, String get_nom, String get_espece, String get_description) {
        // ********** METTRE SYSTEMATIQUEMENT SA PROPRE IP **********
        String url = "http://172.20.10.2:8000/plant";

        RequestQueue queue = Volley.newRequestQueue(ClientAjouterPlante.this);

        try {
            JSONObject respObj = new JSONObject();
            Log.e("respobj1", String.valueOf(respObj));
//            respObj.put("user", get_user);
            respObj.put("name", get_nom);
            respObj.put("type", get_espece);
            respObj.put("description", get_description);

            Log.e("respobj", String.valueOf(respObj));

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, respObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ClientAjouterPlante.this, "Plante ajouté", Toast.LENGTH_SHORT).show();
                    Intent redirect = new Intent(getApplicationContext(), ClientAccueil.class);
                    startActivity(redirect);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", String.valueOf(error));
                    Toast.makeText(ClientAjouterPlante.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Match the request 'pic id with requestCode
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            // Set the image in imageview for display
            click_image_id.setImageBitmap(photo);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intentBack = new Intent(this, ClientAccueil.class);
        startActivity(intentBack);
    }

    private void Ajout_plante() {

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
