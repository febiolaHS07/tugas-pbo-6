/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabsensimurid;

/**
 *
 * @author HYPE AMD
 */
public class Siswa {
    private String nama;
    private int nim;
    private int jumlahHadir;
    public static final int TOTAL_PERTEMUAN = 16;
    public static int totalSiswaBerhasilDibuat = 0;

    public Siswa(String nama, int nim, int jumlahHadir) {
        this.nama = nama;
        this.nim = nim;
        setJumlahHadir(jumlahHadir);
        totalSiswaBerhasilDibuat++;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public int getNim() { return nim; }
    public void setNim(int nisn) { this.nim = nim; }

    public int getJumlahHadir() { return jumlahHadir; }
    public void setJumlahHadir(int jumlahHadir) {
        if (jumlahHadir < 0) this.jumlahHadir = 0;
        else if (jumlahHadir > TOTAL_PERTEMUAN) this.jumlahHadir = TOTAL_PERTEMUAN;
        else this.jumlahHadir = jumlahHadir;
    }

    public double getPersentaseKehadiran() {
        return ((double) this.jumlahHadir / TOTAL_PERTEMUAN) * 100.0;
    }

    public void tampilkanInfo() {
        System.out.printf("NIM: %-6d | Nama: %-15s | Kehadiran: %2d/%d (%5.1f%%)",
                this.nim, this.nama, this.jumlahHadir, TOTAL_PERTEMUAN, getPersentaseKehadiran());
    }

    public void statusAbsensi() {
        System.out.println("Status: Mengikuti prosedur absensi akademik standar.");
    }
}