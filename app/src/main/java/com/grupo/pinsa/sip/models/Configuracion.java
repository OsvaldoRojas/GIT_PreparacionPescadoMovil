package com.grupo.pinsa.sip.models;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class Configuracion {
    private String direccionIP;

    public Configuracion() {
        this.direccionIP = "";
    }

    public String getDireccionIP() {
        return this.direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }
}
