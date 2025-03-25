package com.example.demo;

import java.util.UUID;
public class Motocicleta {
    private String id;
    private String placa;
    private String cilindraje;
    private String precioMotocicleta;
    private String colorMotocicleta;

    public Motocicleta() {
        this.id = UUID.randomUUID().toString();
    }

    public Motocicleta(String placa, String cilindraje, String precioMotocicleta, String colorMotocicleta) {
        this.id = UUID.randomUUID().toString();
        this.placa = placa;
        this.cilindraje = cilindraje;
        this.precioMotocicleta = precioMotocicleta;
        this.colorMotocicleta = colorMotocicleta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getPrecioMotocicleta() {
        return precioMotocicleta;
    }

    public void setPrecioMotocicleta(String precioMotocicleta) {
        this.precioMotocicleta = precioMotocicleta;
    }

    public String getColorMotocicleta() {
        return colorMotocicleta;
    }

    public void setColorMotocicleta(String colorMotocicleta) {
        this.colorMotocicleta = colorMotocicleta;
    }
}
