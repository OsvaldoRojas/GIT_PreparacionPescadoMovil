package com.example.simulador_pescado;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simulador_pescado.Utilerias.Encriptacion;
import com.example.simulador_pescado.conexion.InicioSesion;
import com.example.simulador_pescado.vista.ErrorServicio;
import com.example.simulador_pescado.vista.Usuario;
import com.example.simulador_pescado.vista.UsuarioLogueado;

public class LoginActivity extends AppCompatActivity {

     private EditText textoUsuario;
     private EditText textoContrasena;
     private Button botonAcceder;
     private ProgressBar barraProgreso;
     private LoginActivity actividad;
     private AlertDialog ventanaEmergente;

    public LoginActivity getActividad() {
        return actividad;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciaComponentes();
    }

    private void iniciaComponentes(){
        this.actividad = this;
        this.barraProgreso = findViewById(R.id.barraProgreso);
        this.textoUsuario = findViewById(R.id.usuario);
        this.textoContrasena = findViewById(R.id.contrasena);
        this.botonAcceder = findViewById(R.id.acceder);

        this.botonAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barraProgreso.setVisibility(View.VISIBLE);
                textoUsuario.setEnabled(false);
                textoContrasena.setEnabled(false);
                botonAcceder.setEnabled(false);

                /*InicioSesion login = new InicioSesion(
                        textoUsuario.getText().toString(),//"usuarioerp"
                        Encriptacion.md5( textoContrasena.getText().toString() ),
                        "2",
                        getActividad()
                );
                login.execute();*/

                Intent intent = new Intent(getApplicationContext(), Navegador.class);
                startActivity(intent);
            }
        });
    }

    public void iniciaSesion(Usuario usuarioLogueado){
        this.barraProgreso.setVisibility(View.GONE);
        this.textoUsuario.setEnabled(true);
        this.textoContrasena.setEnabled(true);
        this.botonAcceder.setEnabled(true);
        UsuarioLogueado.getUsuarioLogueado( usuarioLogueado.getUsuario() );

        Intent intent = new Intent(getApplicationContext(), Navegador.class);
        startActivity(intent);
    }

    public void errorInicio(final ErrorServicio errorServidor){
        this.barraProgreso.setVisibility(View.GONE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View vistaAsignar = getLayoutInflater().inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaEmergente = builder.create();
        this.ventanaEmergente.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaEmergente.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText( parseaMensaje( errorServidor.getMensaje() ) );

                Button botonAceptar = ventanaEmergente.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textoUsuario.setEnabled(true);
                        textoContrasena.setEnabled(true);
                        botonAcceder.setEnabled(true);
                        ventanaEmergente.dismiss();
                    }
                });
            }
        });
        this.ventanaEmergente.show();
    }

    private String parseaMensaje(String mensaje){
        if( mensaje.contains("contraseÃ±a") ){
            mensaje = mensaje.replace("Ã±", "ñ");
        }
        return mensaje;
    }


}
