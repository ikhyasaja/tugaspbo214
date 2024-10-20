import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Cashier cashier = new Cashier();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Tambah Barang");
            System.out.println("2. Tampilkan Daftar Item");
            System.out.println("3. Tampilkan Total");
            System.out.println("4. Proses Pembayaran");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan kode barang: ");
                    String addCode = scanner.next();
                    System.out.print("Masukkan nama barang: ");
                    String addName = scanner.next();
                    System.out.print("Masukkan harga barang: ");
                    double addPrice = scanner.nextDouble();
                    System.out.print("Masukkan jumlah barang: ");
                    int addQuantity = scanner.nextInt();
                    cashier.addItem(addCode, addName, addPrice, addQuantity);
                    break;
                case 2:
                    cashier.displayItems();
                    break;
                case 3:
                    cashier.displayTotal();
                    break;
                case 4:
                    cashier.processPayment(scanner);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem kasir kami!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        }
    }
}

class Cashier {
    private ArrayList<Item> items;

    public Cashier() {
        items = new ArrayList<>();
    }

    public void addItem(String code, String name, double price, int quantity) {
        items.add(new Item(code, name, price, quantity));
        System.out.println("Barang ditambahkan.");
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Daftar item kosong.");
            return;
        }

        System.out.println("Daftar Item:");
        System.out.println("Kode\tNama\tHarga\tJumlah\tSubtotal");
        for (Item item : items) {
            System.out.printf("%s\t%s\t%.2f\t%d\t%.2f%n",
                    item.getCode(), item.getName(), item.getPrice(),
                    item.getQuantity(), item.getSubtotal());
        }
    }

    public void displayTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getSubtotal();
        }
        System.out.printf("Total belanja: %.2f%n", total);
    }

    public void processPayment(Scanner scanner) {
        double total = 0.0;
        for (Item item : items) {
            total += item.getSubtotal();
        }

        System.out.printf("Total bayar: %.2f. Masukkan uang yang dibayar: ", total);
        double paidAmount = scanner.nextDouble();
        double change = paidAmount - total;

        if (change >= 0) {
            System.out.printf("Kembali: %.2f%n", change);
            items.clear(); // Kosongkan daftar setelah pembayaran
        } else {
            System.out.println("Uang yang dibayar tidak cukup.");
        }
    }
}

class Item {
    private String itemCode;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;

    public Item(String code, String name, double price, int quantity) {
        this.itemCode = code;
        this.itemName = name;
        this.itemPrice = price;
        this.itemQuantity = quantity;
    }

    public double getPrice() {
        return itemPrice;
    }

    public String getCode() {
        return itemCode;
    }

    public String getName() {
        return itemName;
    }

    public int getQuantity() {
        return itemQuantity;
    }

    public double getSubtotal() {
        return itemPrice * itemQuantity;
    }
}