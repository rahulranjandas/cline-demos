import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Create product catalog
        HashMap<Integer, Product> catalog = new HashMap<>();
        catalog.put(1, new Product(1, "Apple", 0.50, "Fruits"));
        catalog.put(2, new Product(2, "Banana", 0.30, "Fruits"));
        catalog.put(3, new Product(3, "Milk", 1.20, "Dairy"));
        catalog.put(4, new Product(4, "Bread", 2.00, "Bakery"));

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart(catalog);

        // Add items
        cart.addItem(1, 5); // 5 Apples
        cart.addItem(2, 3); // 3 Bananas
        cart.addItem(3, 2); // 2 Milk

        // Remove item
        cart.removeItem(2); // Remove Bananas

        // Apply discount (10%)
        cart.applyDiscount(0.10);

        // Print cart contents and total
        System.out.println("Cart contents:");
        for (CartItem item : cart.getCartItemsSnapshot()) {
            System.out.println(item);
        }
        System.out.printf("Current discount: %.2f%%\n", cart.getDiscount() * 100);
        System.out.printf("Total: $%.2f\n", cart.calculateTotal());

        // Show pretty cart view using toString
        System.out.println("\n--- Cart Summary ---");
        System.out.println(cart);
    }
}
