package com.kelompoktiga.apotek.model;

public class Obat {
    private String idObat;
    private String nama;
    private float harga;

    public Obat(String idObat, String nama, float harga) {
        this.idObat = idObat;
        this.nama = nama;
        this.harga = harga;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }
}
