/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabsensimurid;

/**
 *
 * @author HYPE AMD
 */

public class SiswaReguler extends Siswa implements Pelaporan {
    private String mataKuliah;

    public SiswaReguler(String nama, int nim, int jumlahHadir, String mataKuliah) {
        super(nama, nim, jumlahHadir);
        this.mataKuliah = mataKuliah;
    }

    public String getMataKuliah() { return mataKuliah; }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Reguler ] ");
        super.tampilkanInfo();
        System.out.printf(" | Mata Kuliah: %-15s%n", this.mataKuliah);
    }

    @Override
    public void statusAbsensi() {
        int minimalHadir = (int) Math.ceil(TOTAL_PERTEMUAN * 0.80);
        boolean memenuhi = getJumlahHadir() >= minimalHadir;
        System.out.printf("   -> Syarat Reguler     : Minimal %d/%d pertemuan (80%%) | Status: %s%n",
                minimalHadir, TOTAL_PERTEMUAN, (memenuhi ? "MEMENUHI SYARAT" : "TIDAK MEMENUHI SYARAT"));
    }

    @Override
    public void cetakLaporan() {
        System.out.println("   -> [Laporan] Laporan kehadiran reguler " + getNama() + " untuk mata kuliah "
                + mataKuliah + " dicetak: " + getJumlahHadir() + " dari " + TOTAL_PERTEMUAN + " pertemuan.");
    }
}