package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Pembeli;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemApotek {
    private ArrayList<Obat> daftarObat = new ArrayList<Obat>();
    private Pembeli pembeli;

    private Scanner input = new Scanner(System.in);
    private String pilihan;
    private boolean aktif ;

    public SistemApotek(ArrayList<Obat> daftarObat, Pembeli pembeli) {
        this.daftarObat = daftarObat;
        this.pembeli = pembeli;
        System.out.println("Selamat Datang di Apotek Dos.");
    }

    public void mulai() {
        aktif = true;
        while (aktif) {
            System.out.println("Anda masuk sebagai Pembeli.");
            System.out.println("1. Tambah Obat Ke Keranjang");
            System.out.println("2. Lihat isi Keranjang");
            System.out.println("3. Exit");
            System.out.print("Pilih Menu [1-3]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> pilihObat();
                case "2" -> lihatKeranjang();
                case "3" -> {
                    aktif = false;
                    System.out.println("Program Selesai.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    private void lihatKeranjang() {
        pembeli.lihatKeranjang();
    }

    private void pilihObat() {
        System.out.println("\nMenampilkan Daftar Obat:");
        for (Obat itemObat : daftarObat) {
            System.out.println(itemObat.getIdObat() + ". " + itemObat.getNama());
        }
        System.out.print("Masukkan id obat: ");
        pilihan = input.nextLine();
        System.out.print("Masukkan jumlah obat: ");
        int jumlahObat = Integer.parseInt(input.nextLine());

        for (Obat itemObat : daftarObat) {
            if (pilihan.equals(itemObat.getIdObat())){
                pembeli.tambahkeKeranjang(itemObat, jumlahObat);
            }
        }
    }
}
