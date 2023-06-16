package com.example.ghuddyshurjopayplugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentRes {
    @JsonProperty("checkout_url")
    private String checkoutUrl;

    private double amount;

    private String currency;

    @JsonProperty("sp_order_id")
    private String spOrderId;

    @JsonProperty("customer_order_id")
    private String customerOrderId;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_address")
    private String customerAddress;

    @JsonProperty("customer_city")
    private String customerCity;

    @JsonProperty("customer_phone")
    private String customerPhone;

    @JsonProperty("customer_email")
    private String customerEmail;

    @JsonProperty("client_ip")
    private String clientIp;

    private String intent;

    @JsonProperty("transactionStatus")
    private String transactionStatus;

}



