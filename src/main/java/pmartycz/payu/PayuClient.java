/*
 * Copyright (C) 2017 Piotr Martycz
 */
package pmartycz.payu;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;

/**
 * Class implementing PayU REST API
 * 
 * @author pmart
 */
public class PayuClient {
    private static final String REST_URI = "https://secure.snd.payu.com/api/v2_1";
    
    private Client client = ClientBuilder
            .newClient()
            .register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192));
    private String oauthToken;

    public PayuClient(String oauthToken) {
        this.oauthToken = oauthToken;
    }
    
    public Response createOrder(OrderCreateRequest orderCreateRequest) {
        return client
                .target(REST_URI)
                .path("orders")
                .property(ClientProperties.FOLLOW_REDIRECTS, false)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + oauthToken)
                .post(Entity.entity(orderCreateRequest, MediaType.APPLICATION_JSON));
    }
    
    public static void main(String[] args) {
        final String oauthToken = "d9a4536e-62ba-4f60-8017-6053211d3f47";
        
        PayuClient client = new PayuClient(oauthToken);
        
        OrderCreateRequest orderCreateRequest = 
                new OrderCreateRequest.Builder()
                        .setDescription("My test order")
                        .addProduct(new Product("Wireless Mouse for Laptop", "15000", "1"))
                        .addProduct(new Product("HDMI Cable", "6000", "2"))
                        .build();
        
        try {
            Response response = client.createOrder(orderCreateRequest);
            
            if (response.getStatus() != 302) {
                response.close();
                System.out.println("Unexpected status code returned from server");
                System.exit(1);
            }
            
            OrderCreateResponse orderCreateResponse = response.readEntity(OrderCreateResponse.class);
            
            System.out.format("Hurray! We have just successfully created new order (id %s)%n"
                    + "Follow %s for details.", 
                    orderCreateResponse.getOrderId(), 
                    orderCreateResponse.getRedirectUri());
        } catch (ProcessingException | WebApplicationException ex) {
            ex.printStackTrace();
        }
    }
}
