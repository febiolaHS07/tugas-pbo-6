/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabsensimurid;

/**
 *
 * @author HYPE AMD
 */
public class SiswaInternational extends Siswa {
    private String mataKuliah;

    public SiswaInternational(String nama, int nim, int jumlahHadir, String mataKuliah) {
        super(nama, nim, jumlahHadir);
        this.mataKuliah = mataKuliah;
    }

    public String getNegaraAsal() { return mataKuliah; }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Inter   ] ");
        super.tampilkanInfo();
        System.out.printf(" | Mata Kuliah: %-15s%n", this.mataKuliah);
    }

    @Override
    public void statusAbsensi() {
        int minimalHadir = (int) Math.ceil(TOTAL_PERTEMUAN * 0.75);
        boolean memenuhi = getJumlahHadir() >= minimalHadir;
        System.out.printf("   -> Syarat Internasional: Minimal %d/%d pertemuan (75%%) | Status: %s%n",
                minimalHadir, TOTAL_PERTEMUAN, (memenuhi ? "MEMENUHI SYARAT" : "TIDAK MEMENUHI SYARAT"));
    }
}