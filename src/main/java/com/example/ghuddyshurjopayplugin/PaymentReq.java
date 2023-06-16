package com.example.ghuddyshurjopayplugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class PaymentReq {
    @NonNull
    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("cancel_url")
    private String cancelUrl;

    @NonNull
    @JsonProperty("store_id")
    private String storeId;

    @NonNull
    @JsonProperty("amount")
    private double amount;

    @NonNull
    @JsonProperty("order_id")
    private String orderId;

    @NonNull
    @JsonProperty("currency")
    private String currency;

    @NonNull
    @JsonProperty("customer_name")
    private String customerName;

    @NonNull
    @JsonProperty("customer_address")
    private String customerAddress;

    @NonNull
    @JsonProperty("customer_email")
    private String customerEmail;

    @NonNull
    @JsonProperty("customer_phone")
    private String customerPhone;

    @NonNull
    @JsonProperty("customer_city")
    private String customerCity;

    @JsonProperty("customer_post_code")
    private String customerPostCode;

    @JsonProperty("client_ip")
    private String clientIp;

    @JsonProperty("discount_amount")
    private float discountAmount;

    @JsonProperty("disc_percent")
    private float discPercent;

    @JsonProperty("customer_state")
    private String customerState;

    @JsonProperty("customer_country")
    private String customerCountry;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_city")
    private String shippingCity;

    @JsonProperty("shipping_country")
    private String shippingCountry;

    @JsonProperty("received_person_name")
    private String receivedPersonName;

    @JsonProperty("shipping_phone_number")
    private String shippingPhoneNumber;

    @JsonProperty("value1")
    private String value1;

    @JsonProperty("value2")
    private String value2;

    @JsonProperty("value3")
    private String value3;

    @JsonProperty("value4")
    private String value4;

}
