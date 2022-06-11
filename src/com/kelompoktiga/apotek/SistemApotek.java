package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Apoteker;
import com.kelompoktiga.apotek.user.Pembeli;
import com.kelompoktiga.apotek.user.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class SistemApotek {
    private ArrayList<Obat> daftarObat;
    private ArrayList<User> daftarUser = new ArrayList<>();
    private Apoteker apoteker;
    private Pembeli pembeli;

    private Scanner input = new Scanner(System.in);
    private String pilihan;
    private boolean aktif;
    boolean menuCheckout = false;

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
            System.out.println("2. Cari Obat");
            System.out.println("3. Menu Keranjang");
            System.out.println("4. Checkout Isi Keranjang");
            System.out.println("5. Info akun");
            System.out.println("6. Exit");
            System.out.print("Pilih Menu [1-6]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> tampilkanDaftarObat();
                case "2" -> cariObat();
                case "3" -> menuKeranjang();
                case "4" -> checkoutIsiKeranjang();
                case "5" -> infoAkunPembeli();
                case "6" -> {
                    aktif = false;
                    System.out.println("Keluar Akun.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    private void menuKeranjang() {
        boolean menuKeranjang = true;
        while (menuKeranjang){
            System.out.println("\nMenu Keranjang");
            System.out.println("1. Lihat Daftar Obat");
            System.out.println("2. Tambah Obat Ke Keranjang");
            System.out.println("3. Hapus Obat dari Keranjang");
            System.out.println("4. Lihat isi Keranjang");
            System.out.println("5. Hapus Seluruh isi Keranjang");
            System.out.println("6. CHECKOUT isi Keranjang");
            System.out.println("7. KEMBALI");
            System.out.print("Pilih Menu [1-7]: ");
            pilihan = input.nextLine();

            switch (pilihan) {
                case "1" -> tampilkanDaftarObat();
                case "2" -> tambahObatKeKeranjang();
                case "3" -> hapusObatDariKeranjang();
                case "4" -> lihatKeranjang();
                case "5" -> hapusSeluruhItemKeranjang();
                case "6" -> checkoutIsiKeranjang();
                case "7" -> {
                    menuKeranjang = false;
                    System.out.println("Kembali ke menu awal.");
                }
                default -> System.out.println("Input Salah!");
            }
        }
    }

    private void checkoutIsiKeranjang() {
        if (this.pembeli.getKeranjang().getDaftarItem().size() == 0){
            System.out.println("Keranjang Kamu masih kosong..");
        } else {
            System.out.println();
            System.out.println("Menampilkan isi keranjang:");
            this.pembeli.getKeranjang().cetakDataKeranjang();
            System.out.println();
            menuCheckout = true;
            while (menuCheckout){
                System.out.println("Silahkan pilih metode pembayaran");
                System.out.println("1. Bayar di Kasir");
                System.out.println("2. Transfer Bank");
                System.out.println("3. Batal");
                System.out.print("Pilihan [1-3]: ");
                pilihan = input.nextLine();
                switch (pilihan) {
                    case "1" -> bayar("KASIR");
                    case "2" -> bayar("TRANSFER_BANK");
                    case "3" -> menuCheckout = false;
                    default -> System.out.println("Kembali ke menu keranjang.");
                }
            }
        }
    }

    private void bayar(String metodePembayaran) {
        if (metodePembayaran.equals("KASIR")){
            cetakStrukPembelian();
            menuCheckout = false;
        } else if (metodePembayaran.equals("TRANSFER_BANK")) {
            float sisaSaldo = this.pembeli.getRekening().getSaldo() - this.pembeli.getKeranjang().getTotalHarga();
            if (sisaSaldo <= 2000){
                System.out.println("Maaf saldo tidak cukup.");
            } else {
                this.pembeli.getRekening().setSaldo(sisaSaldo);
                this.pembeli.getKeranjang().hapusSeluruhData();
                System.out.println("Pembayaran berhasil");
                menuCheckout = false;
            }
        }
    }

    private void cetakStrukPembelian() {
        Random rand = new Random();
        // random number antara 1111 to 9998
        int uniqueCode = 1111 + rand.nextInt(8888);

        System.out.println("\n=========================================");
        System.out.println("=======STRUK PEMBAYARAN APOTEK DOS=======");
        System.out.println("=========================================");
        System.out.println("Kode Pembayaran: " + uniqueCode);
        this.pembeli.getKeranjang().cetakDataKeranjang();
        System.out.println("=========================================");
        System.out.println("Harap tunjukkan struk pembelian ke kasir.");
        System.out.println("=========================================\n");

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
                int jumlahObat = input.nextInt();

                input.nextLine();
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
