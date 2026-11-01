/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabsensimurid;

import java.util.Scanner;

public class SistemAbsensi {

    public static void prosesAbsensi(Siswa siswa) {
        siswa.statusAbsensi();

        if (siswa instanceof Pelaporan) {
            Pelaporan laporan = (Pelaporan) siswa;
            laporan.cetakLaporan();
        }

        if (siswa instanceof Peringatan) {
            Peringatan peringatan = (Peringatan) siswa;
            peringatan.kirimPeringatan();
        }
    }

    public static void cariSiswa(String nama, Siswa[] daftar, int total) {
        if (total == 0) {
            System.out.println("Belum ada data siswa yang tersimpan untuk dicari.");
            return;
        }

        System.out.println("\n--- Hasil Pencarian Nama (Tipe Parameter: String): '" + nama + "' ---");
        boolean ditemukan = false;
        for (int i = 0; i < total; i++) {
            if (daftar[i].getNama().equalsIgnoreCase(nama)) {
                daftar[i].tampilkanInfo();
                prosesAbsensi(daftar[i]);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Data siswa dengan nama '" + nama + "' tidak ditemukan.");
        }
    }

    public static void cariSiswa(int nim, Siswa[] daftar, int total) {
        if (total == 0) {
            System.out.println("Belum ada data siswa yang tersimpan untuk dicari.");
            return;
        }

        System.out.println("\n--- Hasil Pencarian NIM (Tipe Parameter: Integer): " + nim + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < total; i++) {
            if (daftar[i].getNim() == nim) {
                daftar[i].tampilkanInfo();
                prosesAbsensi(daftar[i]);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Data siswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Siswa[] daftarSiswa = new Siswa[20];
        int jumlahSiswa = 0;

        daftarSiswa[jumlahSiswa++] = new SiswaReguler("Andi Pratama", 1001, 14, "Struktur Data");
        daftarSiswa[jumlahSiswa++] = new SiswaBeasiswa("Siti Rahma", 1002, 13, "Matematika Diskrit");
        daftarSiswa[jumlahSiswa++] = new SiswaInternational("John Smith", 1003, 10, "Elektronika Digital");
        daftarSiswa[jumlahSiswa++] = new SiswaBeasiswa("Budi Santoso", 1004, 16, "Pemrograman Java");
        daftarSiswa[jumlahSiswa++] = new SiswaReguler("Dewi Lestari", 1005, 9, "Basis Data");

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n==================================================================");
            System.out.println("                   SISTEM ABSENSI AKADEMIK                        ");
            System.out.println("==================================================================");
            System.out.println("1. Tambah Data Siswa Baru");
            System.out.println("2. Tampilkan Seluruh Data & Status Absensi");
            System.out.println("3. Cari Data Siswa");
            System.out.println("4. Keluar Program");
            System.out.print("Pilih Menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    if (jumlahSiswa >= daftarSiswa.length) {
                        System.out.println("Kapasitas penyimpanan data siswa sudah penuh!");
                        break;
                    }

                    System.out.println("\n--- Pilih Jenis Siswa ---");
                    System.out.println("1. Siswa Reguler");
                    System.out.println("2. Siswa Beasiswa");
                    System.out.println("3. Siswa Internasional");
                    System.out.print("Pilihan (1-3): ");
                    int jenis = scanner.nextInt();
                    scanner.nextLine();

                    if (jenis < 1 || jenis > 3) {
                        System.out.println("Pilihan jenis siswa tidak valid!");
                        break;
                    }

                    System.out.print("Masukkan Nama Siswa  : ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan NIM        : ");
                    int nim = scanner.nextInt();

                    int hadir;
                    do {
                        System.out.print("Masukkan Jumlah Hadir (0-16) : ");
                        hadir = scanner.nextInt();
                        if (hadir < 0 || hadir > Siswa.TOTAL_PERTEMUAN) {
                            System.out.println("Wrong: Kehadiran harus 0 - 16!");
                        }
                    } while (hadir < 0 || hadir > Siswa.TOTAL_PERTEMUAN);

                    scanner.nextLine();

                    if (jenis == 1) {
                        System.out.print("Masukkan Mata Kuliah : ");
                        String mk = scanner.nextLine();
                        daftarSiswa[jumlahSiswa++] = new SiswaReguler(nama, nim, hadir, mk);
                    } else if (jenis == 2) {
                        System.out.print("Masukkan Mata Kuliah : ");
                        String mk = scanner.nextLine();
                        daftarSiswa[jumlahSiswa++] = new SiswaBeasiswa(nama, nim, hadir, mk);
                    } else if (jenis == 3) {
                        System.out.print("Masukkan Mata Kuliah : ");
                        String mk = scanner.nextLine();
                        daftarSiswa[jumlahSiswa++] = new SiswaInternational(nama, nim, hadir, mk);
                    }

                    System.out.println("-> SUKSES: Data siswa berhasil disimpan.");
                    break;

                case 2:
                    System.out.println("\n--- DAFTAR PRESENSI & STATUS ABSENSI ---");
                    if (jumlahSiswa == 0) {
                        System.out.println("Belum ada data siswa.");
                    } else {
                        for (int i = 0; i < jumlahSiswa; i++) {
                            System.out.printf("%d. ", (i + 1));
                            daftarSiswa[i].tampilkanInfo(); 
                            prosesAbsensi(daftarSiswa[i]); 
                        }
                        System.out.println("\n* Total Siswa Terdaftar: " + Siswa.totalSiswaBerhasilDibuat);
                    }
                    break;

                case 3:
                    System.out.println("\n--- Fitur Cari Data (Method Overloading) ---");
                    if (jumlahSiswa == 0) {
                        System.out.println("Belum ada data siswa yang tersimpan.");
                        break;
                    }

                    System.out.println("1. Cari berdasarkan Nama (Parameter String)");
                    System.out.println("2. Cari berdasarkan NIM (Parameter Integer)");
                    System.out.print("Pilih Mode (1/2): ");
                    int mode = scanner.nextInt();
                    scanner.nextLine();

                    if (mode == 1) {
                        System.out.print("Masukkan Nama: ");
                        String namaCari = scanner.nextLine();
                        cariSiswa(namaCari, daftarSiswa, jumlahSiswa);
                    } else if (mode == 2) {
                        System.out.print("Masukkan NIM: ");
                        int nimCari = scanner.nextInt();
                        cariSiswa(nimCari, daftarSiswa, jumlahSiswa);
                    } else {
                        System.out.println("Pilihan mode tidak valid.");
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih! Program selesai.");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan menu tidak valid!");
                    break;
            }
        }
        scanner.close();
    }
}