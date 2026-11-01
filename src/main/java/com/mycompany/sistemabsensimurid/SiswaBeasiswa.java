/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabsensimurid;

/**
 *
 * @author HYPE AMD
 */
public class SiswaBeasiswa extends Siswa implements Pelaporan, Peringatan {
    private String mataKuliah;

    public SiswaBeasiswa(String nama, int nim, int jumlahHadir, String mataKuliah) {
        super(nama, nim, jumlahHadir);
        this.mataKuliah = mataKuliah;
    }

    public String getJenisBeasiswa() { return mataKuliah; }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Beasiswa] ");
        super.tampilkanInfo();
        System.out.printf(" | Mata Kuliah   : %-15s%n", this.mataKuliah);
    }

    @Override
    public void statusAbsensi() {
        int minimalHadir = (int) Math.ceil(TOTAL_PERTEMUAN * 0.90);
        boolean memenuhi = getJumlahHadir() >= minimalHadir;
        System.out.printf("   -> Syarat Beasiswa    : Minimal %d/%d pertemuan (90%%) | Status: %s%n",
                minimalHadir, TOTAL_PERTEMUAN, (memenuhi ? "MEMENUHI SYARAT" : "TIDAK MEMENUHI SYARAT"));
    }

    @Override
    public void cetakLaporan() {
        System.out.println("   -> [Laporan] Laporan kehadiran beasiswa " + getNama() + " dicetak dan diteruskan ke bagian kemahasiswaan.");
    }

    @Override
    public void kirimPeringatan() {
        int minimalHadir = (int) Math.ceil(TOTAL_PERTEMUAN * 0.90);
        if (getJumlahHadir() < minimalHadir) {
            System.out.println("   -> [Peringatan] " + getNama() + ", kehadiran di bawah 90%. Beasiswa kamu terancam dicabut!");
        } else {
            System.out.println("   -> [Peringatan] " + getNama() + ", kehadiran aman. Tidak ada peringatan untuk saat ini.");
        }
    }
}