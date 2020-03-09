package com.ucam.bean;

public class PropertiesGeoJson {
    public String nombre;
    public String nombreCompleto;
    public String tipo;
    public String pais;
    public String fechaTweet;
    
    public PropertiesGeoJson(){
        nombre = "";
        nombreCompleto = "";
        tipo = "";
        pais = "";
        fechaTweet = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
}
