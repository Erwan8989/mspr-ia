package com.mspr.arosaje.client;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mspr.arosaje.R;
import com.mspr.arosaje.database.VolleySingleton;

import org.json.JSONException;

import java.util.ArrayList;

public class ClientProfil extends AppCompatActivity {
    Context context = this;
    ArrayList<String> listdata = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_profil);

        // Lookup the recyclerview in activity layout
        RecyclerView rvPlants = (RecyclerView) findViewById(R.id.vertical_recycle_view);

        try {
            VolleySingleton
                    .getInstance(ClientProfil.this)
                    .getData("/plant", response -> {
                        try {
                            Log.e("response", String.valueOf(response));
                            // Initialize infoplants
                            ArrayList<info_plant> infoplants = info_plant.createList(response);
                            // Create adapter passing in the sample plant data
                            PlantAdapter adapter = new PlantAdapter(infoplants);
                            // Attach the adapter to the recyclerview to populate items
                            rvPlants.setAdapter(adapter);
                            // Set layout manager to position the items
                            rvPlants.setLayoutManager(new LinearLayoutManager(this));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
