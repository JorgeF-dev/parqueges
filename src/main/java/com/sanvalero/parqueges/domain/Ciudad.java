package com.sanvalero.parqueges.domain;
/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class Ciudad {
    private int id_ciudad;
    private String nombre;
    private String ccaa;

    public Ciudad(int id_ciudad, String nombre, String ccaa) {
        this.id_ciudad = id_ciudad;
        this.nombre = nombre;
        this.ccaa = ccaa;
    }

    public Ciudad() {
    }
    

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCcaa() {
        return ccaa;
    }

    public void setCcaa(String ccaa) {
        this.ccaa = ccaa;
    }
    
    

}
