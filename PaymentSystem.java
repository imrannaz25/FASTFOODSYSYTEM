import java.util.Scanner;

public class PaymentSystem {
    // Sipariş ödemesini işleyen metot
    public void processPayment(Order order) {
        double totalAmount = order.calculateTotal();
        System.out.println("Toplam tutar: $" + totalAmount);

        System.out.println("Ödeme yöntemini seçiniz:");
        System.out.println("1. Nakit");
        System.out.println("2. Kart");
        System.out.print("Seçiminizi giriniz: ");

        Scanner scanner = new Scanner(System.in);
        int paymentMethod = scanner.nextInt();
        scanner.nextLine(); // Yeni satır karakterini tüket

        switch (paymentMethod) {
            case 1:
                processCashPayment(order, totalAmount);
                break;
            case 2:
                processCardPayment(order, totalAmount);
                break;
            default:
                System.out.println("Geçersiz ödeme yöntemi. Ödeme başarısız.");
        }
    }

    // Nakit ödeme işlemini gerçekleştiren özel metot
    private void processCashPayment(Order order, double totalAmount) {
        System.out.print("Nakit miktarını giriniz: $");
        double cashAmount = new Scanner(System.in).nextDouble();

        if (cashAmount >= totalAmount) {
            double change = cashAmount - totalAmount;
            System.out.println("Ödeme başarılı! Para üstü: $" + change);
            generateReceipt(order, "Nakit", totalAmount);
        } else {
            System.out.println("Yetersiz nakit. Ödeme başarısız.");
        }
    }

    // Kart ödeme işlemini gerçekleştiren özel metot
    private void processCardPayment(Order order, double totalAmount) {
        System.out.print("Kart numarasını giriniz: ");
        String cardNumber = new Scanner(System.in).nextLine().trim();

        // Gerekirse daha fazla kart işleme mantığı ekleyin

        System.out.println("Kart ödemesi başarılı!");
        generateReceipt(order, "Kart", totalAmount);
    }

    // Fiş oluşturan özel metot
    private void generateReceipt(Order order, String paymentMethod, double totalAmount) {
        System.out.println("\nFiş:");
        System.out.println("Sipariş Numarası: " + order.getOrderNumber());
        System.out.println("Ödeme Yöntemi: " + paymentMethod);
        for (MenuItem item : order.getItems()) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Toplam: $" + totalAmount);
        System.out.println("Satın alma işleminiz için teşekkür ederiz!");
    }
}
