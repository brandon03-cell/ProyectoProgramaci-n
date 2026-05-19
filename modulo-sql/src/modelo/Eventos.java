package modelo;

import java.util.Date;

public class Eventos {
    private int id;
    private String nombre;
    private String ubicacion;
    private String fecha;
    private Double precio;

    public Eventos(int id, String nombre, String ubicacion, String fecha, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Eventos(String nombre, String ubicacion, String fecha, Double precio) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Eventos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "El evento " + this.getNombre() + " se encuentra en " + this.getUbicacion() + ", se llevará acabo el " + this.getFecha() + " y tendrá un precio de " + this.getPrecio();
    }
}
