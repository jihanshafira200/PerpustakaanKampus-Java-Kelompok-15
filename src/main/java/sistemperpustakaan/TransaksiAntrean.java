
package sistemperpustakaan;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Scanner;

public class TransaksiAntrean {

    static Queue<String> antrean = new LinkedList<>();

    static class Transaksi {
        String nama;
        String judul;
        int kategori;
        int hari;

        Transaksi(String nama, String judul,
                  int kategori, int hari) {
            this.nama = nama;
            this.judul = judul;
            this.kategori = kategori;
            this.hari = hari;
        }

        @Override
        public String toString() {
            return nama + " meminjam " + judul
                    + " ("
                    + DataPeminjaman.kategoriBuku[kategori - 1]
                    + ", "
                    + DataPeminjaman.hari[hari - 1]
                    + ")";
        }
    }

    static Stack<Transaksi> riwayat = new Stack<>();

    public static void tambahAntrean(Scanner input) {
        System.out.println("\n=== TAMBAH ANTREAN ===");

        System.out.print("Masukkan nama mahasiswa: ");
        String nama = input.nextLine().trim();

        if (nama.isEmpty()) {
            System.out.println("ERROR: Nama tidak boleh kosong!");
            return;
        }

        antrean.offer(nama);

        System.out.println(nama + " berhasil masuk antrean.");
        System.out.println("Jumlah antrean: " + antrean.size());
    }

    public static void tampilAntrean() {
        System.out.println("\n=== DAFTAR ANTREAN ===");

        if (antrean.isEmpty()) {
            System.out.println("Antrean masih kosong.");
            return;
        }

        int nomor = 1;

        for (String nama : antrean) {
            System.out.println(nomor + ". " + nama);
            nomor++;
        }
    }

    public static void layaniAntrean(Scanner input) {
        System.out.println("\n=== LAYANI ANTREAN ===");

        if (antrean.isEmpty()) {
            System.out.println("ERROR: Antrean kosong!");
            return;
        }

        String nama = antrean.peek();

        System.out.println("Mahasiswa: " + nama);

        System.out.print("Masukkan judul buku: ");
        String judul = input.nextLine().trim();

        if (judul.isEmpty()) {
            System.out.println("ERROR: Judul tidak boleh kosong!");
            return;
        }

        try {
            DataPeminjaman.tampilKategori();

            System.out.print("Pilih kategori (1-4): ");
            int kategori = Integer.parseInt(
                    input.nextLine().trim());

            if (kategori < 1 ||
                kategori > DataPeminjaman.kategoriBuku.length) {
                System.out.println("ERROR: Kategori tidak valid!");
                return;
            }

            System.out.println("\n=== PILIH HARI ===");
            for (int i = 0; i < DataPeminjaman.hari.length; i++) {
                System.out.println((i + 1) + ". "
                        + DataPeminjaman.hari[i]);
            }

            System.out.print("Pilih hari (1-7): ");
            int hari = Integer.parseInt(
                    input.nextLine().trim());

            if (hari < 1 || hari > DataPeminjaman.hari.length) {
                System.out.println("ERROR: Hari tidak valid!");
                return;
            }

            if (!ManajemenBuku.pinjamBuku(judul)) {
                System.out.println("Peminjaman gagal!");
                return;
            }

            Transaksi transaksi =
                    new Transaksi(nama, judul, kategori, hari);

            riwayat.push(transaksi);
            DataPeminjaman.catatPeminjaman(kategori, hari);
            antrean.poll();

            System.out.println("\nPEMINJAMAN BERHASIL!");
            System.out.println("Mahasiswa : " + nama);
            System.out.println("Judul Buku: " + judul);
            System.out.println("Kategori  : "
                    + DataPeminjaman.kategoriBuku[kategori - 1]);
            System.out.println("Hari      : "
                    + DataPeminjaman.hari[hari - 1]);

        } catch (NumberFormatException e) {
            System.out.println(
                    "ERROR: Input harus berupa angka bulat!");
        }
    }

    public static void tampilRiwayat() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");

        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        for (int i = riwayat.size() - 1; i >= 0; i--) {
            System.out.println((riwayat.size() - i)
                    + ". " + riwayat.get(i));
        }
    }

    public static void transaksiTerakhir() {
        System.out.println("\n=== TRANSAKSI TERAKHIR ===");

        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        System.out.println(riwayat.peek());
    }

    public static void batalkanTransaksi() {
        System.out.println("\n=== BATALKAN TRANSAKSI ===");

        if (riwayat.isEmpty()) {
            System.out.println("ERROR: Tidak ada transaksi!");
            return;
        }

        Transaksi transaksi = riwayat.pop();

        ManajemenBuku.kembalikanBuku(transaksi.judul);

        DataPeminjaman.batalkanPeminjaman(
                transaksi.kategori, transaksi.hari);

        System.out.println("\nTransaksi berhasil dibatalkan!");
        System.out.println("Mahasiswa : " + transaksi.nama);
        System.out.println("Judul Buku: " + transaksi.judul);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan = -1;

        do {
            System.out.println("\n=== MENU TRANSAKSI ===");
            System.out.println("1. Tambah Antrean");
            System.out.println("2. Tampilkan Antrean");
            System.out.println("3. Layani Antrean");
            System.out.println("4. Tampilkan Riwayat");
            System.out.println("5. Lihat Transaksi Terakhir");
            System.out.println("6. Batalkan Transaksi");
            System.out.println("0. Keluar");

            System.out.print("Pilih menu: ");

            try {
                pilihan = Integer.parseInt(
                        input.nextLine().trim());

                switch (pilihan) {
                    case 1:
                        tambahAntrean(input);
                        break;
                    case 2:
                        tampilAntrean();
                        break;
                    case 3:
                        layaniAntrean(input);
                        break;
                    case 4:
                        tampilRiwayat();
                        break;
                    case 5:
                        transaksiTerakhir();
                        break;
                    case 6:
                        batalkanTransaksi();
                        break;
                    case 0:
                        System.out.println("Keluar.");
                        break;
                    default:
                        System.out.println("Menu tidak tersedia!");
                }

            } catch (NumberFormatException e) {
                System.out.println("ERROR: Input harus angka!");
            }

        } while (pilihan != 0);

        input.close();
    }
}
