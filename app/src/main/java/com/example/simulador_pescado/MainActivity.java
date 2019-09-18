package com.example.simulador_pescado;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Onclick(View view){
        switch (view.getId()){
            case R.id.Descongelado:
                Intent intent = new Intent(this, Navegador.class);
                startActivity(intent);


        }
    }

    @Override
    public void onBackPressed() {

    }
}
