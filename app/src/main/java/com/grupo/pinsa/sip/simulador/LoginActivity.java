package com.grupo.pinsa.sip.simulador;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.grupo.pinsa.sip.simulador.modelo.ErrorServicio;
import com.grupo.pinsa.sip.simulador.modelo.Usuario;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;

public class LoginActivity extends AppCompatActivity {

     private EditText textoUsuario;
     private EditText textoContrasena;
     private Button botonAcceder;
     private ProgressBar barraProgreso;
     private LoginActivity actividad;
     private AlertDialog ventanaEmergente;
     private Switch switchTurno;

     private int turno = 1;

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
        this.switchTurno = findViewById(R.id.switchTurno);
        this.switchTurno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    turno = 2;
                }else{
                    turno = 1;
                }
            }
        });

        this.botonAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barraProgreso.setVisibility(View.VISIBLE);
                textoUsuario.setEnabled(false);
                textoContrasena.setEnabled(false);
                botonAcceder.setEnabled(false);
                switchTurno.setEnabled(false);

                /*InicioSesion login = new InicioSesion(
                        textoUsuario.getText().toString(),//"usuarioerp"
                        Encriptacion.md5( textoContrasena.getText().toString() ),
                        "2",
                        getActividad()
                );
                login.execute();*/

                Usuario u = new Usuario();
                UsuarioLogueado ul = new UsuarioLogueado();
                ul.setNombre("Prueba");
                ul.setApellido_paterno("Nombre");
                u.setUsuario(ul);
                iniciaSesion(u);
            }
        });
    }

    public void iniciaSesion(Usuario usuarioLogueado){
        this.barraProgreso.setVisibility(View.GONE);
        this.textoUsuario.setEnabled(true);
        this.textoContrasena.setEnabled(true);
        this.botonAcceder.setEnabled(true);
        this.switchTurno.setEnabled(true);
        //UsuarioLogueado.getUsuarioLogueado( usuarioLogueado.getUsuario() );
        UsuarioLogueado.getUsuarioLogueado().setTurno(this.turno);

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
                        switchTurno.setEnabled(true);
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
