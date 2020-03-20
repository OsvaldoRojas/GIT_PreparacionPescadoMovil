package com.grupo.pinsa.sip.models.seguridad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan J. Palomera on 27/11/2018
 */

public class ResponsabilidadOracle {
    @SerializedName("id_modulo_responsabilidad_oracle")
    @Expose
    private Integer idModuloResponsabilidadOracle;
    @SerializedName("id_empresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("id_modulo")
    @Expose
    private Integer idModulo;
    @SerializedName("id_responsabilidad_oracle")
    @Expose
    private Integer idResponsabilidadOracle;
    @SerializedName("id_aplicacion_oracle")
    @Expose
    private Integer idAplicacionOracle;
    @SerializedName("id_usuario_oracle")
    @Expose
    private Integer idUsuarioOracle;

    public ResponsabilidadOracle() {
        idModuloResponsabilidadOracle = 0;
        idEmpresa = 0;
        idModulo = 0;
        idResponsabilidadOracle = 0;
        idAplicacionOracle = 0;
        idUsuarioOracle = 0;
    }

    public Integer getIdModuloResponsabilidadOracle() {
        return idModuloResponsabilidadOracle;
    }

    public void setIdModuloResponsabilidadOracle(Integer idModuloResponsabilidadOracle) {
        this.idModuloResponsabilidadOracle = idModuloResponsabilidadOracle;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdResponsabilidadOracle() {
        return idResponsabilidadOracle;
    }

    public void setIdResponsabilidadOracle(Integer idResponsabilidadOracle) {
        this.idResponsabilidadOracle = idResponsabilidadOracle;
    }

    public Integer getIdAplicacionOracle() {
        return idAplicacionOracle;
    }

    public void setIdAplicacionOracle(Integer idAplicacionOracle) {
        this.idAplicacionOracle = idAplicacionOracle;
    }

    public Integer getIdUsuarioOracle() {
        return idUsuarioOracle;
    }

    public void setIdUsuarioOracle(Integer idUsuarioOracle) {
        this.idUsuarioOracle = idUsuarioOracle;
    }
}
