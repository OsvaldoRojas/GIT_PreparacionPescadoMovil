package com.grupo.pinsa.sip.models.oracle.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class SubInventario {
    @SerializedName("subinventory_code")
    @Expose
    private String subinventoryCode;
    @SerializedName("localizaciones")
    @Expose
    private ArrayList<LocalizacionSubInventario> localizaciones;

    /**
     * Getter & Setter
     */
    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public ArrayList<LocalizacionSubInventario> getLocalizaciones() {
        return localizaciones;
    }

    public void setLocalizaciones(ArrayList<LocalizacionSubInventario> localizaciones) {
        this.localizaciones = localizaciones;
    }
}
