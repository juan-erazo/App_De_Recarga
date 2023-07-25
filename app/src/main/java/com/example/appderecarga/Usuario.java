package com.example.appderecarga;

public class Usuario {

    private String nomb, ape, usu, cont;

    public Usuario() {
    }

    public Usuario(String nomb, String ape, String usu, String cont) {
        this.nomb = nomb;
        this.ape = ape;
        this.usu = usu;
        this.cont = cont;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
