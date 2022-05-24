package com.kelompoktiga.apotek.model;

import java.util.ArrayList;

public class Keranjang {
    private String nomorTransaksi;
    private ArrayList<Obat> daftarObat = new ArrayList<>();
    private int totalBarang;
    private float totalHarga;

    public void tambahObat(Obat obat){
        daftarObat.add(obat);
    }

    public String getNomorTransaksi() {
        return nomorTransaksi;
    }

    public void setNomorTransaksi(String nomorTransaksi) {
        this.nomorTransaksi = nomorTransaksi;
    }

    public ArrayList<Obat> getDaftarObat() {
        return daftarObat;
    }

    public void setDaftarObat(ArrayList<Obat> daftarObat) {
        this.daftarObat = daftarObat;
    }

    public int getTotalBarang() {
        return totalBarang;
    }

    public void setTotalBarang(int totalBarang) {
        this.totalBarang = totalBarang;
    }

    public float getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
    }
}
