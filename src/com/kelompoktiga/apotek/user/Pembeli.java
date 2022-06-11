package com.kelompoktiga.apotek.user;

import com.kelompoktiga.apotek.model.ItemKeranjang;
import com.kelompoktiga.apotek.model.Keranjang;
import com.kelompoktiga.apotek.model.Obat;

public class Pembeli extends User {
    private Keranjang keranjang = new Keranjang();
    private Rekening rekening;

    /**
     *  Constructor kelas Pembeli
     */
    public Pembeli(String username, String userId, String nama, String nomorTelepon, String alamat, Rekening rekening) {
        super(username, userId, nama, nomorTelepon, alamat);
        this.rekening = rekening;
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

    @Override
    public void cetakInfo() {
        System.out.println("Mencetak data Pembeli ");
        super.cetakInfo();
    }

    public void cetakDetailInfoPembeli(){
        System.out.println();
        System.out.println("====== INFO AKUN ======");
        cetakInfo();
        System.out.println("====== KERANJANG ======");
        lihatKeranjang();
    }

    public Rekening getRekening() {
        return rekening;
    }
}
