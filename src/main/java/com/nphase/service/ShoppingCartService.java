package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartService {

    private static final int DISCOUNT_ITEMS = 3;
    private static final int DISCOUNT_RATE = 10;

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        BigDecimal total = BigDecimal.ZERO;
        Map<String, Integer> productCounts = new HashMap<>();
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (Product product : shoppingCart.getProducts()) {
            String productKey = product.getName();
            productCounts.put(productKey, productCounts.getOrDefault(productKey, 0) + 1);

            String categoryKey = product.getCategory();
            categoryCounts.put(categoryKey, categoryCounts.getOrDefault(categoryKey, 0) + 1);

            total = total.add(product.getPricePerUnit());
        }

        total = applyProductDiscounts(total, productCounts);
        total = applyCategoryDiscounts(total, categoryCounts);

        return total;
    }

    private BigDecimal applyProductDiscounts(BigDecimal total, Map<String, Integer> productCounts) {
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            if (entry.getValue() >= DISCOUNT_ITEMS) {
                BigDecimal productDiscount = total.multiply(BigDecimal.valueOf(DISCOUNT_RATE)).divide(BigDecimal.valueOf(100));
                total = total.subtract(productDiscount);
            }
        }
        return total;
    }

    private BigDecimal applyCategoryDiscounts(BigDecimal total, Map<String, Integer> categoryCounts) {
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            if (entry.getValue() >= DISCOUNT_ITEMS) {
                BigDecimal categoryDiscount = total.multiply(BigDecimal.valueOf(DISCOUNT_RATE)).divide(BigDecimal.valueOf(100));
                total = total.subtract(categoryDiscount);
            }
        }
        return total;
    }
}
