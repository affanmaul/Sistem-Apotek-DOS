package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Apoteker;
import com.kelompoktiga.apotek.user.Pembeli;
import com.kelompoktiga.apotek.user.User;

import java.util.ArrayList;
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

            if (userInput.equals("exit")){
                System.out.println("Program berakhir.");
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
     *  Menu untuk User sebagai PEMBELI
     */
    public void menuPembeli() {
        aktif = true;

        System.out.println("\nAnda masuk sebagai Pembeli.");
        while (aktif) {
            System.out.println("1. Tambah Obat Ke Keranjang");
            System.out.println("2. Hapus Obat dari Keranjang");
            System.out.println("3. Lihat isi Keranjang");
            System.out.println("4. Hapus Seluruh isi Keranjang");
            System.out.println("5. Cari Obat");
            System.out.println("6. Exit");
            System.out.print("Pilih Menu [1-6]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> tambahObatKeKeranjang();
                case "2" -> hapusObatDariKeranjang();
                case "3" -> lihatKeranjang();
                case "4" -> hapusSeluruhItemKeranjang();
                case "5" -> cariObat();
                case "6" -> {
                    aktif = false;
                    System.out.println("Keluar Akun.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    
    private Obat pilihObat() {
        System.out.println("\nMenampilkan Daftar Obat:");
        // Menampilkan daftar obat
        for (Obat itemObat : daftarObat) {
            System.out.println(itemObat.getIdObat() + ". " + itemObat.getNama());
        }
        System.out.print("Masukkan id obat: ");
        pilihan = input.nextLine();

        // cek obat yang dipilih
        for (Obat itemObat : daftarObat) {
            if (pilihan.equals(itemObat.getIdObat())) {
                return itemObat;
            }
        }
        return  null;
    }

    private void tambahObatKeKeranjang(){
        Obat obat = pilihObat();
        // jika obat yang dipilih valid / tidak null
        // maka minta input jumlah obat dan tambah ke keranjang
        if (obat != null){
            System.out.print("Masukkan jumlah obat: ");
            int jumlahObat = Integer.parseInt(input.nextLine());
            pembeli.tambahObatKeKeranjang(obat, jumlahObat);
        } else {
            System.out.println("Maaf terjadi kesalahan.");
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
            System.out.print("Masukkan nomor obat yang ingin anda hapus: ");
            pilihan = input.nextLine();
            pembeli.hapusObatDariKeranjang(Integer.parseInt(pilihan));
        }
    }

    private void lihatKeranjang() {
        pembeli.lihatKeranjang();
    }

    private void hapusSeluruhItemKeranjang(){
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

        // jika valid maka tampilkan data obat
        if (obat != null) {
            System.out.println("Data obat ditemukan: ");
            obat.cetakObat();
            System.out.println();
        } else {
            System.out.println("Data Tidak Ditemukan.\n");
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

    /**
     *  MENU Untuk User Sebagai APOTEKER
     */
    public void menuApoteker() {
        aktif = true;
        System.out.println("\nAnda masuk sebagai Apoteker");
        while (aktif) {
            System.out.println("1. Tambah Obat");
            System.out.println("2. Exit");
            System.out.print("Pilih Menu [1-2]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> tambahObatBaru();
                case "2" -> {
                    aktif = false;
                    System.out.println("Keluar Akun.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    private void tambahObatBaru() {
        // TODO: Create new method to add new Obat
    }


    public void tambahUser(User user) {
        this.daftarUser.add(user);
    }
}
