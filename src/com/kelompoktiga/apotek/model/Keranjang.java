package com.kelompoktiga.apotek.model;

import java.util.ArrayList;

public class Keranjang {
    private String idKeranjang;
    private ArrayList<ItemKeranjang> daftarItem = new ArrayList<>();
    private int totalBarang;
    private float totalHarga;

    public void tambahObat(Obat obat, int jumlahObat) {
        // jika daftar kosong maka langsung tambah baru
        if (daftarItem.isEmpty()) {
            daftarItem.add(new ItemKeranjang(obat, jumlahObat));
            System.out.println("Berhasil menambahkan barang.\n");
        }
        // jika daftar tidak kosong maka cek itemnya terlebih dulu
        else {
            // cek item yang ada di keranjang
            boolean tidakAdaPadaDaftar = true;
            for (ItemKeranjang itemKeranjang : daftarItem) {
                // jika obat sudah ada maka cukup tambahkan jumlahnya
                if (obat.getIdObat().equals(itemKeranjang.getObat().getIdObat())) {
                    tidakAdaPadaDaftar = false;
                    itemKeranjang.setJumlahObat(itemKeranjang.getJumlahObat() + jumlahObat);
                    System.out.println("Berhasil menambahkan barang.\n");
                    break;
                }
            }
            // jika belum ada, maka buat item baru
            if (tidakAdaPadaDaftar) {
                daftarItem.add(new ItemKeranjang(obat, jumlahObat));
                System.out.println("Berhasil menambahkan barang.\n");
            }
        }

        // rubah total barang dan total harga
        setTotalBarang(getTotalBarang() + jumlahObat);
        float totalHarga = jumlahObat * obat.getHarga();
        setTotalHarga(getTotalHarga() + totalHarga);
    }

    public void hapusSeluruhData() {
        daftarItem.clear();
        totalBarang = 0;
        totalHarga = 0;
        System.out.println("Berhasil menghapus seluruh item pada Keranjang.\n");
    }

    public void hapusObat(int indexObat) {
        try {
        // menampung item berdasarkan index
        ItemKeranjang itemKeranjang = daftarItem.get(indexObat - 1);

        // mengurangi total barang dan harga pada keranjang
        totalBarang = totalBarang - itemKeranjang.getJumlahObat();
        totalHarga = totalHarga - itemKeranjang.getJumlahObat() * itemKeranjang.getObat().getHarga();

        // menghapus item
        daftarItem.remove(itemKeranjang);
        System.out.println("Berhasil menghapus Obat dari keranjang.\n");
        } catch (IndexOutOfBoundsException e){
            System.out.println("Input melebihi Daftar.");
        } catch (Exception e){
            System.out.println("Maaf terjadi Kesalahan.");
        }
    }

    public void cetakDataKeranjang() {
        int i = 1;
        if (getDaftarItem().isEmpty()) {
            System.out.println("Keranjang anda Kosong.\n");
        } else {
            System.out.println("Daftar Obat: ");

            for (ItemKeranjang item : getDaftarItem()) {
                System.out.println(i + ". " + item.getObat().getNama() + " - Rp." + item.getObat().getHarga() + "/obat"+ " (" + item.getJumlahObat() + ")");
                i++;
            }
            System.out.println("Total Barang: " + getTotalBarang());
            System.out.println("Total Harga: " + getTotalHarga());
        }
    }


    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public ArrayList<ItemKeranjang> getDaftarItem() {
        return daftarItem;
    }

    public void setDaftarItem(ArrayList<ItemKeranjang> daftarItem) {
        this.daftarItem = daftarItem;
    }

    public int getTotalBarang() {
        return totalBarang;
    }

    public void setTotalBarang(int totalBarang) {
        this.totalBarang = totalBarang;
    }

    public float getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
    }
}
