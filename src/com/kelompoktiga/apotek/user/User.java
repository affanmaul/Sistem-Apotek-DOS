package com.kelompoktiga.apotek.user;

public class User {
    // atribut
    private String username;
    private String userId;
    private String nama;
    private String nomorTelepon;
    private String alamat;

    public User(String username, String userId, String nama, String nomorTelepon, String alamat) {
        this.username = username;
        this.userId = userId;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
    }

    public void cetakInfo() {
        System.out.println("id : " + this.userId);
        System.out.println("Nama :" + this.nama);
        System.out.println("No Hp : "+ this.nomorTelepon);
        System.out.println("Alamat :" + this.alamat);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
