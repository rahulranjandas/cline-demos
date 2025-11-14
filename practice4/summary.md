# Shopping Cart System Documentation

## Overview

This Java shopping cart system demonstrates a simple e-commerce cart using Java Collections with thread-safe operations. It supports:
- Adding/removing products to/from the cart
- Applying discounts
- Calculating cart totals

## Core Components

### 1. `Product`
- Represents a product with `id`, `name`, `price`, and `category`.
- Used as items available in the catalog and within the cart.

### 2. `CartItem`
- Contains a `Product` and a `quantity`, representing how many units of a product are added to the cart.

### 3. `ShoppingCart`
- Holds:
  - `HashMap<Integer, Product>`: Catalog of all available products, keyed by id.
  - `ArrayList<CartItem>`: Current items and their quantities in the cart.
  - `double discount`: Discount applied (as a fraction; e.g. 0.1 for 10%).
- Core methods (all thread-safe):
  - `addItem(int productId, int quantity)`: Adds given quantity of the product to the cart.
  - `removeItem(int productId)`: Removes all quantities of the product from the cart.
  - `applyDiscount(double discount)`: Applies a percentage discount (0.0 to 1.0).
  - `calculateTotal()`: Returns total price (after discounts) of current cart.
- Thread-safety: All mutating methods on the cart are synchronized using a private lock object.

### 4. `Main`
- Demonstrates building a product catalog, performing cart operations, applying discount, and outputting cart contents/total.

## Example Usage

```java
// Create catalog
HashMap<Integer, Product> catalog = new HashMap<>();
catalog.put(1, new Product(1, "Apple", 0.50, "Fruits"));
catalog.put(2, new Product(2, "Milk", 1.20, "Dairy"));

// Create cart
ShoppingCart cart = new ShoppingCart(catalog);

// Add 5 apples, 2 milk
cart.addItem(1, 5);
cart.addItem(2, 2);

// Remove milk
cart.removeItem(2);

// Apply 10% discount
cart.applyDiscount(0.10);

// Print total
System.out.println("Cart total: $" + cart.calculateTotal());
```

## Thread Safety

- All modifications to the cart (add, remove, discount) are performed within synchronized blocks using a dedicated lock object, ensuring safe usage in multi-threaded environments.
- Read methods like `getCartItemsSnapshot()` are also synchronized to return consistent data.

---

For more details, see the inline Javadoc/documentation within each class.
