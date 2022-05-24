package com.kelompoktiga.apotek.user;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.model.Keranjang;

import java.util.ArrayList;

public class Pembeli extends User{
    private Keranjang keranjang = new Keranjang();

    public Pembeli(String userId, String nama, String nomorTelepon, String alamat) {
        super(userId, nama, nomorTelepon, alamat);
    }

    public void tambahkeKeranjang(Obat obat, int totalBarang) {
        this.keranjang.tambahObat(obat);
        this.keranjang.setTotalBarang(this.keranjang.getTotalBarang() + totalBarang);

        float totalHarga = totalBarang * obat.getHarga();
        this.keranjang.setTotalHarga(this.keranjang.getTotalHarga() + totalHarga);
        System.out.println("Berhasil menambahkan barang.\n");
    }

    public void lihatKeranjang() {
        System.out.println("\nMenampilkan isi keranjang:");
        System.out.print("Obat: ");
        for(Obat obat: keranjang.getDaftarObat()){
            System.out.print(obat.getNama() + ", ");
        }
        System.out.println("\nTotal Barang: " + keranjang.getTotalBarang());
        System.out.println("Total Harga: " + keranjang.getTotalHarga());
        System.out.println();
    }
}
