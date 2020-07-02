package com.grupo.pinsa.sip.views.simulador.descongelado;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorControlDescongeladoDetalle;
import com.grupo.pinsa.sip.views.simulador.adaptadores.AdaptadorDetalleSimple;
import com.grupo.pinsa.sip.views.simulador.contenedores.Contenedor_Descongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongeladoDetalle;
import com.grupo.pinsa.sip.views.simulador.modelo.DetalleSimple;
import com.grupo.pinsa.sip.views.simulador.modelo.PescadoProcesado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Detalle_General extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;
    private AlertDialog ventanaError;
    private ControlDescongeladoDetalle detalle;

    public Fragment_Detalle_General(){
    }

    public void setDetalle(ControlDescongeladoDetalle detalle) {
        this.detalle = detalle;
    }

    public ControlDescongeladoDetalle getDetalle() {
        return detalle;
    }

    public static Fragment_Detalle_General newInstance(Serializable param1) {
        Fragment_Detalle_General fragment = new Fragment_Detalle_General();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setDetalle( (ControlDescongeladoDetalle) getArguments().getSerializable(ARG_PARAM1) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.vista = inflater.inflate(R.layout.fragment_detalle_general, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        iniciaProcesando();

        Button botonCancelar = this.vista.findViewById(R.id.botonVolver);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Contenedor_Descongelado().newInstance(4);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        Button botonGraficar = this.vista.findViewById(R.id.botonGraficar);
        botonGraficar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Detalle_Grafica().newInstance( getDetalle() );
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        RecyclerView vistaDetalleGeneral = this.vista.findViewById(R.id.listaDetalleGeneral);
        vistaDetalleGeneral.setHasFixedSize(true);

        LinearLayoutManager layoutManagerDetalle = new LinearLayoutManager( getContext() );
        vistaDetalleGeneral.setLayoutManager(layoutManagerDetalle);

        AdaptadorControlDescongeladoDetalle adaptadorControlDescongeladoDetalle =
                new AdaptadorControlDescongeladoDetalle( getDetalle().getControlesDescongelado() );
        vistaDetalleGeneral.setAdapter(adaptadorControlDescongeladoDetalle);

        ViewGroup.LayoutParams paramsDetalle = vistaDetalleGeneral.getLayoutParams();
        paramsDetalle.height = 45*getDetalle().getControlesDescongelado().size();
        vistaDetalleGeneral.setLayoutParams(paramsDetalle);
        vistaDetalleGeneral.requestLayout();

        List<DetalleSimple> listaConsumoProceso = new ArrayList<>();
        DetalleSimple hipocloritoSodio = new DetalleSimple();
        hipocloritoSodio.setLlave("Hipoclorito de sodio");
        hipocloritoSodio.setValor( String.valueOf( getDetalle().getHipocloritoSodio() ) );
        listaConsumoProceso.add(hipocloritoSodio);

        DetalleSimple antiespumante = new DetalleSimple();
        antiespumante.setLlave("Antiespumante");
        antiespumante.setValor( String.valueOf( getDetalle().getAntiespumante() ) );
        listaConsumoProceso.add(antiespumante);

        DetalleSimple consumoTotalAgua = new DetalleSimple();
        consumoTotalAgua.setLlave("Consumo total de agua");
        consumoTotalAgua.setValor( String.valueOf( getDetalle().getConsumoTotalAgua() ) );
        listaConsumoProceso.add(consumoTotalAgua);

        DetalleSimple pescadoProcesado = new DetalleSimple();
        pescadoProcesado.setLlave("Pescado procesado");
        pescadoProcesado.setValor( String.valueOf( getDetalle().getPescadoProcesado() ) );
        listaConsumoProceso.add(pescadoProcesado);

        RecyclerView vistaConsumoProceso = this.vista.findViewById(R.id.listaConsumoProceso);
        vistaConsumoProceso.setHasFixedSize(true);

        LinearLayoutManager layoutManagerConsumo = new LinearLayoutManager( getContext() );
        vistaConsumoProceso.setLayoutManager(layoutManagerConsumo);

        AdaptadorDetalleSimple adaptadorConsumoProceso = new AdaptadorDetalleSimple(listaConsumoProceso);
        vistaConsumoProceso.setAdapter(adaptadorConsumoProceso);

        ViewGroup.LayoutParams paramsConsumo = vistaConsumoProceso.getLayoutParams();
        paramsConsumo.height = 45*4;
        vistaConsumoProceso.setLayoutParams(paramsConsumo);
        vistaConsumoProceso.requestLayout();

        List<DetalleSimple> listaLotes = new ArrayList<>();
        DetalleSimple loteHipoclorito = new DetalleSimple();
        loteHipoclorito.setLlave("Lote hipoclorito");
        loteHipoclorito.setValor( String.valueOf( getDetalle().getLoteHipoclorito() ) );
        listaLotes.add(loteHipoclorito);

        DetalleSimple loteAntiespumante = new DetalleSimple();
        loteAntiespumante.setLlave("Lote antiespumante");
        loteAntiespumante.setValor( String.valueOf( getDetalle().getLoteAntiespumante() ) );
        listaLotes.add(loteAntiespumante);

        RecyclerView vistaLotes = this.vista.findViewById(R.id.listaLotes);
        vistaLotes.setHasFixedSize(true);

        LinearLayoutManager layoutManagerLotes = new LinearLayoutManager( getContext() );
        vistaLotes.setLayoutManager(layoutManagerLotes);

        AdaptadorDetalleSimple adaptadorLotes = new AdaptadorDetalleSimple(listaLotes);
        vistaLotes.setAdapter(adaptadorLotes);

        ViewGroup.LayoutParams paramsLotes = vistaLotes.getLayoutParams();
        paramsLotes.height = 45*2;
        vistaLotes.setLayoutParams(paramsLotes);
        vistaLotes.requestLayout();

        List<DetalleSimple> listaPescadoProcesado = new ArrayList<>();
        for( PescadoProcesado registroPescado : getDetalle().getPescadosProcesados() ){
            DetalleSimple detalleSimple = new DetalleSimple();
            detalleSimple.setLlave( registroPescado.getTalla() );
            detalleSimple.setValor( String.valueOf( registroPescado.getToneladas() ) );
            listaPescadoProcesado.add(detalleSimple);
        }

        RecyclerView vistaPescadosProcesados = this.vista.findViewById(R.id.listaPescadoProcesado);
        vistaPescadosProcesados.setHasFixedSize(true);

        LinearLayoutManager layoutManagerPescados = new LinearLayoutManager( getContext() );
        vistaPescadosProcesados.setLayoutManager(layoutManagerPescados);

        AdaptadorDetalleSimple adaptadorPescados = new AdaptadorDetalleSimple(listaPescadoProcesado);
        vistaPescadosProcesados.setAdapter(adaptadorPescados);

        ViewGroup.LayoutParams paramsPescados = vistaPescadosProcesados.getLayoutParams();
        paramsPescados.height = 45*getDetalle().getPescadosProcesados().size();
        vistaPescadosProcesados.setLayoutParams(paramsPescados);
        vistaPescadosProcesados.requestLayout();

        terminaProcesando();
    }

    public void errorServicio(final String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater = getActivity().getLayoutInflater();

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
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.VISIBLE);
    }

    public void terminaProcesando(){
        ProgressBar barraProgreso = this.vista.findViewById(R.id.barraProgreso);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        barraProgreso.setVisibility(View.GONE);
    }
}
