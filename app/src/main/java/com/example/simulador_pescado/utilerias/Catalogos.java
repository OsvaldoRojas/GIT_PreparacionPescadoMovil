package com.example.simulador_pescado.utilerias;

import com.example.simulador_pescado.vista.Especialidad;
import com.example.simulador_pescado.vista.Etapa;
import com.example.simulador_pescado.vista.GrupoEspecie;
import com.example.simulador_pescado.vista.Maquinaria;
import com.example.simulador_pescado.vista.Refaccion;
import com.example.simulador_pescado.vista.Subtalla;
import com.example.simulador_pescado.vista.Talla;

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
        for( Etapa etapaLista : this.catalogoEtapa){
            if( etapaLista.getDescripcion().equalsIgnoreCase( etapa.toString() ) ){
                this.etapaActual = etapaLista.getIdEtapa();
                break;
            }
        }
    }

    public int getEtapaActual() {
        return etapaActual;
    }

    public void setCatalogoMaquinaria(List<Maquinaria> catalogoMaquinaria) {
        this.catalogoMaquinaria = new ArrayList<>();
        Maquinaria maquinaria = new Maquinaria();
        maquinaria.setDescripcion("Máquinaria");
        maquinaria.setIdMaquinaria(0);
        this.catalogoMaquinaria.add(maquinaria);
        this.catalogoMaquinaria.addAll(catalogoMaquinaria);
    }

    public List<Maquinaria> getCatalogoMaquinaria() {
        return catalogoMaquinaria;
    }

    public List<Subtalla> getCatalogoSubtalla() {
        return catalogoSubtalla;
    }

    public void setCatalogoSubtalla(List<Subtalla> catalogoSubtalla) {
        this.catalogoSubtalla = new ArrayList<>();
        Subtalla subtalla = new Subtalla();
        subtalla.setDescripcion("Subtalla");
        subtalla.setIdSubtalla(0);
        this.catalogoSubtalla.add(subtalla);
        this.catalogoSubtalla.addAll(catalogoSubtalla);
    }

    public List<Talla> getCatalogoTalla() {
        return catalogoTalla;
    }

    public void setCatalogoTalla(List<Talla> catalogoTalla) {
        this.catalogoTalla = new ArrayList<>();
        Talla talla = new Talla();
        talla.setDescripcion("Talla");
        talla.setIdTalla(0);
        this.catalogoTalla.add(talla);
        this.catalogoTalla.addAll(catalogoTalla);
    }

    public List<GrupoEspecie> getCatalogoGrupoEspecie() {
        return catalogoGrupoEspecie;
    }

    public void setCatalogoGrupoEspecie(List<GrupoEspecie> catalogoGrupoEspecie) {
        this.catalogoGrupoEspecie = new ArrayList<>();
        GrupoEspecie especie = new GrupoEspecie();
        especie.setDescripcion("Especie");
        especie.setIdEspecie(0);
        this.catalogoGrupoEspecie.add(especie);
        this.catalogoGrupoEspecie.addAll(catalogoGrupoEspecie);
    }

    public List<Especialidad> getCatalogoEspecialidad() {
        return catalogoEspecialidad;
    }

    public void setCatalogoEspecialidad(List<Especialidad> catalogoEspecialidad) {
        this.catalogoEspecialidad = new ArrayList<>();
        Especialidad especialidad = new Especialidad();
        especialidad.setDescripcion("Especialidad");
        especialidad.setIdEspecialidad(0);
        this.catalogoEspecialidad.add(especialidad);
        this.catalogoEspecialidad.addAll(catalogoEspecialidad);
    }

    public List<Refaccion> getCatalogoRefaccion() {
        return catalogoRefaccion;
    }

    public void setCatalogoRefaccion(List<Refaccion> catalogoRefaccion) {
        this.catalogoRefaccion = new ArrayList<>();
        Refaccion refaccion = new Refaccion();
        refaccion.setIdRefaccion(0);
        refaccion.setDescripcion("Refacción");
        this.catalogoRefaccion.add(refaccion);
        this.catalogoRefaccion.addAll(catalogoRefaccion);
    }
}
