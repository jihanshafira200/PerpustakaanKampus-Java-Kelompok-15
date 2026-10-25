/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemperpustakaan;
import java.util.Scanner;
/**
 *
 * @author LENOVO
 */
public class MainPerpustakaan {

        public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan = -1;

        // TAMPILAN AWAL PROGRAM
        System.out.println("==================================");
        System.out.println(" SISTEM MANAJEMEN PERPUSTAKAAN");
        System.out.println("==================================");

        // INPUT NAMA PETUGAS
        String nama;

        do {
            System.out.print("Masukkan nama petugas: ");
            nama = input.nextLine().trim();

            if (nama.isEmpty()) {
                System.out.println(
                    "ERROR: Nama petugas tidak boleh kosong!");
            }

        } while (nama.isEmpty());

        System.out.println("\nSelamat datang, " + nama + "!");
        System.out.println("Login sebagai Petugas Perpustakaan.");

        // MENU UTAMA
        do {
            System.out.println("\n==================================");
            System.out.println(" MENU UTAMA PERPUSTAKAAN KAMPUS");
            System.out.println("==================================");
            System.out.println("1. Tampilkan Kategori Buku");
            System.out.println("2. Data Peminjaman Mingguan");
            System.out.println("3. Ubah Data Peminjaman");
            System.out.println("4. Total Peminjaman");
            System.out.println("5. Tampilkan Lokasi Buku");
            System.out.println("6. Tambah Buku");
            System.out.println("7. Tampilkan Koleksi Buku");
            System.out.println("8. Cari Buku");
            System.out.println("9. Hapus Buku");
            System.out.println("10. Tambah Antrean Mahasiswa");
            System.out.println("11. Tampilkan Antrean");
            System.out.println("12. Layani Antrean / Pinjam Buku");
            System.out.println("13. Riwayat Transaksi");
            System.out.println("14. Lihat Transaksi Terakhir");
            System.out.println("15. Batalkan Transaksi Terakhir");
            System.out.println("0. Keluar");
            System.out.println("==================================");

            System.out.print("Pilih menu (0-15): ");

            try {
                pilihan = Integer.parseInt(
                        input.nextLine().trim());

                switch (pilihan) {

                    case 1:
                        DataPeminjaman.tampilKategori();
                        break;

                    case 2:
                        DataPeminjaman.tampilPeminjaman();
                        break;

                    case 3:
                        DataPeminjaman.ubahPeminjaman(input);
                        break;

                    case 4:
                        DataPeminjaman.totalPeminjaman();
                        break;

                    case 5:
                        ManajemenBuku.tampilLokasi();
                        break;

                    case 6:
                        ManajemenBuku.tambahBuku(input);
                        break;

                    case 7:
                        ManajemenBuku.tampilBuku();
                        break;

                    case 8:
                        ManajemenBuku.cariBuku(input);
                        break;

                    case 9:
                        ManajemenBuku.hapusBuku(input);
                        break;

                    case 10:
                        TransaksiAntrean.tambahAntrean(input);
                        break;

                    case 11:
                        TransaksiAntrean.tampilAntrean();
                        break;

                    case 12:
                        TransaksiAntrean.layaniAntrean(input);
                        break;

                    case 13:
                        TransaksiAntrean.tampilRiwayat();
                        break;

                    case 14:
                        TransaksiAntrean.transaksiTerakhir();
                        break;

                    case 15:
                        TransaksiAntrean.batalkanTransaksi();
                        break;

                    case 0:
                        System.out.println(
                            "\nTerima kasih, " + nama + "!");
                        System.out.println(
                            "Program perpustakaan ditutup.");
                        break;

                    default:
                        System.out.println(
                            "ERROR: Pilihan menu tidak tersedia!");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "ERROR: Pilihan harus berupa angka bulat!");
            }

        } while (pilihan != 0);

        input.close();
    }
}
