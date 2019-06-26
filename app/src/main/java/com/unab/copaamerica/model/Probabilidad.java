package com.unab.copaamerica.model;

public class Probabilidad {

    String bandera;
    String nombre;
    float probabilidad;

    public Probabilidad(String bandera, String nombre, float probabilidad) {
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

    public float getProbabilidad() {
        return probabilidad;
    }
    public void setProbabilidad(float probabilidad) {
        this.probabilidad = probabilidad;
    }
}
