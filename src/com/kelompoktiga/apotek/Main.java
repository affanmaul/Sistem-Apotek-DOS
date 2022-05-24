package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Pembeli;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Obat> daftarObat = new ArrayList<>();
        daftarObat.add(new Obat("1", "Bodrex",2000));
        daftarObat.add(new Obat("2", "Paramex",5000));
        daftarObat.add(new Obat("3", "OBH",3000));

        Pembeli pembeli = new Pembeli("UID1", "Aljabbar", "0824141", "Bandar Lampung");

        SistemApotek sistemApotek = new SistemApotek(daftarObat, pembeli);
        sistemApotek.mulai();
    }
}
