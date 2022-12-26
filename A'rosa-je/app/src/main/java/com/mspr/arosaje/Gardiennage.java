package com.mspr.arosaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gardiennage extends Activity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardiennage);

        b1 = (Button) findViewById(R.id.btn_register);

        // ***************** Changement de page au clic *****************

    }

}
