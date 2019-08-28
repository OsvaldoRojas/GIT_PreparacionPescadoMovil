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
}
