package com.kelompoktiga.apotek;

import com.kelompoktiga.apotek.user.Apoteker;
import com.kelompoktiga.apotek.user.Pembeli;
import com.kelompoktiga.apotek.user.User;

public class TestClass {
    public static void main(String[] args) {
        Pembeli pembeli1 = new Pembeli("UID1", "Aljabbar", "0824141", "Bandar Lampung");
        User pembeli2 = new Pembeli("2", "Wafa", "0343", "Pringsewu");
//        Pembeli pembeli3 = new User("3", "Al", "4124", "fadfsad");

        Apoteker apoteker1 = new Apoteker("UID1", "Aljabbar", "0824141", "Bandar Lampung");
        User apoteker2 = new Apoteker("UID1", "Aljabbar", "0824141", "Bandar Lampung");
//        Apoteker apoteker3 = new User("UID1", "Aljabbar", "0824141", "Bandar Lampung");

        /*
         * Cek Instance Of
         */
        System.out.println(pembeli1 instanceof Pembeli);
        System.out.println(pembeli1 instanceof User);

        System.out.println(apoteker1 instanceof Apoteker);
        System.out.println(apoteker1 instanceof User);
    }
}
