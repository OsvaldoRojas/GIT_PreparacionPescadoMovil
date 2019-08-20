package com.example.simulador_pescado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simulador_pescado.vista.UsuarioLogueado;

public class Turno extends AppCompatActivity {

    private LinearLayout turnoUno;
    private LinearLayout turnoDos;
    private Button botonAceptar;
    private int turno = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_turno);
        iniciaComponentes();
    }

    private void iniciaComponentes(){
        UsuarioLogueado ul = new UsuarioLogueado();
        ul.setNombre("Nombre");
        ul.setApellido_paterno("Usuario");
        UsuarioLogueado.getUsuarioLogueado(ul);

        this.turnoUno = findViewById(R.id.turnoUno);
        this.turnoDos = findViewById(R.id.turnoDos);
        this.botonAceptar = findViewById(R.id.botonAceptar);

        this.turnoUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turno = 1;
                turnoDos.setBackgroundColor( getResources().getColor(R.color.blanco) );
                turnoUno.setBackgroundColor( getResources().getColor(R.color.no_selecion) );
                botonAceptar.setVisibility(View.VISIBLE);
            }
        });

        this.turnoDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turno = 2;
                turnoUno.setBackgroundColor( getResources().getColor(R.color.blanco) );
                turnoDos.setBackgroundColor( getResources().getColor(R.color.no_selecion) );
                botonAceptar.setVisibility(View.VISIBLE);
            }
        });

        this.botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioLogueado.getUsuarioLogueado(null).setTurno(turno);
                Intent intent = new Intent(getApplicationContext(), Navegador.class);
                startActivity(intent);
            }
        });
    }
}
