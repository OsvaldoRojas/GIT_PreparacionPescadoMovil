package com.example.simulador_pescado.Utilerias;

public class Constantes {

    public enum ESTADO{
        inicial,
        seleccionado,
        asignado
    }

    public enum ETAPA{
        preseleccion,
        atemperado,
        descongelado,
        eviscerado,
        cocimiento,
        enfriamiento
    }

    public static final String URL_SERVICIOS = "http://10.50.1.15:7080/api_simulador_movil/v1";
    public static final String URL_SERVICIOS_PINSA = "http://10.50.1.15:7080/sip";
}
