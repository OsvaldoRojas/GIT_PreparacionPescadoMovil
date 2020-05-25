package com.grupo.pinsa.sip.simulador;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.grupo.pinsa.sip.simulador.cocimiento.Fragment_Carga_Carritos;
import com.grupo.pinsa.sip.simulador.cocimiento.Fragment_Cocimiento_Plan;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Asigna_Tina;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Cocida;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Cocimiento;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Emparrillado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Eviscerado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Lavado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Modulos;
import com.grupo.pinsa.sip.simulador.descongelado.Fragment_Descongelado_DetalleEstiba;
import com.grupo.pinsa.sip.simulador.atemperado.Fragment_Atemperado_Plan;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Atemperado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Descongelado;
import com.grupo.pinsa.sip.simulador.descongelado.Fragment_Descongelado_Plan;
import com.grupo.pinsa.sip.simulador.descongelado.Fragment_Descongelado_SalidaTinas;
import com.grupo.pinsa.sip.simulador.emparrillado.Fragment_Emparrillado_Plan;
import com.grupo.pinsa.sip.simulador.estatusCocedor.Fragment_Cocida;
import com.grupo.pinsa.sip.simulador.estatusCocedor.Fragment_Estatus;
import com.grupo.pinsa.sip.simulador.eviscerado.Fragment_Eviscerado_Plan;
import com.grupo.pinsa.sip.simulador.lavadoCarros.Fragment_Lavado_Plan;
import com.grupo.pinsa.sip.simulador.modulos.Fragment_Detalle_Modulo;
import com.grupo.pinsa.sip.simulador.modulos.Fragment_Entrada_Manual;
import com.grupo.pinsa.sip.simulador.modulos.Fragment_Modulos_Plan;
import com.grupo.pinsa.sip.simulador.modulos.Fragment_Salida_Carritos;
import com.grupo.pinsa.sip.simulador.modulos.Fragment_Vista_Modulo;
import com.grupo.pinsa.sip.simulador.orden.Fragment_AsignaMecanico;
import com.grupo.pinsa.sip.simulador.orden.Fragment_CreaOrdenMantenimiento;
import com.grupo.pinsa.sip.simulador.orden.Fragment_DetalleOrden;
import com.grupo.pinsa.sip.simulador.orden.Fragment_OrdenMantenimiento;
import com.grupo.pinsa.sip.simulador.preselecion.Fragment_Asigna_Montacargas;
import com.grupo.pinsa.sip.simulador.preselecion.Fragment_Asigna_Operador;
import com.grupo.pinsa.sip.simulador.preselecion.Fragment_Asigna_Tina;
import com.grupo.pinsa.sip.simulador.preselecion.Fragment_Asigna_Tina_Cocida;
import com.grupo.pinsa.sip.simulador.preselecion.Fragment_Preselecion_Tinas;
import com.grupo.pinsa.sip.simulador.temperatura.Fragment_Temperatura_Carrito;
import com.grupo.pinsa.sip.simulador.temperatura.Fragment_Temperatura_Tina;
import com.grupo.pinsa.sip.simulador.temperatura.HomeTemperatura;
import com.grupo.pinsa.sip.simulador.utilerias.Constantes;
import com.grupo.pinsa.sip.simulador.utilerias.Utilerias;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;
import com.google.android.material.navigation.NavigationView;

