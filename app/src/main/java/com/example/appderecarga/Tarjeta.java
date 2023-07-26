package com.example.appderecarga;

public class Tarjeta {

    private String nroTarjeta, CVV, fecha;

    public Tarjeta(){
    }

    public Tarjeta(String nroTarjeta, String CVV, String fecha) {
        this.nroTarjeta = nroTarjeta;
        this.CVV = CVV;
        this.fecha = fecha;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
