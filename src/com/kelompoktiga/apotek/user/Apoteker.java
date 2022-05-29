package com.kelompoktiga.apotek.user;

public class Apoteker extends User{
    public Apoteker(String username, String userId, String nama, String nomorTelepon, String alamat) {
        super(username,userId, nama, nomorTelepon, alamat);
    }

    @Override
    public void cetakInfo() {
        System.out.println();
        System.out.println("Cetak data apoteker");
        super.cetakInfo();
    }
}
