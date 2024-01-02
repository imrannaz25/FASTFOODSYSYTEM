import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 0; // Benzersiz sipariş numaraları oluşturmak için sayaç
    private int orderNumber; // Sipariş numarası
    private List<MenuItem> items; // Menü öğeleri listesi

    // Kurucu metod, her bir sipariş oluşturulduğunda bir sipariş numarası ve boş bir öğe listesi atanır
    public Order() {
        orderNumber = ++orderCounter;
        items = new ArrayList<>();
    }

    // Bir menü öğesi eklemek için metot
    public void addItem(MenuItem item) {
        items.add(item);
    }

    // Siparişe ait menü öğelerini döndüren metot
    public List<MenuItem> getItems() {
        return items;
    }

    // Siparişin toplam tutarını hesaplayan metot
    public double calculateTotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    // Sipariş numarasını döndüren metot
    public int getOrderNumber() {
        return orderNumber;
    }
}
