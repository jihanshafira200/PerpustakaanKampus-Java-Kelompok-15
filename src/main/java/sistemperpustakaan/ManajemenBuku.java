
package sistemperpustakaan;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Locale;

public class ManajemenBuku {

    static String[][][] lokasiBuku = new String[2][3][4];
    static ArrayList<String> daftarBuku = new ArrayList<>();
    static Set<String> bukuDipinjam = new HashSet<>();

    static {
        lokasiBuku[0][0][0] = "Java Dasar";
        lokasiBuku[0][0][1] = "Algoritma";
        lokasiBuku[0][1][0] = "Kalkulus";
        lokasiBuku[0][1][1] = "Aljabar";
        lokasiBuku[0][2][0] = "Jaringan Dasar";

        lokasiBuku[1][0][0] = "MySQL";
        lokasiBuku[1][0][1] = "SQL Dasar";
        lokasiBuku[1][1][0] = "Struktur Data";
        lokasiBuku[1][2][0] = "Sistem Operasi";

        for (int i = 0; i < lokasiBuku.length; i++) {
            for (int j = 0; j < lokasiBuku[i].length; j++) {
                for (int k = 0; k < lokasiBuku[i][j].length; k++) {

                    if (lokasiBuku[i][j][k] != null) {
                        daftarBuku.add(lokasiBuku[i][j][k]);
                    }
                }
            }
        }
    }

    private static String kunciBuku(String judul) {
        return judul.trim().toLowerCase(Locale.ROOT);
    }

    public static void tampilLokasi() {
        System.out.println("\n=== LOKASI PENYIMPANAN BUKU ===");

        for (int i = 0; i < lokasiBuku.length; i++) {
            System.out.println("\nLantai " + (i + 1));

            for (int j = 0; j < lokasiBuku[i].length; j++) {
                System.out.println("  Rak " + (j + 1));

                for (int k = 0; k < lokasiBuku[i][j].length; k++) {

                    String buku = lokasiBuku[i][j][k];

                    if (buku == null) {
                        System.out.println("    Posisi " + (k + 1)
                                + ": [Kosong]");
                    } else {
                        String status = bukuDipinjam.contains(
                                kunciBuku(buku))
                                ? "Sedang Dipinjam"
                                : "Tersedia";

                        System.out.println("    Posisi " + (k + 1)
                                + ": " + buku
                                + " [" + status + "]");
                    }
                }
            }
        }
    }

    public static void tambahBuku(Scanner input) {
        System.out.println("\n=== TAMBAH BUKU ===");

        System.out.print("Masukkan judul buku: ");
        String judul = input.nextLine().trim();

        if (judul.isEmpty()) {
            System.out.println("ERROR: Judul tidak boleh kosong!");
            return;
        }

        for (String buku : daftarBuku) {
            if (buku.equalsIgnoreCase(judul)) {
                System.out.println("ERROR: Buku sudah terdaftar!");
                return;
            }
        }

        try {
            System.out.println("\n=== PILIH LANTAI ===");
            System.out.println("1. Lantai 1");
            System.out.println("2. Lantai 2");
            System.out.print("Pilih lantai (1-2): ");

            int lantai = Integer.parseInt(
                    input.nextLine().trim());

            if (lantai < 1 || lantai > lokasiBuku.length) {
                System.out.println("ERROR: Lantai tidak valid!");
                return;
            }

            System.out.println("\n=== PILIH RAK ===");

            for (int i = 0; i < lokasiBuku[lantai - 1].length; i++) {
                System.out.println((i + 1) + ". Rak " + (i + 1));
            }

            System.out.print("Pilih rak: ");

            int rak = Integer.parseInt(
                    input.nextLine().trim());

            if (rak < 1 || rak > lokasiBuku[lantai - 1].length) {
                System.out.println("ERROR: Rak tidak valid!");
                return;
            }

            System.out.println("\n=== PILIH POSISI ===");

            for (int i = 0;
                    i < lokasiBuku[lantai - 1][rak - 1].length;
                    i++) {

                String isi = lokasiBuku[lantai - 1][rak - 1][i];

                System.out.println((i + 1) + ". Posisi "
                        + (i + 1) + " - "
                        + (isi == null ? "Kosong" : "Terisi: " + isi));
            }

            System.out.print("Pilih posisi: ");

            int posisi = Integer.parseInt(
                    input.nextLine().trim());

            if (posisi < 1 ||
                    posisi > lokasiBuku[lantai - 1][rak - 1].length) {

                System.out.println("ERROR: Posisi tidak valid!");
                return;
            }

            if (lokasiBuku[lantai - 1][rak - 1][posisi - 1]
                    != null) {

                System.out.println(
                        "ERROR: Posisi sudah terisi buku!");
                return;
            }

            lokasiBuku[lantai - 1][rak - 1][posisi - 1] = judul;
            daftarBuku.add(judul);

            System.out.println("\nBuku berhasil ditambahkan!");
            System.out.println("Judul  : " + judul);
            System.out.println("Lantai : " + lantai);
            System.out.println("Rak    : " + rak);
            System.out.println("Posisi : " + posisi);
            System.out.println("Status : Tersedia");

        } catch (NumberFormatException e) {
            System.out.println(
                    "ERROR: Input harus berupa angka bulat!");
        }
    }

