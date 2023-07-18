import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.math.BigDecimal;

public class ShoppingCart {
    private final List<Product> products;

    // Configurable discount items
    private final int discountItems = 3;

    // Configurable discount rate
    private final int discountRate = 10;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        Map<String, Integer> productCounts = new HashMap<>();
        Map<String, Integer> categoryCounts = new HashMap<>();

        // Calculate the count of each product and category
        for (var product : products) {
            String productKey = product.getName();
            productCounts.put(productKey, productCounts.getOrDefault(productKey, 0) + 1);

            String categoryKey = product.getCategory();
            categoryCounts.put(categoryKey, categoryCounts.getOrDefault(categoryKey, 0) + 1);

            total = total.add(product.getPricePerUnit());
        }

        // Apply discountRate per product if same product is bought discountItems times or more
        for (var entry : productCounts.entrySet()) {
            if (entry.getValue() >= discountItems) {
                BigDecimal productDiscount = entry.getKey().getPricePerUnit().multiply(BigDecimal.valueOf(discountRate)).divide(BigDecimal.valueOf(100));
                total = total.subtract(productDiscount);
            }
        }

        // Apply discountRate discount per product in category if bought discountItems times or more
        for (var entry : categoryCounts.entrySet()) {
            if (entry.getValue() >= discountItems) {
                BigDecimal categoryDiscount = entry.getKey().getCategory().multiply(BigDecimal.valueOf(discountRate)).divide(BigDecimal.valueOf(100));
                total = total.subtract(categoryDiscount);
            }
        }

        return total;
    }
}
