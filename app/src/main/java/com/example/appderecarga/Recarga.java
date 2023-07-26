package com.example.appderecarga;

public class Recarga {

    String nomPer, apePer, nroTarjeta, numero, empresa;
    int monto;

    public Recarga(){
    }

    public Recarga(String nomPer, String apePer, String nroTarjeta, String numero, String empresa, int monto) {
        this.nomPer = nomPer;
        this.apePer = apePer;
        this.nroTarjeta = nroTarjeta;
        this.numero = numero;
        this.empresa = empresa;
        this.monto = monto;
    }

    public String getNomPer() { return nomPer; }

    public void setNomPer(String nomPer) {
        this.nomPer = nomPer;
    }

    public String getApePer() { return apePer; }

    public void setApePer(String apePer) {
        this.apePer = apePer;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}
