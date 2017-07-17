/*
 * Copyright (C) 2017 Piotr Martycz
 */
package pmartycz.payu;

/**
 *
 * @author pmart
 */
public class OrderCreateResponse {
    private Status status;
    private String redirectUri;
    private String orderId;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderCreateResponse{" + "status=" + status + ", redirectUri=" + redirectUri + ", orderId=" + orderId + '}';
    }
    
    private static class Status {
        private String statusCode;

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }
    }
}
