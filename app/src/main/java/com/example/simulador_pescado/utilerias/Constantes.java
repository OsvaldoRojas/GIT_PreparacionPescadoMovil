package com.example.simulador_pescado.utilerias;

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

    public enum ROL{
        auxiliar(1),
        mecanico(2),
        supervisor(3);

        int id;

        ROL(int id){
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public static final String URL_SERVICIOS = "http://10.50.1.15:7080/api_simulador_movil/v1";
    public static final String URL_SERVICIOS_PINSA = "http://10.50.1.15:7080/sip";
}
