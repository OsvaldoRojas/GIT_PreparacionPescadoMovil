package com.example.simulador_pescado;

import android.net.Uri;
import android.os.Bundle;

import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_OM;
import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_Plan;
import com.example.simulador_pescado.Atemperado.Fragment_Atemperado_TiempoMuerto;
import com.example.simulador_pescado.Contenedores.Contenedor;
import com.example.simulador_pescado.Contenedores.Contenedor_Atemperado;
import com.example.simulador_pescado.Contenedores.Contenedor_Descongelado;
import com.example.simulador_pescado.Descongelado.Fragment_Descongelado_OM;
import com.example.simulador_pescado.Descongelado.Fragment_Descongelado_Plan;
import com.example.simulador_pescado.Descongelado.Fragment_Descongelado_TiempoMuerto;
import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_OM;
import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_TiempoMuerto;
import com.example.simulador_pescado.Preselecion.Fragment_Preselecion_Tinas;
import com.example.simulador_pescado.vista.UsuarioLogueado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;

public class Navegador extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Fragment_Preselecion_OM.OnFragmentInteractionListener,
        Fragment_Preselecion_Tinas.OnFragmentInteractionListener, Fragment_Preselecion_TiempoMuerto.OnFragmentInteractionListener,
Contenedor.OnFragmentInteractionListener, Contenedor_Descongelado.OnFragmentInteractionListener, Fragment_Descongelado_TiempoMuerto.OnFragmentInteractionListener,
        Fragment_Descongelado_OM.OnFragmentInteractionListener,home.OnFragmentInteractionListener, Fragment_Atemperado_OM.OnFragmentInteractionListener,
        Fragment_Atemperado_TiempoMuerto.OnFragmentInteractionListener,Contenedor_Atemperado.OnFragmentInteractionListener, Fragment_Descongelado_Plan.OnFragmentInteractionListener,
        Fragment_Atemperado_Plan.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*UsuarioLogueado usuarioLogueado = UsuarioLogueado.getUsuarioLogueado(null);
        this.setTitle( usuarioLogueado.getNombre().concat(" ").concat( usuarioLogueado.getApellido_paterno() ) );*/
       
        Fragment fragment= new home();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navegador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean fragmentSelecionado= false;
        Fragment mifragment= null;

        if (id == R.id.nav_home) {
      mifragment = new home();
       fragmentSelecionado= true;

        } else if (id == R.id.nav_preselecion) {
            mifragment = new Contenedor();
            fragmentSelecionado= true;

        } else if (id == R.id.nav_desconglado) {
            mifragment = new Contenedor_Descongelado();
            fragmentSelecionado=true;

        } else if (id == R.id.nav_atemperado) {
            mifragment = new Contenedor_Atemperado();
            fragmentSelecionado=true;

        } else if (id == R.id.cerrar_sesion) {
            this.finish();

        }
        if (fragmentSelecionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,mifragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