public class Navegador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Fragment_OrdenMantenimiento.OnFragmentInteractionListener,
        Fragment_Preselecion_Tinas.OnFragmentInteractionListener, Contenedor.OnFragmentInteractionListener, Contenedor_Descongelado.OnFragmentInteractionListener,
        home.OnFragmentInteractionListener, Contenedor_Atemperado.OnFragmentInteractionListener, Fragment_Descongelado_Plan.OnFragmentInteractionListener,
        Fragment_Atemperado_Plan.OnFragmentInteractionListener, Fragment_Asigna_Tina.OnFragmentInteractionListener, Fragment_Asigna_Operador.OnFragmentInteractionListener,
        Fragment_Asigna_Montacargas.OnFragmentInteractionListener, Fragment_CreaOrdenMantenimiento.OnFragmentInteractionListener, Fragment_AsignaMecanico.OnFragmentInteractionListener,
        Fragment_DetalleOrden.OnFragmentInteractionListener, Fragment_Descongelado_DetalleEstiba.OnFragmentInteractionListener, Contenedor_Eviscerado.OnFragmentInteractionListener,
        Fragment_Eviscerado_Plan.OnFragmentInteractionListener, Contenedor_Emparrillado.OnFragmentInteractionListener, Fragment_Emparrillado_Plan.OnFragmentInteractionListener,
        Fragment_Descongelado_SalidaTinas.OnFragmentInteractionListener, com.grupo.pinsa.sip.simulador.eviscerado.Fragment_Asigna_Operador.OnFragmentInteractionListener,
        com.grupo.pinsa.sip.simulador.emparrillado.Fragment_Asigna_Operador.OnFragmentInteractionListener, Fragment_MovimientoPersonal.OnFragmentInteractionListener,
        Fragment_ControlProceso.OnFragmentInteractionListener, Fragment_Asigna_Tina_Cocida.OnFragmentInteractionListener, Fragment_Cocimiento_Plan.OnFragmentInteractionListener,
        Contenedor_Cocimiento.OnFragmentInteractionListener, com.grupo.pinsa.sip.simulador.cocimiento.Fragment_Asigna_Operador.OnFragmentInteractionListener,
        Fragment_Carga_Carritos.OnFragmentInteractionListener, Contenedor_Asigna_Tina.OnFragmentInteractionListener, Fragment_Lavado_Plan.OnFragmentInteractionListener,
        com.grupo.pinsa.sip.simulador.lavadoCarros.Fragment_Asigna_Operador.OnFragmentInteractionListener, Contenedor_Lavado.OnFragmentInteractionListener,
        Fragment_Estatus.OnFragmentInteractionListener, Contenedor_Cocida.OnFragmentInteractionListener, Fragment_Cocida.OnFragmentInteractionListener,
        HomeTemperatura.OnFragmentInteractionListener, Fragment_Temperatura_Tina.OnFragmentInteractionListener, Fragment_Temperatura_Carrito.OnFragmentInteractionListener,
        Contenedor_Modulos.OnFragmentInteractionListener, Fragment_Modulos_Plan.OnFragmentInteractionListener, com.grupo.pinsa.sip.simulador.modulos.Fragment_Asigna_Operador.OnFragmentInteractionListener,
        Fragment_Vista_Modulo.OnFragmentInteractionListener, Fragment_Detalle_Modulo.OnFragmentInteractionListener, Fragment_Entrada_Manual.OnFragmentInteractionListener,
        Fragment_Salida_Carritos.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UsuarioLogueado usuarioLogueado = UsuarioLogueado.getUsuarioLogueado();
        this.setTitle( usuarioLogueado.getNombre().concat(" ").concat( usuarioLogueado.getApellido_paterno() ) );

        Fragment fragment= new home();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        permisos(navigationView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void permisos(NavigationView navigationView){
        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.supervisor.getId()
                || UsuarioLogueado.getUsuarioLogueado().getId_rol() == Constantes.ROL.mecanico.getId() ){
            navigationView.getMenu().getItem(6).setVisible(false);
            navigationView.getMenu().getItem(7).setVisible(false);
        }else{
            if( UsuarioLogueado.getUsuarioLogueado().getId_rol() != Constantes.ROL.auxiliar.getId() ){
                navigationView.getMenu().getItem(1).setVisible(false);
                navigationView.getMenu().getItem(2).setVisible(false);
                navigationView.getMenu().getItem(3).setVisible(false);
                navigationView.getMenu().getItem(4).setVisible(false);
                navigationView.getMenu().getItem(5).setVisible(false);
                navigationView.getMenu().getItem(6).setVisible(false);
                navigationView.getMenu().getItem(7).setVisible(false);
            }
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean fragmentSelecionado = false;
        Fragment mifragment= null;

        if (id == R.id.nav_home) {
            mifragment = new home();
            fragmentSelecionado = true;

        } else if (id == R.id.nav_preselecion) {
            mifragment = Utilerias.navegaInicio(Constantes.ETAPA.preseleccion);
            fragmentSelecionado = true;

        } else if (id == R.id.nav_desconglado) {
            mifragment = Utilerias.navegaInicio(Constantes.ETAPA.descongelado);
            fragmentSelecionado = true;

        } else if (id == R.id.nav_atemperado) {
            mifragment = Utilerias.navegaInicio(Constantes.ETAPA.atemperado);
            fragmentSelecionado = true;

        } else if (id == R.id.nav_control) {
            mifragment = Utilerias.navegaInicio(Constantes.ETAPA.control);
            fragmentSelecionado = true;

        } else if (id == R.id.nav_emparrillado) {
            mifragment = Utilerias.navegaInicio(Constantes.ETAPA.emparrillado);
            fragmentSelecionado = true;

        } else if (id == R.id.nav_eviscerado) {
            mifragment = Utilerias.navegaInicio(Constantes.ETAPA.eviscerado);
            fragmentSelecionado = true;

        }  else if (id == R.id.nav_movimiento) {
            mifragment = Utilerias.navegaInicio(Constantes.ETAPA.movimiento);
            fragmentSelecionado = true;

        }
        else if (id == R.id.cerrar_sesion) {
            this.finish();

        }

        if (fragmentSelecionado){
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
