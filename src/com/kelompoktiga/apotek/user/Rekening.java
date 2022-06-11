package com.kelompoktiga.apotek.user;

public class Rekening {
    private int nomorRekening;
    private String namaAkun;
    private float saldo;
    private static long noRekCounter = 1;

    public Rekening(String namaAkun, int saldo) {
        this.namaAkun = namaAkun;
        this.saldo = saldo;
        this.nomorRekening = createNoRek();
    }

    private int createNoRek() {
        return 10000 + (int) noRekCounter++;
    }

    public int getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(int nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getNamaAkun() {
        return namaAkun;
    }

    public void setNamaAkun(String namaAkun) {
        this.namaAkun = namaAkun;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

}
