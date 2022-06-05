package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Apoteker;
import com.kelompoktiga.apotek.user.Pembeli;
import com.kelompoktiga.apotek.user.User;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Obat> daftarObat = new ArrayList<>();
        daftarObat.add(new Obat("Bodrex", 2000));
        daftarObat.add(new Obat("Paramex", 5000));
        daftarObat.add(new Obat("OBH", 3000));
        daftarObat.add(new Obat("Tolak Angin", 5000));
        daftarObat.add(new Obat("Antimo", 10000));
        daftarObat.add(new Obat("daktarin", 7000, true));

        SistemApotek sistemApotek = new SistemApotek(daftarObat);

        sistemApotek.tambahUser(new Pembeli("aljabbar", "U1", "Aljabbar", "0824141", "Bandar Lampung"));
        sistemApotek.tambahUser(new Pembeli("affan", "U2", "Affan", "082414133343", "Bandar Lampung"));
        sistemApotek.tambahUser(new Apoteker("waluyo","AP1", "Pak Waluyo", "0824141", "Bandar Lampung"));
        sistemApotek.loginManager();
    }
}
