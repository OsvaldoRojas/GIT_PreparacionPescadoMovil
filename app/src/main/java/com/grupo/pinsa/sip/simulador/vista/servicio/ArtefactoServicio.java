package com.grupo.pinsa.sip.simulador.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtefactoServicio {

    @SerializedName("idMaquinariaArtefacto")
    @Expose
    private long idMaquinariaArtefacto;

    public long getIdMaquinariaArtefacto() {
        return idMaquinariaArtefacto;
    }

    public void setIdMaquinariaArtefacto(long idMaquinariaArtefacto) {
        this.idMaquinariaArtefacto = idMaquinariaArtefacto;
    }
}
