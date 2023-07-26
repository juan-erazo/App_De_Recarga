package com.example.appderecarga;

public class Recarga {

    String numero, empresa, monto;

    public Recarga(){
    }

    public Recarga(String numero, String empresa, String monto) {
        this.numero = numero;
        this.empresa = empresa;
        this.monto = monto;
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

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
