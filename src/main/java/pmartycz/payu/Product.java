/*
 * Copyright (C) 2017 Piotr Martycz
 */
package pmartycz.payu;

/**
 *
 * @author pmart
 */
public class Product {
    private final String name;
    private final String unitPrice;
    private final String quantity;

    public Product(String name, String unitPrice, String quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getQuantity() {
        return quantity;
    }
}
