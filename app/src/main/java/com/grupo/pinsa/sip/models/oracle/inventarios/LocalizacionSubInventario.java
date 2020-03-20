package com.grupo.pinsa.sip.models.oracle.inventarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 27/10/2018
 */

public class LocalizacionSubInventario {
    @SerializedName("locator_id")
    @Expose
    private String locatorId;
    @SerializedName("ubication")
    @Expose
    private String ubication;

    /**
     * Constructor
     */
    public LocalizacionSubInventario() {
        locatorId = "";
        ubication = "";
    }

    /**
     * Getter & Setter
     */
    public String getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(String locatorId) {
        this.locatorId = locatorId;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }
}
