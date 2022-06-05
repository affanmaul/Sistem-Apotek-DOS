package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Apoteker;
import com.kelompoktiga.apotek.user.Pembeli;
import com.kelompoktiga.apotek.user.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemApotek {
    private ArrayList<Obat> daftarObat;
    private ArrayList<User> daftarUser = new ArrayList<>();
    private Apoteker apoteker;
    private Pembeli pembeli;

    private Scanner input = new Scanner(System.in);
    private String pilihan;
    private boolean aktif;

    public SistemApotek(ArrayList<Obat> daftarObat) {
        this.daftarObat = daftarObat;
        System.out.println("Selamat Datang di Apotek DOS.");
    }

    public void loginManager() {
        while (true) {
            System.out.println("Silahkan Login terlebih dahulu. [atau ketik 'exit' untuk keluar]");
            System.out.print("Masukan username Anda : ");
            String userInput = input.nextLine();

            if (userInput.equals("exit")) {
                System.out.println("Program berakhir.");
                input.close();  // menutup scanner
                break;
            } else {
                User user = cariUserByUsername(userInput); // Mengecek username yang diinputkan
                if (user instanceof Pembeli) {  // jika User == Pembeli, maka masuk ke menu pembeli
                    this.pembeli = (Pembeli) user;
                    menuPembeli();
                } else if (user instanceof Apoteker) {  // jika User == Apoteker, maka masuk ke menu Apoteker
                    this.apoteker = (Apoteker) user;
                    menuApoteker();
                } else {    // jika tidak keduanya maka username tidak valid.
                    System.out.println("Username tidak dapat ditemukan");
                }
            }
        }
    }

    public User cariUserByUsername(String username) {
        for (User user : daftarUser) {
            if (user.getUsername().equals(username)) {  // jika username sesuai, maka return objek User
                return user;
            }
        }
        return null;
    }

    /**
     * Menu untuk User sebagai PEMBELI
     */
    public void menuPembeli() {
        aktif = true;

        System.out.println("\nAnda masuk sebagai Pembeli.");
        while (aktif) {
            System.out.println("\nMENU PEMBELI");
            System.out.println("1. Lihat Daftar Obat");
            System.out.println("2. Tambah Obat Ke Keranjang");
            System.out.println("3. Hapus Obat dari Keranjang");
            System.out.println("4. Lihat isi Keranjang");
            System.out.println("5. Hapus Seluruh isi Keranjang");
            System.out.println("6. Cari Obat");
            System.out.println("7. Info akun");
            System.out.println("8. Exit");
            System.out.print("Pilih Menu [1-8]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> tampilkanDaftarObat();
                case "2" -> tambahObatKeKeranjang();
                case "3" -> hapusObatDariKeranjang();
                case "4" -> lihatKeranjang();
                case "5" -> hapusSeluruhItemKeranjang();
                case "6" -> cariObat();
                case "7" -> infoAkunPembeli();
                case "8" -> {
                    aktif = false;
                    System.out.println("Keluar Akun.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    private void tampilkanDaftarObat() {
        System.out.println("\nMenampilkan Daftar Obat:");
        // Menampilkan daftar obat
        for (Obat itemObat : daftarObat) {
            System.out.println(itemObat.getIdObat() + ". " + itemObat.getNama());
        }
    }

    private Obat pilihObat() {
        tampilkanDaftarObat();
        System.out.print("Masukkan id obat: ");
        pilihan = input.nextLine();

        // cek obat yang dipilih
        for (Obat itemObat : daftarObat) {
            if (pilihan.equals(itemObat.getIdObat())) {
                return itemObat;
            }
        }
        return null;
    }

    private void tambahObatKeKeranjang() {
        Obat obat = pilihObat();
        if (obat != null) {
            try {
                // jika obat yang dipilih valid / tidak null
                // maka minta input jumlah obat dan tambah ke keranjang
                System.out.print("Masukkan jumlah obat: ");
                int jumlahObat = input.nextInt(); input.nextLine();
                pembeli.tambahObatKeKeranjang(obat, jumlahObat);
            } catch (InputMismatchException e) {
                System.out.println("Input yang dimasukkan salah.");
                System.out.println(e.getMessage());
                System.out.println();
            } catch (Exception e) {
                System.out.println("Maaf terjadi Kesalahan.");
            }
        } else {
            System.out.println("Maaf Input tidak valid.");
        }
    }

    private void hapusObatDariKeranjang() {
        // cek item pada keranjang
        if (pembeli.getKeranjang().getDaftarItem().isEmpty()) {
            System.out.println("Keranjang anda masih kosong.\n");
        } else {
            // jika keranjang tidak kosong,
            // maka tampilkan keranjang dan minta input nomor yang ingin dihapus
            lihatKeranjang();
            try {
                System.out.print("Masukkan nomor obat yang ingin anda hapus: ");
                pilihan = input.nextLine();
                pembeli.hapusObatDariKeranjang(Integer.parseInt(pilihan));
            } catch (NumberFormatException e) {
                System.out.println("Format Input yang dimasukkan salah");
                System.out.println(e.getMessage());
                System.out.println();
            } catch (Exception e) {
                System.out.println("Maaf terjadi Kesalahan.");
            }
        }
    }

    private void lihatKeranjang() {
        pembeli.lihatKeranjang();
    }

    private void hapusSeluruhItemKeranjang() {
        if (pembeli.getKeranjang().getDaftarItem().isEmpty()) {
            System.out.println("Keranjang anda masih kosong.\n");
        } else {
            pembeli.hapusSeluruhIsiKeranjang();
        }
    }

    public void cariObat() {
        System.out.print("\nMasukan nama obat: ");
        String namaObat = input.nextLine();
        // cek obat berdasarkan nama yang diinput
        Obat obat = cekObatByNama(namaObat);

        try {
            System.out.println("Data obat ditemukan: ");
            obat.cetakObat();
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("Data Obat tidak ditemukan");
            // System.out.println(e.getMessage());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Maaf terjadi Kesalahan.");
        }
    }

    // cek apakah nama obat yang diinput terdapat pada daftarObat atau tidak
    public Obat cekObatByNama(String namaObat) {
        //cek setiap obat pada daftar Obat
        for (Obat itemObat : daftarObat) {
            // jika namaObat == yang diinput maka return obat
            if (itemObat.getNama().equalsIgnoreCase(namaObat)) {
                return itemObat;
            }
        }
        return null; // jika tidak ada yang sama maka return null
    }

    public void infoAkunPembeli() {
        pembeli.cetakDetailInfoPembeli();
    }

    /**
     * MENU Untuk User Sebagai APOTEKER
     */
    public void menuApoteker() {
        aktif = true;
        System.out.println("\nAnda masuk sebagai Apoteker");
        while (aktif) {
            System.out.println("\nMENU APOTEKER");
            System.out.println("1. Lihat Daftar Obat");
            System.out.println("2. Tambah Obat");
            System.out.println("3. Hapus Obat");
            System.out.println("4. Lihat Daftar User");
            System.out.println("5. Exit");
            System.out.print("Pilih Menu [1-5]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> tampilkanDaftarObat();
                case "2" -> tambahObatBaru();
                case "3" -> hapusObatDariDaftar();
                case "4" -> tampilkanDaftarUser();
                case "5" -> {
                    aktif = false;
                    System.out.println("Keluar Akun.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    private void tambahObatBaru() {
        apoteker.tambahObatBaru(daftarObat);
    }

    private void hapusObatDariDaftar() {
        tampilkanDaftarObat();
        Obat obat = pilihObat();
        hapusObat(obat);
        System.out.println("Berhasil menghapus Data Obat.");
    }

    private void tampilkanDaftarUser() {
        int counter = 0;
        System.out.println();
        for (User user: daftarUser){
            user.cetakInfo();
            if (user instanceof Pembeli){
                System.out.println("Role : Pembeli");
            } else if (user instanceof Apoteker){
                System.out.println("Role : Apoteker");
            }
            System.out.println();
            counter++;
        }
        System.out.println("Total User : " + counter);
    }

    public void tambahUser(User user) {
        this.daftarUser.add(user);
    }

    public void tambahObat(Obat obat){
        this.daftarObat.add(obat);
    }

    public void hapusObat(Obat obat){
        this.daftarObat.remove(obat);
    }
}
