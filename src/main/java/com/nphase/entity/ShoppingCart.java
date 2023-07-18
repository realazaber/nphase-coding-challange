package com.nphase.entity;

import java.util.List;

import java.math.BigDecimal;

public class ShoppingCart {
    private final List<Product> products;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (var product : products) {
            total = total.add(product.getPricePerUnit());
        }

        return total;
    }
}
