package com.grupo.pinsa.sip.views.simulador.modulos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorBasculaCatalogo;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorCarritoModuloInventario;
import com.grupo.pinsa.sip.views.simulador.conexion.APIServicios;
import com.grupo.pinsa.sip.views.simulador.modelo.Bascula;
import com.grupo.pinsa.sip.views.simulador.modelo.Carrito;
import com.grupo.pinsa.sip.views.simulador.modelo.Cocedor;
import com.grupo.pinsa.sip.views.simulador.utilerias.Utilerias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadCarritosInventario extends AppCompatActivity {

    private AdaptadorCarritoModuloInventario adaptador;
    private Cocedor cocedorSeleccionado;
    private List<Carrito> listaCarritos = new ArrayList<>();
    private List<Carrito> listaCarritosAgregados;
    private List<Bascula> basculas;

    public int totalCarritos = 0;
    public int capacidadTotal;

    private AlertDialog ventanaError;
    private AlertDialog ventanaEmergente;
    private ProgressBar barraProgreso;
    private SwipeRefreshLayout actualizar;
    private RecyclerView vistaLista;
    private Spinner seleccionaBascula;

    public Cocedor getCocedorSeleccionado() {
        return cocedorSeleccionado;
    }

    public void setCocedorSeleccionado(Cocedor cocedorSeleccionado) {
        this.cocedorSeleccionado = cocedorSeleccionado;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_entrada_inventario);
        iniciaComponentes();
    }

    @Override
    public void onBackPressed() {
    }

    private void iniciaComponentes(){
        this.barraProgreso = findViewById(R.id.barraProgreso);
        iniciaProcesando();

        setCocedorSeleccionado( (Cocedor) getIntent().getSerializableExtra("cocedor") );
        this.listaCarritosAgregados = (List<Carrito>) getIntent().getSerializableExtra("listaCarritosAgregados");

        this.capacidadTotal = getIntent().getIntExtra("capacidadTotal", 0);
        this.totalCarritos = getIntent().getIntExtra("totalCarritos", 0);
        TextView etiquetaTotalCarritos = findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf(this.capacidadTotal) ) );

        this.actualizar = findViewById(R.id.actualizar);
        this.actualizar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actualizar.setRefreshing(true);
                if( ( (Bascula) seleccionaBascula.getSelectedItem() ).getIdBascula() > 0 ){
                    getCarritos();
                }else{
                    actualizar.setRefreshing(false);
                }
            }
        });

        Button agregar = findViewById(R.id.botonAgregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciaProcesando();
                registra();
            }
        });

        Button volver = findViewById(R.id.botonVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("listaCarritosAgregados", (Serializable) listaCarritosAgregados);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        TextView cocedor = findViewById(R.id.cocedor);
        cocedor.setText( getCocedorSeleccionado().getDescripcion() );

        this.vistaLista = findViewById(R.id.listaCarritos);

        this.seleccionaBascula = findViewById(R.id.seleccionBascula);
        this.seleccionaBascula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( basculas.get(i).getIdBascula() > 0 ){
                    iniciaProcesando();
                    getCarritos();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getCatalogoBasculas();
    }

    private void getCatalogoBasculas(){
        Call<List<Bascula>> llamadaServicio = APIServicios.getConexionAPPWEB().getBasculas("coc");
        llamadaServicio.enqueue(new Callback<List<Bascula>>() {
            @Override
            public void onResponse(Call<List<Bascula>> call, Response<List<Bascula>> response) {
                if(response.code() == 200){
                    muestraBasculas( response.body() );
                }else{
                    terminaProcesando();
                    errorServicio("Error al obtener las básculas");
                }
            }

            @Override
            public void onFailure(Call<List<Bascula>> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    private void muestraBasculas(List<Bascula> basculas){
        this.basculas = basculas;
        Bascula basculaVacia = new Bascula();
        basculaVacia.setIdBascula(0);
        basculaVacia.setDescripcion("Seleccionar báscula");
        this.basculas.add(0, basculaVacia);
        AdaptadorBasculaCatalogo adaptadorBasculaCatalogo = new AdaptadorBasculaCatalogo(this, this.basculas);
        this.seleccionaBascula.setAdapter(adaptadorBasculaCatalogo);
        this.seleccionaBascula.setSelection(
                Utilerias.obtenerPosicionBascula(
                        this.seleccionaBascula,
                        getIntent().getStringExtra("bascula")
                )
        );
        terminaProcesando();
    }

    private void registra(){
        //agregar el cocedor del que viene cada carrito
        List<Carrito> carritosActualizados = new ArrayList<>();
        for(Carrito carrito : this.listaCarritos){
            if( carrito.isSeleccionado() ){
                carrito.setCocedor( String.valueOf( getCocedorSeleccionado().getId() ) );
                carrito.setIdBascula( ( (Bascula) this.seleccionaBascula.getSelectedItem() ).getIdBascula() );
                carritosActualizados.add(carrito);
            }
        }

        //al guardar quitar todos los carritos que vienen del  mismo cocedor de la lista de agregados
        List<Carrito> carritosRemover = new ArrayList<>();
        for(Carrito carritoAgregado : this.listaCarritosAgregados){
            if( carritoAgregado.getCocedor().equals( String.valueOf( getCocedorSeleccionado().getId() ) ) &&
                    carritoAgregado.getIdBascula() == ( (Bascula) this.seleccionaBascula.getSelectedItem() ).getIdBascula() ){
                carritosRemover.add(carritoAgregado);
            }
        }
        this.listaCarritosAgregados.removeAll(carritosRemover);

        //y meter los nuevos marcados
        terminaProcesando();
        this.listaCarritosAgregados.addAll(carritosActualizados);
        Intent intent = new Intent();
        intent.putExtra("listaCarritosAgregados", (Serializable) this.listaCarritosAgregados);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void getCarritos(){
        int idBascula = ( (Bascula) this.seleccionaBascula.getSelectedItem() ).getIdBascula();
        Call<List<Carrito>> llamadaServicio = APIServicios.getConexion()
                .getCarritosSinAsignarModulo(getCocedorSeleccionado().getId(), idBascula);
        llamadaServicio.enqueue(new Callback<List<Carrito>>() {
            @Override
            public void onResponse(Call<List<Carrito>> call, Response<List<Carrito>> response) {
                if(response.code() == 200){
                    muestraCarritos( response.body() );
                }else{
                    terminaProcesando();
                    errorServicio("Error al obtener los carritos sin asignar módulo");
                }
            }

            @Override
            public void onFailure(Call<List<Carrito>> call, Throwable t) {
                terminaProcesando();
                errorServicio("Error al conectar con el servidor");
            }
        });
    }

    private void muestraCarritos(List<Carrito> carritos){
        //tomar la lista de agregados y marcar los que ya estan marcados en la nueva lista
        for(Carrito carritoNuevo : carritos) {
            for (Carrito carritoAgregado : this.listaCarritosAgregados) {
                if ( carritoNuevo.getDescripcion().equalsIgnoreCase( carritoAgregado.getDescripcion() ) ) {
                    carritoNuevo.setSeleccionado(true);
                    carritoNuevo.setSeleccionadoSuma(true);
                    break;
                }
            }
        }

        //despues sustituit la nueva lista por la anterior en caso que coincidan los id
        for(Carrito carritoNuevo : carritos){
            for(Carrito carritoAnterior : this.listaCarritos){
                if( carritoNuevo.getDescripcion().equalsIgnoreCase( carritoAnterior.getDescripcion() ) ){
                    carritoNuevo.setSeleccionado( carritoAnterior.isSeleccionado() );
                    carritoNuevo.setSeleccionadoSuma( carritoAnterior.isSeleccionadoSuma() );
                    break;
                }
            }
        }
        this.listaCarritos = carritos;

        TextView sinResultado = findViewById(R.id.sinResultados);
        if( !this.listaCarritos.isEmpty() ) {
            sinResultado.setVisibility(View.GONE);
            this.vistaLista.setVisibility(View.VISIBLE);

            this.vistaLista.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            this.vistaLista.setLayoutManager(layoutManager);

            this.adaptador = new AdaptadorCarritoModuloInventario(this.listaCarritos, this);
            this.vistaLista.setAdapter(this.adaptador);

        }else{
            this.vistaLista.setVisibility(View.GONE);
            sinResultado.setVisibility(View.VISIBLE);
        }

        actualizaTotal();
        terminaProcesando();
    }

    public void actualizaTotal(){
        this.totalCarritos = getIntent().getIntExtra("totalCarritos", 0);
        for(Carrito carrito : this.listaCarritos){
            for(Carrito carritoAgregado : this.listaCarritosAgregados){
                if( carrito.getDescripcion().equalsIgnoreCase( carritoAgregado.getDescripcion() ) ){
                    this.totalCarritos = this.totalCarritos - 1;
                    break;
                }
            }
            if( carrito.isSeleccionadoSuma() ){
                this.totalCarritos = this.totalCarritos + 1;
            }
        }
        TextView etiquetaTotalCarritos = findViewById(R.id.totalCarritos);
        etiquetaTotalCarritos.setText( String.valueOf(this.totalCarritos).concat("/")
                .concat( String.valueOf(this.capacidadTotal) ) );
    }

    public void errorServicio(final String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View vistaAsignar = inflater.inflate(R.layout.dialog_mensaje_general, null);
        builder.setCancelable(false);
        builder.setView(vistaAsignar);

        this.ventanaError = builder.create();
        this.ventanaError.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView etiquetaMensaje = ventanaError.findViewById(R.id.etiquetaMensaje);
                etiquetaMensaje.setText(mensaje);

                Button botonAceptar = ventanaError.findViewById(R.id.boton1);
                botonAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ventanaError.dismiss();
                    }
                });
            }
        });
        this.ventanaError.show();
    }

    public void iniciaProcesando(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        this.barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if( this.actualizar.isRefreshing() ){
            this.actualizar.setRefreshing(false);
        }
        this.barraProgreso.setVisibility(View.GONE);
    }

}
