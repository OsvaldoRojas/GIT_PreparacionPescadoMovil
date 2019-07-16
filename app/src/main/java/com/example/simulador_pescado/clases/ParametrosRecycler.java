package com.example.simulador_pescado.clases;

public class ParametrosRecycler {

    private String Folio;
    private String Fecha;
    private String Arterfacto;
    private String Mecanico;

    public ParametrosRecycler(String folio, String fecha, String arterfacto, String mecanico) {
        Folio = folio;
        Fecha = fecha;
        Arterfacto = arterfacto;
        Mecanico = mecanico;
    }

    public ParametrosRecycler() {
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String folio) {
        Folio = folio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getArterfacto() {
        return Arterfacto;
    }

    public void setArterfacto(String arterfacto) {
        Arterfacto = arterfacto;
    }

    public String getMecanico() {
        return Mecanico;
    }

    public void setMecanico(String mecanico) {
        Mecanico = mecanico;
    }
}
