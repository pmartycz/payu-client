/*
 * Copyright (C) 2017 Piotr Martycz
 */
package pmartycz.payu;

import pmartycz.payu.Product;
import pmartycz.payu.OrderCreateRequest;
import pmartycz.payu.PayuClient;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for PayuClient
 * 
 * @author pmart
 */

public class PayuClientTest {
    private static final String OAUTH_TOKEN = "d9a4536e-62ba-4f60-8017-6053211d3f47";
    private static final int ORDER_CREATED_STATUS_CODE = 302;
    private static final int ORDER_ERROR_STATUS_CODE = 400;
        
    private PayuClient client;
    
    @Before
    public void setup() {
        client = new PayuClient(OAUTH_TOKEN);
    }
    
    @Test
    public void givenCorrectObjectShouldCreateOrder() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest.Builder()
                .setDescription("My test order")
                .addProduct(new Product("Wireless Mouse for Laptop", "15000", "1"))
                .addProduct(new Product("HDMI Cable", "6000", "2"))
                .build();
        
        Response response = client.createOrder(orderCreateRequest);
        
        assertEquals("must return success status code", ORDER_CREATED_STATUS_CODE, response.getStatus());
    }
    
    @Test
    public void givenWrongObjectShouldReturnError() {
        // no products
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest.Builder()
                .setDescription("My test order")
                .build();
        
        Response response = client.createOrder(orderCreateRequest);
        
        assertEquals("must return error status code", ORDER_ERROR_STATUS_CODE, response.getStatus());
    }
}
