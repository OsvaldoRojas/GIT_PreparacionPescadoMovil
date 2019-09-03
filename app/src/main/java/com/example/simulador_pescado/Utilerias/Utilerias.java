package com.example.simulador_pescado.Utilerias;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

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

    public static String fechaActual(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
         return formatoFecha.format( new Date() );
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
        return mensaje;
    }
}
