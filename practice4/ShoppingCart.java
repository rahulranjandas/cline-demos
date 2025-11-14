import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    private final HashMap<Integer, Product> catalog;
    private final List<CartItem> items;
    private double discount; // as fraction (0.1 for 10%)
    private final Object lock = new Object();

    public ShoppingCart(HashMap<Integer, Product> catalog) {
        this.catalog = catalog;
        this.items = new ArrayList<>();
        this.discount = 0.0;
    }

    public void addItem(int productId, int quantity) {
        synchronized (lock) {
            Product product = catalog.get(productId);
            if (product == null) {
                throw new IllegalArgumentException("Product with id " + productId + " not found in catalog.");
            }
            for (CartItem item : items) {
                if (item.getProduct().getId() == productId) {
                    item.setQuantity(item.getQuantity() + quantity);
                    return;
                }
            }
            items.add(new CartItem(product, quantity));
        }
    }

    public void removeItem(int productId) {
        synchronized (lock) {
            items.removeIf(item -> item.getProduct().getId() == productId);
        }
    }

    public void applyDiscount(double discount) {
        synchronized (lock) {
            if (discount < 0.0 || discount > 1.0) {
                throw new IllegalArgumentException("Discount must be a fraction between 0.0 and 1.0");
            }
            this.discount = discount;
        }
    }

    public double calculateTotal() {
        synchronized (lock) {
            double total = 0.0;
            for (CartItem item : items) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
            total = total * (1.0 - discount);
            return total;
        }
    }

    public List<CartItem> getCartItemsSnapshot() {
        synchronized (lock) {
            return new ArrayList<>(items);
        }
    }

    public double getDiscount() {
        synchronized (lock) {
            return discount;
        }
    }

    @Override
    public String toString() {
        synchronized (lock) {
            StringBuilder sb = new StringBuilder();
            sb.append("ShoppingCart:\n");
            for (CartItem item : items) {
                sb.append(item).append("\n");
            }
            sb.append("Current discount: ").append(discount * 100).append("%\n");
            sb.append("Total: ").append(calculateTotal()).append("\n");
            return sb.toString();
        }
    }
}
