package com.grupo.pinsa.sip.views.simulador.descongelado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.grupo.pinsa.sip.R;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongelado;
import com.grupo.pinsa.sip.views.simulador.modelo.ControlDescongeladoDetalle;

import java.io.Serializable;
import java.util.ArrayList;

public class Fragment_Detalle_Grafica extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private View vista;
    private ControlDescongeladoDetalle detalle;

    public Fragment_Detalle_Grafica(){}

    public ControlDescongeladoDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ControlDescongeladoDetalle detalle) {
        this.detalle = detalle;
    }

    public static Fragment_Detalle_Grafica newInstance(Serializable param1) {
        Fragment_Detalle_Grafica fragment = new Fragment_Detalle_Grafica();
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
        this.vista = inflater.inflate(R.layout.fragment_detalle_grafica, container, false);
        iniciaComponentes();
        return this.vista;
    }

    private void iniciaComponentes(){
        iniciaProcesando();

        Button botonCancelar = this.vista.findViewById(R.id.botonVolver);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_Detalle_General().newInstance( getDetalle() );
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

        TextView fecha = this.vista.findViewById(R.id.etiquetaFecha);
        fecha.setText( getDetalle().getFecha() );

        TextView clave = this.vista.findViewById(R.id.etiquetaClave);
        clave.setText( clave.getText().toString().concat( getDetalle().getClaveProceso() ) );

        LineChart grafica = this.vista.findViewById(R.id.grafica);
        Description etiqueta = new Description();
        etiqueta.setText("");
        grafica.setDescription(etiqueta);
        grafica.setNoDataText("No se encontraron datos");

        long primerValor = getTiempo( getDetalle().getControlesDescongelado().get(0).getHora() );
        ArrayList<Entry> entradasTemperatura = new ArrayList<>();
        ArrayList<Entry> entradasCloro = new ArrayList<>();
        for( ControlDescongelado controlDescongelado : getDetalle().getControlesDescongelado() ){
            entradasTemperatura.add(
                    new Entry(
                            getTiempo( controlDescongelado.getHora() ) - primerValor,
                            controlDescongelado.getTemperatura()
                    )
            );

            entradasCloro.add(
                    new Entry(
                            getTiempo( controlDescongelado.getHora() ) - primerValor,
                            controlDescongelado.getConcentrado()
                    )
            );
        }
        LineDataSet datosTemperatura = new LineDataSet(entradasTemperatura, "Temperatura");
        datosTemperatura.setColor( getResources().getColor(R.color.grafica1) );
        datosTemperatura.setCircleColor( getResources().getColor(R.color.grafica1) );
        LineDataSet datosCloro = new LineDataSet(entradasCloro, "Concentración Cloro");
        datosCloro.setColor( getResources().getColor(R.color.grafica2) );
        datosCloro.setCircleColor( getResources().getColor(R.color.grafica2) );

        LineData lineData = new LineData();
        lineData.addDataSet(datosTemperatura);
        lineData.addDataSet(datosCloro);

        grafica.setData(lineData);
        grafica.invalidate();

        IAxisValueFormatter xAxisFormatter = new HourAxisValueFormatter(primerValor);
        XAxis xAxis = grafica.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setLabelRotationAngle(270);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        terminaProcesando();
    }

    private long getTiempo(String cadenaHora){
        int hora = Integer.valueOf( cadenaHora.substring(0, 2) );
        int minutos = Integer.valueOf( cadenaHora.substring(3, 5) );
        return ( hora*60 ) + minutos;
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

    public class HourAxisValueFormatter implements IAxisValueFormatter {

        private long referenceTimestamp;

        public HourAxisValueFormatter(long referenceTimestamp) {
            this.referenceTimestamp = referenceTimestamp;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            long convertedTimestamp = (long) value;
            long originalTimestamp = referenceTimestamp + convertedTimestamp;
            return getHora(originalTimestamp);
        }

        private String getHora(long timestamp){
            try{
                long hours = timestamp / 60;
                long minutes = timestamp % 60;
                return ( hours < 10 ? "0" + hours : hours )
                        + ":" +
                        ( minutes < 10 ? "0" + minutes : minutes );
            }
            catch(Exception ex){
                return "xx";
            }
        }
    }
}
