package com.kelompoktiga.apotek.model;

public class ItemKeranjang {
    private Obat obat;
    private int jumlahObat;

    public ItemKeranjang(Obat obat, int jumlahObat) {
        this.obat = obat;
        this.jumlahObat = jumlahObat;
    }

    public Obat getObat() {
        return obat;
    }

    public int getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(int jumlahObat) {
        this.jumlahObat = jumlahObat;
    }
}
