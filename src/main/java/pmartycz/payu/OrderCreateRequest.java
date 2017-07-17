/*
 * Copyright (C) 2017 Piotr Martycz
 */
package pmartycz.payu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pmart
 */
public class OrderCreateRequest {
    public final String notifyUrl;
    public final String customerIp;
    public final String merchantPosId;
    public final String description;
    public final String currencyCode;
    public final String totalAmount;
    public final List<Product> products;
    
    public static class Builder {
        private String notifyUrl = "https://your.eshop.com/notify";
        private String customerIp = "127.0.0.1";
        private String merchantPosId = "300746";
        private String description = "RTV market";
        private String currencyCode = "PLN";
        private String totalAmount = "0";
        private List<Product> products = new ArrayList();

        public Builder setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public Builder setCustomerIp(String customerIp) {
            this.customerIp = customerIp;
            return this;
        }

        public Builder setMerchantPosId(String merchantPosId) {
            this.merchantPosId = merchantPosId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }
        
        public Builder addProduct(Product... products) {
            for (Product product : products) {
                this.products.add(product);
                totalAmount = Integer.toString(Integer.parseInt(totalAmount) + 
                        Integer.parseInt(product.getUnitPrice()) * 
                        Integer.parseInt(product.getQuantity()));
            }
            return this;
        }
        
        public OrderCreateRequest build() {
            return new OrderCreateRequest(this);
        }
    }

    private OrderCreateRequest(Builder builder) {
        notifyUrl = builder.notifyUrl;
        customerIp = builder.customerIp;
        merchantPosId = builder.merchantPosId;
        description = builder.description;
        currencyCode = builder.currencyCode;
        totalAmount = builder.totalAmount;
        products = builder.products;
    }
}
