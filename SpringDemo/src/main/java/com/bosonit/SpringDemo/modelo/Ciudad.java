package com.bosonit.SpringDemo.modelo;

public class Ciudad {
    String nombreCiudad;
    int habitantes;

    public Ciudad(String nombreCiudad, int habitantes) {
        this.nombreCiudad = nombreCiudad;
        this.habitantes = habitantes;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }
}
