package com.grupo.pinsa.sip.views.simulador.utilerias;

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
        emparrillado,
        cocimiento,
        enfriamiento,
        temperatura,
        estatus,
        lavadoCarro,
        movimiento,
        control
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

    public enum ESTATUS_COCEDOR{
        disponible("Disponible",1),
        listoIniciarCocimiento("Listo para iniciar cocimiento", 2),
        procesoCocimiento("En proceso de cocimiento", 3),
        procesoCarga("En proceso de carga", 4),
        procesoDescarga("En proceso de descarga", 5),
        inhabilitado("Inhabilitado", 6);

        String descripcion;
        int id;

        ESTATUS_COCEDOR(String descripcion, int id){
            this.descripcion = descripcion;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    public static final String URL_SERVICIOS = "http://10.50.1.15:7080/api_simulador_movil/v1";
    public static final String URL_SERVICIOS_PINSA = "http://10.50.1.15:7080/sip";
}
