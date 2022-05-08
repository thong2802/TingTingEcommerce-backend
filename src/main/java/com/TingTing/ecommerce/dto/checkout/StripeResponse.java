package com.TingTing.ecommerce.dto.checkout;

public class StripeResponse {
    private String sessioniD;

    public StripeResponse(String sessioniD) {
        this.sessioniD = sessioniD;
    }

    public String getSessioniD() {
        return sessioniD;
    }

    public void setSessioniD(String sessioniD) {
        this.sessioniD = sessioniD;
    }
}
