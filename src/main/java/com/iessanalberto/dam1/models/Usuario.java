package com.iessanalberto.dam1.models;
import java.sql.Timestamp;
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private Timestamp fechaCreacion;

    public Usuario(int id, String nombre, String email, Timestamp fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
