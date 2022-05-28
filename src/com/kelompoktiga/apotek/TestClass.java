package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.model.Obat;
import com.kelompoktiga.apotek.user.Apoteker;
import com.kelompoktiga.apotek.user.Pembeli;
import com.kelompoktiga.apotek.user.User;

import java.util.ArrayList;

public class TestClass {
    public static void main(String[] args) {
        Pembeli pembeli1 = new Pembeli("affan", "UID1", "Affan", "089619258610", "Bandar Lampung");
        User pembeli2 = new Pembeli("wafa", "UID2", "Wafa", "089898989343", "Pringsewu");
//        Pembeli pembeli3 = new User("3", "Al", "4124", "fadfsad");

        Apoteker apoteker1 = new Apoteker("waluyo", "APK1", "Pak Waluyo", "0824141", "Bandar Lampung");
        User apoteker2 = new Apoteker("joko", "APK1", "Pak Joko", "0824141", "Bandar Lampung");
//        Apoteker apoteker3 = new User("UID1", "Aljabbar", "0824141", "Bandar Lampung");

        /*
         * Cek Instance Of
         *
         * instanceof adalah salah satu keyword pada Java, yang digunakan untuk membandingkan suatu objek,
         * apakah instansiasi dari suatu class atau tidak, hasil dari perbandingan tersebut akan menghasilkan
         * nilai boolean berupa nilai true atau false.
         */
        System.out.println(pembeli1 instanceof Pembeli);
        System.out.println(pembeli1 instanceof User);

        System.out.println(apoteker1 instanceof Apoteker);
        System.out.println(apoteker1 instanceof User);
    }

}
