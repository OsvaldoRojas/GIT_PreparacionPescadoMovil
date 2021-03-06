package com.grupo.pinsa.sip.views.simulador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.conexion.CargaCatalogos;
import com.grupo.pinsa.sip.views.simulador.modelo.UsuarioLogueado;

public class Turno extends AppCompatActivity {

    private LinearLayout turnoUno;
    private LinearLayout turnoDos;
    private LinearLayout turnoTres;
    private Button botonAceptar;
    private int turno = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_turno);
        iniciaComponentes();
    }

    private void iniciaComponentes(){
        /*String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String usuario = getIntent().getStringExtra("usuario");
        int idEmpleado = getIntent().getIntExtra("idEmpleado", 0);
        int rol = getIntent().getIntExtra("rol", 1);

        UsuarioLogueado ul = new UsuarioLogueado();
        ul.setNombre(nombre);
        ul.setApellido_paterno(apellido);
        ul.setClave_usuario(usuario);
        ul.setId_empleado(idEmpleado);
        ul.setId_rol(rol);*/

        UsuarioLogueado ul = UsuarioLogueado.getUsuarioLogueado();
        ul.setNombre("Juan");
        ul.setApellido_paterno("Perez");
        ul.setClave_usuario("usuarioerp");
        ul.setId_empleado(7000002);
        ul.setId_rol(1);//1 = auxiliar; 2 = mecanico; 3 = supervisor

        this.turnoUno = findViewById(R.id.turnoUno);
        this.turnoDos = findViewById(R.id.turnoDos);
        this.turnoTres = findViewById(R.id.turnoTres);
        this.botonAceptar = findViewById(R.id.botonAceptar);

        this.turnoUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turno = 1;
                turnoDos.setBackground( getResources().getDrawable(R.drawable.contenedor_turno) );
                turnoTres.setBackground( getResources().getDrawable(R.drawable.contenedor_turno) );
                turnoUno.setBackground( getResources().getDrawable(R.drawable.contenedor_turno_seleccionado) );
                botonAceptar.setVisibility(View.VISIBLE);
            }
        });

        this.turnoDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turno = 2;
                turnoUno.setBackground( getResources().getDrawable(R.drawable.contenedor_turno) );
                turnoTres.setBackground( getResources().getDrawable(R.drawable.contenedor_turno) );
                turnoDos.setBackground( getResources().getDrawable(R.drawable.contenedor_turno_seleccionado) );
                botonAceptar.setVisibility(View.VISIBLE);
            }
        });

        this.turnoTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turno = 3;
                turnoUno.setBackground( getResources().getDrawable(R.drawable.contenedor_turno) );
                turnoDos.setBackground( getResources().getDrawable(R.drawable.contenedor_turno) );
                turnoTres.setBackground( getResources().getDrawable(R.drawable.contenedor_turno_seleccionado) );
                botonAceptar.setVisibility(View.VISIBLE);
            }
        });

        this.botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioLogueado.getUsuarioLogueado().setTurno(turno);
                Intent intent = new Intent(getApplicationContext(), Navegador.class);
                startActivity(intent);
            }
        });

        CargaCatalogos cargaCatalogos = new CargaCatalogos();
        cargaCatalogos.execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CargaCatalogos cargaCatalogos = new CargaCatalogos();
        cargaCatalogos.execute();
    }
}
