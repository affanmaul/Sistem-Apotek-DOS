package com.kelompoktiga.apotek.user;

import com.kelompoktiga.apotek.model.ItemKeranjang;
import com.kelompoktiga.apotek.model.Keranjang;
import com.kelompoktiga.apotek.model.Obat;

public class Pembeli extends User {
    private Keranjang keranjang = new Keranjang();

    /**
     *  Constructor kelas Pembeli
     */
    public Pembeli(String username, String userId, String nama, String nomorTelepon, String alamat) {
        super(username, userId, nama, nomorTelepon, alamat);
    }

    public void tambahObatKeKeranjang(Obat obat, int jumlahBarang) {
        this.keranjang.tambahObat(obat, jumlahBarang);
    }

    public void hapusObatDariKeranjang(int indexObat) {
        this.keranjang.hapusObat(indexObat);
    }

    public void hapusSeluruhIsiKeranjang() {
        this.keranjang.hapusSeluruhData();
    }

    public void lihatKeranjang() {
        keranjang.cetakDataKeranjang();
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }
}
