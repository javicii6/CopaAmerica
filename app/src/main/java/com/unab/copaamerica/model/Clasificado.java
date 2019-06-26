package com.unab.copaamerica.model;

public class Clasificado {
    private String bandera;
    private String nombre;
    private String probabilidad;

    public Clasificado(String bandera, String nombre, String probabilidad) {
        this.bandera = bandera;
        this.nombre = nombre;
        this.probabilidad = probabilidad;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
    }
}
