package com.example.simulador_pescado.utilerias;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.simulador_pescado.Fragment_ControlProceso;
import com.example.simulador_pescado.Fragment_MovimientoPersonal;
import com.example.simulador_pescado.atemperado.Fragment_Atemperado_OM;
import com.example.simulador_pescado.conexion.APIServicios;
import com.example.simulador_pescado.contenedores.Contenedor;
import com.example.simulador_pescado.contenedores.Contenedor_Atemperado;
import com.example.simulador_pescado.contenedores.Contenedor_Descongelado;
import com.example.simulador_pescado.contenedores.Contenedor_Emparrillado;
import com.example.simulador_pescado.contenedores.Contenedor_Eviscerado;
import com.example.simulador_pescado.descongelado.Fragment_Descongelado_OM;
import com.example.simulador_pescado.emparrillado.Fragment_Emparrillado_OM;
import com.example.simulador_pescado.eviscerado.Fragment_Eviscerado_OM;
import com.example.simulador_pescado.preselecion.Fragment_Preselecion_OM;
import com.example.simulador_pescado.vista.Maquinaria;
import com.example.simulador_pescado.vista.UsuarioLogueado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Fragment fragment = null;
        switch ( Catalogos.getInstancia().getEtapaActual() ){
            case 1:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor().newInstance(pestaña);
                }else{
                    fragment = new Fragment_Preselecion_OM();
                }
                break;
            case 2:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Atemperado().newInstance(pestaña);
                }else{
                    fragment = new Fragment_Atemperado_OM();
                }
                break;
            case 3:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Descongelado().newInstance(pestaña);
                }else{
                    fragment = new Fragment_Descongelado_OM();
                }
                break;
            case 4:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Eviscerado().newInstance(pestaña);
                }else{
                    fragment = new Fragment_Eviscerado_OM();
                }
                break;
            case 5:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Emparrillado().newInstance(pestaña);
                }else{
                    fragment = new Fragment_Emparrillado_OM();
                }
                break;
        }
        return fragment;
    }

    public static Fragment navegaInicio(Constantes.ETAPA etapa){
        Catalogos.getInstancia().setEtapaActual(etapa);


        Fragment fragment = null;
        switch (etapa){
            case preseleccion:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor();
                }else{
                    fragment = new Fragment_Preselecion_OM();
                }
                break;
            case atemperado:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Atemperado();
                }else{
                    fragment = new Fragment_Atemperado_OM();
                }
                break;
            case descongelado:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Descongelado();
                }else{
                    fragment = new Fragment_Descongelado_OM();
                }
                break;
            case eviscerado:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Eviscerado();
                }else{
                    fragment = new Fragment_Eviscerado_OM();
                }
                break;
            case emparrillado:
                if( UsuarioLogueado.getUsuarioLogueado(null).getId_rol() == Constantes.ROL.auxiliar.getId() ){
                    fragment = new Contenedor_Emparrillado();
                }else{
                    fragment = new Fragment_Emparrillado_OM();
                }
                break;
            case movimiento:
                fragment = new Fragment_MovimientoPersonal();
                break;
            case control:
                fragment = new Fragment_ControlProceso();
                break;
        }

        return fragment;
    }

    public static String cambiaAcentos(String mensaje){
        String resultado = "";
        for ( char c : mensaje.toCharArray() ){
            String valor = String.valueOf(c);
            switch (valor){
                case "á":
                    resultado = resultado.concat("\\u00e1");
                    break;
                case "é":
                    resultado = resultado.concat("\\u00e9");
                    break;
                case "í":
                    resultado = resultado.concat("\\u00ed");
                    break;
                case "ó":
                    resultado = resultado.concat("\\u00f3");
                    break;
                case "ú":
                    resultado = resultado.concat("\\u00fa");
                    break;
                case "Á":
                    resultado = resultado.concat("\\u00c1");
                    break;
                case "É":
                    resultado = resultado.concat("\\u00c9");
                    break;
                case "Í":
                    resultado = resultado.concat("\\u00cd");
                    break;
                case "Ó":
                    resultado = resultado.concat("\\u00d3");
                    break;
                case "Ú":
                    resultado = resultado.concat("\\u00da");
                    break;
                case "ñ":
                    resultado = resultado.concat("\\u00f1");
                    break;
                case "Ñ":
                    resultado = resultado.concat("\\u00d1");
                    break;
                    default:
                        resultado = resultado.concat(valor);

            }
        }
        return resultado;
    }
}
