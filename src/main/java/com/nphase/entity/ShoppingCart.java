import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.math.BigDecimal;

public class ShoppingCart {
    private final List<Product> products;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
