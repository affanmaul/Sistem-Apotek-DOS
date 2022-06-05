package com.kelompoktiga.apotek.user;

import com.kelompoktiga.apotek.model.Obat;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Apoteker extends User {
    Scanner input = new Scanner(System.in);

    public Apoteker(String username, String userId, String nama, String nomorTelepon, String alamat) {
        super(username, userId, nama, nomorTelepon, alamat);
    }

    public void tambahObatBaru(ArrayList<Obat> daftarObat) {
        boolean apakahObatKeras;
        float hargaObat;

        System.out.print("Masukkan nama obat: ");
        String namaObat = input.nextLine();

        while (true) {
            try {
                System.out.print("Masukan harga obat: ");
                hargaObat = Float.parseFloat(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Format input salah.");
                System.out.println();
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan.");
                System.out.println();
            }
        }

        while (true) {
            System.out.print("Apakah Obat keras? [y/n]: ");
            String userInput = input.nextLine();
            if (userInput.equals("y")) {
                apakahObatKeras = true;
                break;
            } else if (userInput.equals("n")) {
                apakahObatKeras = false;
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        Obat obat = new Obat(namaObat, hargaObat, apakahObatKeras);
        daftarObat.add(obat);
        System.out.println("Berhasil menambahkan Data Obat Baru");
    }


    public void hapusObatDariDaftar(ArrayList<Obat> daftarObat) {

    }

    public void hapusObatDariDaftar() {
        try {
            System.out.print("Masukkan ID obat yang ingin dihapus: ");
            int idObat = Integer.parseInt(input.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("Format input salah.\n");
        } catch (Exception e) {
            System.out.println("Maaf terjadi kesalahan.");
        }
    }

    @Override
    public void cetakInfo() {
        System.out.println("Cetak data Apoteker");
        super.cetakInfo();
    }
}
