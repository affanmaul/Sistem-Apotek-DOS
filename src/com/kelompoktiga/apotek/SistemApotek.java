package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Apoteker;
import com.kelompoktiga.apotek.user.Pembeli;
import com.kelompoktiga.apotek.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemApotek {
    private ArrayList<Obat> daftarObat = new ArrayList<Obat>();
    private ArrayList<Pembeli> daftarPembeli = new ArrayList<>();
    private ArrayList<Apoteker> daftarApoteker = new ArrayList<>();
    private ArrayList<User> daftarUser = new ArrayList<User>();
    private Apoteker apoteker;
    private Pembeli pembeli;

    private Scanner input = new Scanner(System.in);
    private String pilihan;
    private boolean aktif ;

    public SistemApotek(ArrayList<Obat> daftarObat) {
        this.daftarObat = daftarObat;
        System.out.println("Selamat Datang di Apotek Dos.");
    }

    public void loginManager(){
        while (true) {
            System.out.print("Masukan username Anda : ");
            String username = input.nextLine();
            User user = cariUserByUsername(username);

            if (user instanceof Pembeli) {
                menuPembeli();
            } else if (user instanceof Apoteker) {
                menuApoteker();
            } else {
                System.out.println("Data tidak Ditemukan");
            }
        }
    }

    public User cariUserByUsername(String username) {
        for (User u : daftarUser) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        } return null;
    }

    public void menuPembeli() {
        aktif = true;

        System.out.println("Anda masuk sebagai Pembeli.");
        while (aktif) {
            System.out.println("1. Tambah Obat Ke Keranjang");
            System.out.println("2. Lihat isi Keranjang");
            System.out.println("3. Cari Obat");
            System.out.println("4. Exit");
            System.out.print("Pilih Menu [1-3]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> pilihObat();
                case "2" -> lihatKeranjang();
                case "3" -> cariObat();
                case "4" -> {
                    aktif = false;
                    System.out.println("Program Selesai.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    public void menuApoteker() {
        aktif = true;
        System.out.println("Anda masuk sebagai Apoteker");
        while (aktif) {
            System.out.println("1. Tambah Obat");
            System.out.println("2. Exit");
            System.out.print("Pilih Menu [1-2]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> tambahObat();
                case "2" -> {
                    aktif = false;
                    System.out.println("Program Selesai.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    private void tambahObat() {
    }

    private void lihatKeranjang() {
        pembeli.lihatKeranjang();
    }
    public void cariObat(){
        System.out.println("Masukan nama obat ");
        String namaObat = input.nextLine();
        Obat obat = cekObat(namaObat); //agar data di print jika tidak null
        if (obat != null){
            obat.cetakObat();
        }else {
            System.out.println("Data Tidak Ditemukan.");
        }


    }
    public Obat cekObat(String namaObat){

        for (Obat itemObat : daftarObat){//cek setiap obat
            if (itemObat.getNama().equalsIgnoreCase(namaObat)){// jika nama sama maka return obat
                return itemObat;
            }
        }return  null;//kalo ga ya ga
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
                pembeli.tambahKeKeranjang(itemObat, jumlahObat);
            }
        }
    }

    public void tambahUser(User user){
        this.daftarUser.add(user);
    }
}
