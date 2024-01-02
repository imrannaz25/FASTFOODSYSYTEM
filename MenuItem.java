public class MenuItem {
    // Özel sınıf değişkenleri
    private String name; // Ürün adı
    private double price; // Ürün fiyatı

    // Kurucu metod, MenuItem nesnesi oluştururken ad ve fiyat bilgilerini alır
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Ürün adını döndüren metot
    public String getName() {
        return name;
    }

    // Ürün fiyatını döndüren metot
    public double getPrice() {
        return price;
    }
}
