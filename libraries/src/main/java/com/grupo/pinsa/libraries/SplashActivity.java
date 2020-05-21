package com.grupo.pinsa.libraries;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo.pinsa.libraries.common.Constantes;
import com.grupo.pinsa.libraries.common.Utilerias;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grupopinsa_activity_splash);

        /*
          Tiempo de espera para simular carga de información
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utilerias.newActivity(SplashActivity.this, Utilerias.getClass(Constantes.CLASS_BEFORE_SPLASH));
                finish();
            }
        }, Constantes.SPLASH_DISPLAY_TIME);
    }
}
