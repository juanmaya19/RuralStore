package com.example.saborysteps;

public class Producto {
    private int id;
    private String nombre;
    private String cantidad;
    private double precio;
    private String ubicacion;
    private byte[] imagen;

    // Constructor completo
    public Producto(int id, String nombre, String cantidad, double precio, String ubicacion, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
    }

    // Constructor sin id (para inserciones)
    public Producto(String nombre, String cantidad, double precio, String ubicacion, byte[] imagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public byte[] getImagen() {
        return imagen;
    }
}
