package com.kelompoktiga.apotek.model;

public class Obat {
    private String idObat;
    private String nama;
    private float harga;
    private boolean obatKeras = false;

    public Obat(String idObat, String nama, float harga) {
        this.idObat = idObat;
        this.nama = nama;
        this.harga = harga;
    }

    public Obat(String idObat, String nama, float harga, boolean obatKeras) {
        this(idObat,nama, harga);
        this.obatKeras = obatKeras;

    }

    public void cetakObat() {
        System.out.println("Id obat : " + idObat);
        System.out.println("nama obat : " + nama);
        System.out.println("harga obat : Rp." + harga);
        if (obatKeras == true){
            System.out.println("Merupakan obat keras");
        }
        else {
            System.out.println("Bukan merupakan obat keras");
        }
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
