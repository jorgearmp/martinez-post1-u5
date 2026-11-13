package com.universidad.reservaslabs.model;

public class Laboratorio {
    private Long id;
    private String nombre;
    private int capacidad;
    private String ubicacion;

    public Laboratorio(Long id, String nombre, int capacidad, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public String getUbicacion() { return ubicacion; }
}
