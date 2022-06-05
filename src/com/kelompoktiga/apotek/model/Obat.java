package com.kelompoktiga.apotek.model;

public class Obat {
    private String idObat;
    private String nama;
    private float harga;
    private boolean obatKeras = false;
    private static long idCounter = 1;  // untuk pembuatan ID

    public Obat(String nama, float harga) {
        this.nama = nama;
        this.harga = harga;

        // pembuatan ID dilakukan secara otomatis di dalam kelas ini ketika objek dibuat
        this.idObat = createId();
    }

    private String createId() {
        return String.valueOf(idCounter++);
    }

    public Obat(String nama, float harga, boolean obatKeras) {
        this(nama, harga);
        this.obatKeras = obatKeras;

    }

    public void cetakObat() {
        System.out.println("Id obat : " + idObat);
        System.out.println("nama obat : " + nama);
        System.out.println("harga obat : Rp." + harga);
        if (obatKeras == true){
            System.out.println("Merupakan obat keras");
        }
        else {
            System.out.println("Bukan merupakan obat keras");
        }
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }
}
