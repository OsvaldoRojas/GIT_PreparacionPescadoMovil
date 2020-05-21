package com.grupo.pinsa.sip.simulador.utilerias;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo.pinsa.sip.simulador.Fragment_ControlProceso;
import com.grupo.pinsa.sip.simulador.Fragment_MovimientoPersonal;
import com.grupo.pinsa.sip.simulador.adaptadores.AdaptadorCarritoCocida;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Atemperado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Cocimiento;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Descongelado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Emparrillado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Eviscerado;
import com.grupo.pinsa.sip.simulador.estatusCocedor.Fragment_Estatus;
import com.grupo.pinsa.sip.simulador.home;
import com.grupo.pinsa.sip.simulador.orden.Fragment_OrdenMantenimiento;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Lavado;
import com.grupo.pinsa.sip.simulador.contenedores.Contenedor_Modulos;
import com.grupo.pinsa.sip.simulador.temperatura.Fragment_Temperatura_Carrito;
import com.grupo.pinsa.sip.simulador.temperatura.Fragment_Temperatura_Tina;
import com.grupo.pinsa.sip.simulador.temperatura.HomeTemperatura;
import com.grupo.pinsa.sip.simulador.modelo.UsuarioLogueado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilerias {

    public static boolean setAlturaLista(ListView listView, int tamañoMaximo) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null){
            int numberOfItems = listAdapter.getCount();

            //Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int)px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            //Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            //Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            //Set list height.
            int totalAltura = totalItemsHeight + totalDividersHeight + totalPadding;
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            if(tamañoMaximo > 0){
                if(totalAltura > tamañoMaximo){
                    totalAltura = tamañoMaximo;
                }
            }
            params.height = totalAltura;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;
        }
        return false;
    }

    public static int obtenerPosicionItem(Spinner spinner, String descripcion) {
        int posicion = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(descripcion)) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    public static String fechaActual(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format( new Date() );
    }

    public static String fechaActual(String formato){
        SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
        return formatoFecha.format( new Date() );
    }

    public static String formatoFecha(String fechaTexto){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoFechaEntrada = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formatoFechaEntrada.parse(fechaTexto);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatoFecha.format(fecha);
    }

    public static Fragment navega(int pestaña){
        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() != Constantes.ROL.auxiliar.getId() ){
            return new Fragment_OrdenMantenimiento();
        }else{
            switch ( Catalogos.getInstancia().getEtapaActual() ){
                case 1:
                    return new Contenedor().newInstance(pestaña);
                case 2:
                    return new Contenedor_Atemperado().newInstance(pestaña);
                case 3:
                    return new Contenedor_Descongelado().newInstance(pestaña);
                case 4:
                    return new Contenedor_Eviscerado().newInstance(pestaña);
                case 8:
                    return new Contenedor_Emparrillado().newInstance(pestaña);
                case 5:
                    return new Contenedor_Cocimiento().newInstance(pestaña);
                case 6:
                    return new Contenedor_Modulos().newInstance(pestaña);
                case 7:
                    return new Contenedor_Lavado().newInstance(pestaña);
            }
        }
        return null;
    }

    public static Fragment navegaInicio(Constantes.ETAPA etapa){
        Catalogos.getInstancia().setEtapaActual(etapa);
        if( UsuarioLogueado.getUsuarioLogueado().getId_rol() != Constantes.ROL.auxiliar.getId() ){
            return new Fragment_OrdenMantenimiento();
        }else{
            switch (etapa){
                case preseleccion:
                    return new Contenedor();
                case atemperado:
                    return new Contenedor_Atemperado();
                case descongelado:
                    return new Contenedor_Descongelado();
                case eviscerado:
                    return new Contenedor_Eviscerado();
                case emparrillado:
                    return new Contenedor_Emparrillado();
                case cocimiento:
                    return new Contenedor_Cocimiento();
                case modulos:
                    return new Contenedor_Modulos();
                case temperatura:
                    return new HomeTemperatura();
                case estatus:
                    return new Fragment_Estatus();
                case lavado:
                    return new Contenedor_Lavado();
                case movimiento:
                    return new Fragment_MovimientoPersonal();
                case control:
                    return new Fragment_ControlProceso();
            }
        }
        return null;
    }

    public static Fragment navegaTemperatura(Constantes.ETAPA etapa){
        switch (etapa){
            case preseleccion:
            case atemperado:
            case eviscerado:
                return new Fragment_Temperatura_Tina().newInstance( Catalogos.getInstancia().getEtapaTemperatura(etapa) );
            case cocimiento:
                return new Fragment_Temperatura_Carrito().newInstance(1);
            case modulos:
                return new Fragment_Temperatura_Carrito().newInstance(2);
            case temperatura:
                return new home();
        }
        return null;
    }
}
