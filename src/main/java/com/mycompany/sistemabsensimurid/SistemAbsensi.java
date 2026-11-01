    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabsensimurid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
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

    public static void cariSiswa(String nama, ArrayList<Siswa> daftar) throws DataTidakDitemukanException {
        System.out.println("\n--- Hasil Pencarian Nama (Tipe Parameter: String): '" + nama + "' ---");
        boolean ditemukan = false;
        for (int i = 0; i < daftar.size(); i++) {
            Siswa siswa = daftar.get(i);
            if (siswa.getNama().equalsIgnoreCase(nama)) {
                siswa.tampilkanInfo();
                prosesAbsensi(siswa);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new DataTidakDitemukanException("Data siswa dengan nama '" + nama + "' tidak ditemukan.");
        }
    }

    public static void cariSiswa(int nim, ArrayList<Siswa> daftar) throws DataTidakDitemukanException {
        System.out.println("\n--- Hasil Pencarian NIM (Tipe Parameter: Integer): " + nim + " ---");
        boolean ditemukan = false;
        for (int i = 0; i < daftar.size(); i++) {
            Siswa siswa = daftar.get(i);
            if (siswa.getNim() == nim) {
                siswa.tampilkanInfo();
                prosesAbsensi(siswa);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new DataTidakDitemukanException("Data siswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    public static Siswa bacaDataSiswa(Scanner scanner) throws DataTidakValidException {
        System.out.println("\n--- Pilih Jenis Siswa ---");
        System.out.println("1. Siswa Reguler");
        System.out.println("2. Siswa Beasiswa");
        System.out.println("3. Siswa Internasional");
        System.out.print("Pilihan (1-3): ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        if (jenis < 1 || jenis > 3) {
            throw new DataTidakValidException("Pilihan jenis siswa harus 1 - 3!");
        }

        System.out.print("Masukkan Nama Siswa  : ");
        String nama = scanner.nextLine();
        if (nama.trim().isEmpty()) {
            throw new DataTidakValidException("Nama siswa tidak boleh kosong!");
        }

        System.out.print("Masukkan NIM         : ");
        int nim = scanner.nextInt();
        scanner.nextLine();
        if (nim <= 0) {
            throw new DataTidakValidException("NIM harus berupa angka positif!");
        }

        System.out.print("Masukkan Jumlah Hadir (0-16) : ");
        int hadir = scanner.nextInt();
        scanner.nextLine();
        if (hadir < 0 || hadir > Siswa.TOTAL_PERTEMUAN) {
            throw new DataTidakValidException("Kehadiran harus 0 - 16!");
        }

        System.out.print("Masukkan Mata Kuliah : ");
        String mk = scanner.nextLine();
        if (mk.trim().isEmpty()) {
            throw new DataTidakValidException("Mata kuliah tidak boleh kosong!");
        }

        if (jenis == 1) {
            return new SiswaReguler(nama, nim, hadir, mk);
        } else if (jenis == 2) {
            return new SiswaBeasiswa(nama, nim, hadir, mk);
        } else {
            return new SiswaInternational(nama, nim, hadir, mk);
        }
    }

    public static void tambahKeMap(HashMap<Integer, Siswa> peta, Siswa siswa) throws DataTidakValidException {
        if (peta.containsKey(siswa.getNim())) {
            throw new DataTidakValidException("NIM " + siswa.getNim() + " sudah ada di HashMap, key harus unik!");
        }
        peta.put(siswa.getNim(), siswa);
        System.out.println("-> SUKSES: Data " + siswa.getNama() + " disimpan ke HashMap dengan key NIM " + siswa.getNim() + ".");
    }

    public static void cariDiMap(HashMap<Integer, Siswa> peta, int nim) throws DataTidakDitemukanException {
        System.out.println("\n--- Hasil Pencarian HashMap (Key NIM): " + nim + " ---");
        if (!peta.containsKey(nim)) {
            throw new DataTidakDitemukanException("Key NIM " + nim + " tidak ditemukan di HashMap.");
        }
        Siswa siswa = peta.get(nim);
        siswa.tampilkanInfo();
        prosesAbsensi(siswa);
    }

    public static void tampilkanSemuaMap(HashMap<Integer, Siswa> peta) {
        System.out.println("\n--- DATA HASHMAP (Key = NIM, Value = Siswa) ---");
        for (Integer nim : peta.keySet()) {
            System.out.printf("Key %d -> ", nim);
            Siswa siswa = peta.get(nim);
            siswa.tampilkanInfo();
            prosesAbsensi(siswa);
        }
        System.out.println("\n* Total Data di HashMap : " + peta.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Siswa> daftarSiswa = new ArrayList<>();
        HashMap<Integer, Siswa> mapSiswa = new HashMap<>();

        daftarSiswa.add(new SiswaReguler("Andi Pratama", 1001, 14, "Struktur Data"));
        daftarSiswa.add(new SiswaBeasiswa("Siti Rahma", 1002, 13, "Matematika Diskrit"));
        daftarSiswa.add(new SiswaInternational("John Smith", 1003, 10, "Elektronika Digital"));
        daftarSiswa.add(new SiswaBeasiswa("Budi Santoso", 1004, 16, "Pemrograman Java"));
        daftarSiswa.add(new SiswaReguler("Dewi Lestari", 1005, 9, "Basis Data"));

        mapSiswa.put(2001, new SiswaReguler("Rina Marlina", 2001, 15, "Basis Data"));
        mapSiswa.put(2002, new SiswaBeasiswa("Maya Putri", 2002, 15, "Algoritma"));
        mapSiswa.put(2003, new SiswaInternational("Kevin Tan", 2003, 11, "Jaringan Komputer"));

        boolean isRunning = true;

        while (isRunning) {
            try {
                System.out.println("\n==================================================================");
                System.out.println("                   SISTEM ABSENSI AKADEMIK                        ");
                System.out.println("==================================================================");
                System.out.println("1. Tambah Data Siswa Baru (ArrayList)");
                System.out.println("2. Tampilkan Seluruh Data & Status Absensi (ArrayList)");
                System.out.println("3. Cari Data Siswa (ArrayList)");
                System.out.println("4. Tambah Data Siswa ke HashMap (Key = NIM)");
                System.out.println("5. Cari Data Siswa di HashMap (Key = NIM)");
                System.out.println("6. Tampilkan Seluruh Data HashMap");
                System.out.println("7. Keluar Program");
                System.out.print("Pilih Menu (1-7): ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        daftarSiswa.add(bacaDataSiswa(scanner));
                        System.out.println("-> SUKSES: Data siswa berhasil disimpan ke ArrayList.");
                        break;

                    case 2:
                        System.out.println("\n--- DAFTAR PRESENSI & STATUS ABSENSI (ArrayList) ---");
                        if (daftarSiswa.isEmpty()) {
                            System.out.println("Belum ada data siswa.");
                        } else {
                            for (int i = 0; i < daftarSiswa.size(); i++) {
                                System.out.printf("%d. ", (i + 1));
                                daftarSiswa.get(i).tampilkanInfo();
                                prosesAbsensi(daftarSiswa.get(i));
                            }
                            System.out.println("\n* Total Siswa di ArrayList : " + daftarSiswa.size());
                            System.out.println("* Total Objek Siswa Dibuat : " + Siswa.totalSiswaBerhasilDibuat);
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Fitur Cari Data (Method Overloading) ---");
                        if (daftarSiswa.isEmpty()) {
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
                            if (namaCari.trim().isEmpty()) {
                                throw new DataTidakValidException("Nama yang dicari tidak boleh kosong!");
                            }
                            cariSiswa(namaCari, daftarSiswa);
                        } else if (mode == 2) {
                            System.out.print("Masukkan NIM: ");
                            int nimCari = scanner.nextInt();
                            scanner.nextLine();
                            cariSiswa(nimCari, daftarSiswa);
                        } else {
                            throw new DataTidakValidException("Pilihan mode pencarian harus 1 atau 2!");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- Tambah Data ke HashMap (Key = NIM) ---");
                        Siswa siswaBaru = bacaDataSiswa(scanner);
                        tambahKeMap(mapSiswa, siswaBaru);
                        break;

                    case 5:
                        System.out.println("\n--- Cari Data di HashMap ---");
                        if (mapSiswa.isEmpty()) {
                            System.out.println("HashMap masih kosong.");
                            break;
                        }
                        System.out.print("Masukkan NIM (Key): ");
                        int nimKey = scanner.nextInt();
                        scanner.nextLine();
                        cariDiMap(mapSiswa, nimKey);
                        break;

                    case 6:
                        if (mapSiswa.isEmpty()) {
                            System.out.println("\nHashMap masih kosong.");
                        } else {
                            tampilkanSemuaMap(mapSiswa);
                        }
                        break;

                    case 7:
                        System.out.println("Terima kasih! Program selesai.");
                        isRunning = false;
                        break;

                    default:
                        throw new DataTidakValidException("Pilihan menu harus 1 - 7!");
                }
            } catch (InputMismatchException e) {
                System.out.println("[CATCH] Input salah! Yang dimasukkan harus berupa angka bulat, bukan huruf atau simbol.");
                scanner.nextLine();
            } catch (DataTidakValidException e) {
                System.out.println("[CATCH] Data tidak valid: " + e.getMessage());
            } catch (DataTidakDitemukanException e) {
                System.out.println("[CATCH] Pencarian gagal: " + e.getMessage());
            } finally {
                System.out.println("[FINALLY] Satu proses menu selesai, blok finally selalu dijalankan walau terjadi error.");
            }
        }
        scanner.close();
    }
}