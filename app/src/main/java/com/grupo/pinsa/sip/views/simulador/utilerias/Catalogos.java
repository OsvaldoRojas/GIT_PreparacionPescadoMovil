package com.grupo.pinsa.sip.views.simulador.utilerias;

import com.grupo.pinsa.sip.views.simulador.modelo.Especialidad;
import com.grupo.pinsa.sip.views.simulador.modelo.Etapa;
import com.grupo.pinsa.sip.views.simulador.modelo.GrupoEspecie;
import com.grupo.pinsa.sip.views.simulador.modelo.Maquinaria;
import com.grupo.pinsa.sip.views.simulador.modelo.Refaccion;
import com.grupo.pinsa.sip.views.simulador.modelo.Subtalla;
import com.grupo.pinsa.sip.views.simulador.modelo.Talla;

import java.util.ArrayList;
import java.util.List;

public class Catalogos {

    private static Catalogos INSTANCIA = null;
    private List<Etapa> catalogoEtapa = new ArrayList<>();
    private List<Maquinaria> catalogoMaquinaria = new ArrayList<>();
    private List<Subtalla> catalogoSubtalla = new ArrayList<>();
    private List<Talla> catalogoTalla = new ArrayList<>();
    private List<GrupoEspecie> catalogoGrupoEspecie = new ArrayList<>();
    private List<Especialidad> catalogoEspecialidad = new ArrayList<>();
    private List<Refaccion> catalogoRefaccion = new ArrayList<>();
    private int etapaActual;

    public static Catalogos getInstancia(){
        if( INSTANCIA == null ){
            INSTANCIA = new Catalogos();
        }
        return INSTANCIA;
    }

    public void setCatalogoEtapa(List<Etapa> catalogoEtapa) {
        this.catalogoEtapa = catalogoEtapa;
    }

    public void setEtapaActual(Constantes.ETAPA etapa) {
        String etapaNombre = etapa.toString();
        if(etapaNombre.equalsIgnoreCase("lavadoCarro")){
            etapaNombre = "lavado carro";
        }
        for( Etapa etapaLista : this.catalogoEtapa){
            if( etapaLista.getDescripcion().equalsIgnoreCase(etapaNombre) ){
                this.etapaActual = etapaLista.getIdEtapa();
                break;
            }
        }
    }

    public int getEtapaActual() {
        return etapaActual;
    }

    public int getEtapaTemperatura(Constantes.ETAPA etapa){
        for( Etapa etapaLista : this.catalogoEtapa){
            if( etapaLista.getDescripcion().equalsIgnoreCase( etapa.toString() ) ){
                return etapaLista.getIdEtapa();
            }
        }
        return 0;
    }

    public void setCatalogoMaquinaria(List<Maquinaria> catalogoMaquinaria) {
        this.catalogoMaquinaria = catalogoMaquinaria;
    }

    public List<Maquinaria> getCatalogoMaquinaria() {
        Maquinaria maquinaria = new Maquinaria();
        maquinaria.setDescripcion("Máquinaria");
        maquinaria.setIdMaquinaria(0);
        this.catalogoMaquinaria.add(0, maquinaria);
        return this.catalogoMaquinaria;
    }

    public List<Subtalla> getCatalogoSubtalla() {
        Subtalla subtalla = new Subtalla();
        subtalla.setDescripcion("Subtalla");
        subtalla.setIdSubtalla(0);
        this.catalogoSubtalla.add(0, subtalla);
        return this.catalogoSubtalla;
    }

    public void setCatalogoSubtalla(List<Subtalla> catalogoSubtalla) {
        this.catalogoSubtalla = catalogoSubtalla;
    }

    public List<Talla> getCatalogoTalla() {
        Talla talla = new Talla();
        talla.setDescripcion("Talla");
        talla.setIdTalla(0);
        this.catalogoTalla.add(0, talla);
        return this.catalogoTalla;
    }

    public void setCatalogoTalla(List<Talla> catalogoTalla) {
        this.catalogoTalla = catalogoTalla;
    }

    public List<GrupoEspecie> getCatalogoGrupoEspecie() {
        if(this.catalogoGrupoEspecie.isEmpty() || this.catalogoGrupoEspecie.get(0).getIdEspecie() > 0){
            GrupoEspecie especie = new GrupoEspecie();
            especie.setDescripcion("Especie");
            especie.setIdEspecie(0);
            this.catalogoGrupoEspecie.add(0, especie);

        }
        return this.catalogoGrupoEspecie;
    }

    public void setCatalogoGrupoEspecie(List<GrupoEspecie> catalogoGrupoEspecie) {
        this.catalogoGrupoEspecie = catalogoGrupoEspecie;
    }

    public List<Especialidad> getCatalogoEspecialidad() {
        if(this.catalogoEspecialidad.isEmpty() || this.catalogoEspecialidad.get(0).getIdEspecialidad() > 0){
            Especialidad especialidad = new Especialidad();
            especialidad.setDescripcion("Especialidad");
            especialidad.setIdEspecialidad(0);
            this.catalogoEspecialidad.add(0, especialidad);
        }
        return this.catalogoEspecialidad;
    }

    public void setCatalogoEspecialidad(List<Especialidad> catalogoEspecialidad) {
        this.catalogoEspecialidad = catalogoEspecialidad;
    }

    public List<Refaccion> getCatalogoRefaccion() {
        Refaccion refaccion = new Refaccion();
        refaccion.setIdRefaccion(0);
        refaccion.setDescripcion("Refacción");
        this.catalogoRefaccion.add(0, refaccion);
        return this.catalogoRefaccion;
    }

    public void setCatalogoRefaccion(List<Refaccion> catalogoRefaccion) {
        this.catalogoRefaccion = catalogoRefaccion;
    }
}
