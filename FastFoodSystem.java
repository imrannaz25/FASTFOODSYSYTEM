// BY imran nazari
// 14/12/2023 - 28/12/2023
// NTP PROJESİ
import java.util.*;

public class FastFoodSystem {
    private Map<Integer, MenuItem> menu;
    private List<Order> orders;
    private PaymentSystem paymentSystem;

    public FastFoodSystem() {
        menu = new HashMap<>();
        orders = new ArrayList<>();
        paymentSystem = new PaymentSystem();
    }

    // Her menü öğesi için benzersiz bir numara atamak için kullanılır
    public void addMenuItem(String name, double price) {
        int itemNumber = menu.size() + 1;
        MenuItem item = new MenuItem(name, price);
        menu.put(itemNumber, item);
    }

    // Mevcut menü öğelerini ekrana yazdırır
    public void displayMenu() {
        System.out.println("Menü:");
        for (Map.Entry<Integer, MenuItem> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName() + " - $" + entry.getValue().getPrice());
        }
    }

    // Yeni bir sipariş oluşturur
    public Order createOrder() {
        Order order = new Order();
        orders.add(order);
        return order;
    }

    // Belirli bir siparişin detaylarını ve toplam tutarını ekrana yazdırır
    // Kullanıcıya ödeme yapmak isteyip istemediğini sorar
    public void processOrder(Order order) {
        System.out.println("\nSipariş detayları:");
        for (MenuItem item : order.getItems()) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Toplam: $" + order.calculateTotal());

        System.out.print("Ödeme yapmak ister misiniz? (evet/hayır): ");
        String choice = new Scanner(System.in).nextLine().trim();

        if (choice.equalsIgnoreCase("evet")) {
            paymentSystem.processPayment(order);
        } else {
            System.out.println("Ödeme yapılmadan sipariş verildi. Teşekkür ederiz!");
        }
    }

    // Diğer sınıfların menüye erişimini sağlamak için
    public Map<Integer, MenuItem> getMenu() {
        return menu;
    }

    public static void main(String[] args) {
        FastFoodSystem fastFoodSystem = new FastFoodSystem();//input almak için
        Scanner scanner = new Scanner(System.in);
        //burada menüye yeni yiyecek türü ekleyebilirsiniz
        fastFoodSystem.addMenuItem("Kızarmış Tavuk",15.99);
        fastFoodSystem.addMenuItem("cheeseburger", 5.99);
        fastFoodSystem.addMenuItem("Patates Kızartması", 2.49);
        fastFoodSystem.addMenuItem("Peynirli Patates Kızartması",10.99);
        fastFoodSystem.addMenuItem("Soda", 1.99);


        while (true) {
            System.out.println("\n1. Menüyü Görüntüle");
            System.out.println("2. Sipariş Oluştur");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminizi girin: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    fastFoodSystem.displayMenu();
                    break;
                case 2:
                    Order order = fastFoodSystem.createOrder();
                    System.out.println("Sipariş için öğe numaralarını girin ('tamam' yazarak bitirin):");

                    int itemNumber;
                    do {
                        System.out.print("Öğe numarası: ");
                        if (scanner.hasNextInt()) {
                            itemNumber = scanner.nextInt();
                            if (fastFoodSystem.getMenu().containsKey(itemNumber)) {
                                MenuItem selectedItem = fastFoodSystem.getMenu().get(itemNumber);
                                order.addItem(selectedItem);
                            } else {
                                System.out.println("Geçersiz öğe numarası. Lütfen tekrar deneyin.");
                            }
                        } else {
                            System.out.println("Geçersiz giriş. Lütfen geçerli bir öğe numarası girin.");
                            scanner.next();
                            itemNumber = 0;
                        }
                    } while (itemNumber != 0);

                    fastFoodSystem.processOrder(order);
                    break;
                case 3:
                    System.out.println("Hızlı yemek sistemi kapatılıyor. Hoşça kal!");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçim. Lütfen geçerli bir seçenek girin.");
            }
        }
    }
}