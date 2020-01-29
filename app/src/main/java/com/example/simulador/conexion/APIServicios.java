package com.example.simulador.conexion;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServicios {

    private static final String URL_SERVICIOS = "http://10.50.1.15:7080/api_simulador_movil/v1/";
    private static final String URL_SERVICIOS_PINSA = "http://10.50.1.15:7080/sip/";

    public static ServicioRest getConexion(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVICIOS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ServicioRest.class);
    }

    public static ServicioRest getConexionPINSA(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVICIOS_PINSA)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ServicioRest.class);
    }
}