    public static void tampilBuku() {
        System.out.println("\n=== DAFTAR KOLEKSI BUKU ===");

        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada koleksi buku.");
            return;
        }

        for (int i = 0; i < daftarBuku.size(); i++) {

            String buku = daftarBuku.get(i);

            String status = bukuDipinjam.contains(kunciBuku(buku))
                    ? "Sedang Dipinjam"
                    : "Tersedia";

            System.out.println((i + 1) + ". " + buku
                    + " [" + status + "]");
        }

        System.out.println("Total koleksi: " + daftarBuku.size());
    }

    public static void cariBuku(Scanner input) {
        System.out.println("\n=== CARI BUKU ===");

        System.out.print("Masukkan judul buku: ");
        String judul = input.nextLine().trim();

        if (judul.isEmpty()) {
            System.out.println("ERROR: Judul tidak boleh kosong!");
            return;
        }

        for (int i = 0; i < lokasiBuku.length; i++) {
            for (int j = 0; j < lokasiBuku[i].length; j++) {
                for (int k = 0; k < lokasiBuku[i][j].length; k++) {

                    String buku = lokasiBuku[i][j][k];

                    if (buku != null &&
                            buku.equalsIgnoreCase(judul)) {

                        String status = bukuDipinjam.contains(
                                kunciBuku(buku))
                                ? "Sedang Dipinjam"
                                : "Tersedia";

                        System.out.println("\nBuku ditemukan!");
                        System.out.println("Judul  : " + buku);
                        System.out.println("Lantai : " + (i + 1));
                        System.out.println("Rak    : " + (j + 1));
                        System.out.println("Posisi : " + (k + 1));
                        System.out.println("Status : " + status);
                        return;
                    }
                }
            }
        }

        System.out.println("Buku tidak ditemukan!");
    }

    public static void hapusBuku(Scanner input) {
        System.out.println("\n=== HAPUS BUKU ===");

        System.out.print("Masukkan judul buku: ");
        String judul = input.nextLine().trim();

        if (judul.isEmpty()) {
            System.out.println("ERROR: Judul tidak boleh kosong!");
            return;
        }

        for (int i = 0; i < lokasiBuku.length; i++) {
            for (int j = 0; j < lokasiBuku[i].length; j++) {
                for (int k = 0; k < lokasiBuku[i][j].length; k++) {

                    String buku = lokasiBuku[i][j][k];

                    if (buku != null &&
                            buku.equalsIgnoreCase(judul)) {

                        // Mencegah penghapusan buku dipinjam
                        if (bukuDipinjam.contains(
                                kunciBuku(buku))) {

                            System.out.println(
                                "ERROR: Buku sedang dipinjam!");
                            System.out.println(
                                "Buku tidak dapat dihapus.");
                            return;
                        }

                        lokasiBuku[i][j][k] = null;
                        daftarBuku.remove(buku);

                        System.out.println(
                                "Buku berhasil dihapus!");
                        return;
                    }
                }
            }
        }

        System.out.println("Buku tidak ditemukan!");
    }

    public static boolean pinjamBuku(String judul) {

        if (judul == null || judul.trim().isEmpty()) {
            System.out.println("ERROR: Judul buku tidak valid!");
            return false;
        }

        String kunci = kunciBuku(judul);
        boolean ditemukan = false;

        // Memeriksa apakah buku ada di koleksi
        for (String buku : daftarBuku) {
            if (kunciBuku(buku).equals(kunci)) {
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("ERROR: Buku tidak ditemukan!");
            return false;
        }

        if (bukuDipinjam.contains(kunci)) {
            System.out.println(
                    "ERROR: Buku sedang dipinjam mahasiswa lain!");
            return false;
        }

        bukuDipinjam.add(kunci);

        System.out.println(
                "Buku tersedia dan berhasil dipinjam.");
        return true;
    }

    public static void kembalikanBuku(String judul) {

        if (judul == null || judul.trim().isEmpty()) {
            System.out.println("ERROR: Judul buku tidak valid!");
            return;
        }

        String kunci = kunciBuku(judul);

        if (bukuDipinjam.remove(kunci)) {
            System.out.println(
                    "Status buku berhasil dikembalikan menjadi tersedia.");
        } else {
            System.out.println(
                    "Buku tidak sedang dalam status dipinjam.");
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int pilihan = -1;

        do {
            System.out.println("\n=== MENU MANAJEMEN BUKU ===");
            System.out.println("1. Tampilkan Lokasi Buku");
            System.out.println("2. Tambah Buku");
            System.out.println("3. Tampilkan Daftar Buku");
            System.out.println("4. Cari Buku");
            System.out.println("5. Hapus Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            try {
                pilihan = Integer.parseInt(
                        input.nextLine().trim());

                switch (pilihan) {
                    case 1:
                        tampilLokasi();
                        break;

                    case 2:
                        tambahBuku(input);
                        break;

                    case 3:
                        tampilBuku();
                        break;

                    case 4:
                        cariBuku(input);
                        break;

                    case 5:
                        hapusBuku(input);
                        break;

                    case 0:
                        System.out.println(
                                "Keluar dari manajemen buku.");
                        break;

                    default:
                        System.out.println(
                                "ERROR: Menu tidak tersedia!");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "ERROR: Input harus berupa angka bulat!");
            }

        } while (pilihan != 0);

        input.close();
    }
}
