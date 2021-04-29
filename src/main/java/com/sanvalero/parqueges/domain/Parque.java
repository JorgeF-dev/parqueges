package com.sanvalero.parqueges.domain;

/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class Parque {

    private int id_parque;
    private int id_ciudad;
    private int extension;
    private String nombre;

    public Parque(int id_parque, int id_ciudad, int extension, String nombre) {
        this.id_parque = id_parque;
        this.id_ciudad = id_ciudad;
        this.extension = extension;
        this.nombre = nombre;
    }

    public Parque(int id_ciudad, int extension, String nombre) {
        this.id_ciudad = id_ciudad;
        this.extension = extension;
        this.nombre = nombre;
    }

    public Parque() {
    }

    public int getId_parque() {
        return id_parque;
    }

    public void setId_parque(int id_parque) {
        this.id_parque = id_parque;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "Parques {" + " Nombre= " + nombre + '}';
    }
    
}
