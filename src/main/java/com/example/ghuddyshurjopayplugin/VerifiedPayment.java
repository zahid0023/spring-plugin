package com.example.ghuddyshurjopayplugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerifiedPayment {
    @JsonProperty("id")
    private int id;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private float amount;

    @JsonProperty("payable_amount")
    private float payableAmount;

    @JsonProperty("discsount_amount")
    private float discountAmount;

    @JsonProperty("disc_percent")
    private float discountPercent;

    @JsonProperty("received_amount")
    private float receivedAmount;

    @JsonProperty("usd_amt")
    private float usdAmount;

    @JsonProperty("usd_rate")
    private float usdRate;

    @JsonProperty("is_verify")
    private short isVerify;

    @JsonProperty("card_holder_name")
    private String cardHolderName;

    @JsonProperty("card_number")
    private String cardNumber;

    @JsonProperty("phone_no")
    private String phoneNumber;

    @JsonProperty("bank_trx_id")
    private String bankTransactionId;

    @JsonProperty("invoice_no")
    private String invoiceNumber;

    @JsonProperty("bank_status")
    private String bankStatus;

    @JsonProperty("customer_order_id")
    private String customerOrderId;

    @JsonProperty("sp_code")
    private Integer sp_code;

    @JsonProperty("sp_message")
    private String spMessage;

    @JsonProperty("name")
    private String customerName;

    @JsonProperty("email")
    private String customerEmail;

    @JsonProperty("address")
    private String customerAddress;

    @JsonProperty("city")
    private String customerCity;

    @JsonProperty("value1")
    private String value1;

    @JsonProperty("value2")
    private String value2;

    @JsonProperty("value3")
    private String value3;

    @JsonProperty("value4")
    private String value4;

    @JsonProperty("transaction_status")
    private String transactionStatus;

    @JsonProperty("method")
    private String transactionMethod;

    @JsonProperty("date_time")
    private String transactionTime;

}
