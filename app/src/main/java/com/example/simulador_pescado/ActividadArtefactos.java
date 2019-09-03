package com.example.simulador_pescado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simulador_pescado.adaptadores.AdaptadorMaquinariaArtefacto;
import com.example.simulador_pescado.vista.Artefacto;

import java.io.Serializable;
import java.util.List;

public class ActividadArtefactos extends AppCompatActivity {

    private AdaptadorMaquinariaArtefacto adaptador;
    private ListView vistaArtefactos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_artefactos);
        iniciaComponentes();
    }

    @Override
    public void onBackPressed() {
    }

    private void iniciaComponentes(){
        Button botonCancelar = findViewById(R.id.botonCancelar);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button botonAceptar = findViewById(R.id.botonAceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardaArtefactos();
            }
        });

        List<Artefacto> catalogoArtefactos = (List<Artefacto>) getIntent().getSerializableExtra("catalogo");
        this.vistaArtefactos = findViewById(R.id.listaArtefactos);
        this.adaptador = new AdaptadorMaquinariaArtefacto(getApplicationContext(), catalogoArtefactos);
        this.vistaArtefactos.setAdapter(this.adaptador);
    }

    private void guardaArtefactos(){
        Intent valores = new Intent();
        valores.putExtra("artefactos", (Serializable) this.adaptador.getListaArtefactos() );
        setResult(RESULT_OK, valores);
        finish();
    }
}
