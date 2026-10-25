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
public class DataPeminjaman {

    static String[] kategoriBuku = {
        "Pemrograman",
        "Matematika",
        "Jaringan Komputer",
        "Basis Data"
    };

    static String[] hari = {
        "Senin", "Selasa", "Rabu", "Kamis",
        "Jumat", "Sabtu", "Minggu"
    };

    static int[][] jumlahPeminjaman = {
        {5, 3, 4, 6, 2, 1, 0},
        {2, 4, 3, 5, 1, 0, 0},
        {3, 2, 5, 4, 3, 1, 0},
        {4, 3, 2, 5, 4, 2, 0}
    };

    public static void tampilKategori() {
        System.out.println("\n=== DAFTAR KATEGORI BUKU ===");

        for (int i = 0; i < kategoriBuku.length; i++) {
            System.out.println((i + 1) + ". " + kategoriBuku[i]);
        }
    }

    public static void tampilPeminjaman() {
        System.out.println("\n=== DATA PEMINJAMAN MINGGUAN ===");

        System.out.printf("%-20s", "Kategori");
        for (String namaHari : hari) {
            System.out.printf("%-9s", namaHari);
        }
        System.out.println();

        for (int i = 0; i < kategoriBuku.length; i++) {
            System.out.printf("%-20s", kategoriBuku[i]);

            for (int j = 0; j < hari.length; j++) {
                System.out.printf("%-9d", jumlahPeminjaman[i][j]);
            }
            System.out.println();
        }
    }

    
        public static void ubahPeminjaman(Scanner input) {
        System.out.println("\n=== UBAH DATA PEMINJAMAN ===");

        try {
            tampilKategori();

            System.out.print("Pilih nomor kategori: ");
            int kategori = Integer.parseInt(
                    input.nextLine().trim());

            if (kategori < 1 || kategori > kategoriBuku.length) {
                System.out.println("ERROR: Kategori tidak valid!");
                return;
            }

            System.out.println("\n=== PILIH HARI ===");

            for (int i = 0; i < hari.length; i++) {
                System.out.println((i + 1) + ". " + hari[i]);
            }

            System.out.print("Pilih nomor hari: ");
            int nomorHari = Integer.parseInt(
                    input.nextLine().trim());

            if (nomorHari < 1 || nomorHari > hari.length) {
                System.out.println("ERROR: Hari tidak valid!");
                return;
            }

            System.out.print("Masukkan jumlah peminjaman baru: ");
            int jumlah = Integer.parseInt(
                    input.nextLine().trim());

            if (jumlah < 0) {
                System.out.println(
                    "ERROR: Jumlah tidak boleh negatif!");
                return;
            }

            jumlahPeminjaman[kategori - 1][nomorHari - 1] = jumlah;

            System.out.println(
                "Data peminjaman berhasil diperbarui!");

        } catch (NumberFormatException e) {
            System.out.println(
                "ERROR: Input harus berupa angka bulat!");
        }
    }


    public static void totalPeminjaman() {
        System.out.println("\n=== TOTAL PEMINJAMAN PER KATEGORI ===");

        for (int i = 0; i < kategoriBuku.length; i++) {
            int total = 0;

            for (int j = 0; j < hari.length; j++) {
                total += jumlahPeminjaman[i][j];
            }

            System.out.println(kategoriBuku[i] + ": " +
                    total + " peminjaman");
        }
    }
    
        public static void catatPeminjaman(int kategori, int nomorHari) {
         jumlahPeminjaman[kategori - 1][nomorHari - 1]++;
         System.out.println("Data peminjaman berhasil diperbarui.");
     }

        public static void batalkanPeminjaman(int kategori, int nomorHari) {
            if (jumlahPeminjaman[kategori - 1][nomorHari - 1] > 0) {
                jumlahPeminjaman[kategori - 1][nomorHari - 1]--;
            }
            System.out.println("Data peminjaman berhasil disesuaikan.");
        }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan = -1;
        do {
            System.out.println("\n=== MENU DATA PEMINJAMAN ===");
            System.out.println("1. Tampilkan Kategori");
            System.out.println("2. Tampilkan Data Mingguan");
            System.out.println("3. Ubah Jumlah Peminjaman");
            System.out.println("4. Total Peminjaman");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            if (!input.hasNextInt()) {
                System.out.println("Masukkan angka yang valid!");
                input.next();
                continue;
            }

            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    tampilKategori();
                    break;
                case 2:
                    tampilPeminjaman();
                    break;
                case 3:
                    ubahPeminjaman(input);
                    break;
                case 4:
                    totalPeminjaman();
                    break;
                case 0:
                    System.out.println("Keluar dari menu peminjaman.");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia!");
            }

        } while (pilihan != 0);

        input.close();
    }
}

