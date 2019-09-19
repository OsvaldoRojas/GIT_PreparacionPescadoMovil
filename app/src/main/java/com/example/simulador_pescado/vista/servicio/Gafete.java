package com.example.simulador_pescado.vista.servicio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gafete {

    @SerializedName("empleado")
    @Expose
    private EmpleadoGafete empleado;

    @SerializedName("resultado")
    @Expose
    private String resultado;

    public EmpleadoGafete getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoGafete empleado) {
        this.empleado = empleado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
