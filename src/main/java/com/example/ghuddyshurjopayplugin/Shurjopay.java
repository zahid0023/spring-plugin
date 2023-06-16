package com.example.ghuddyshurjopayplugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Data
@Service
public class Shurjopay {
    private MerchantCredentials shurjopayCredentials;
    private ShurjopayToken shurjopayToken;
    private String TOKEN_URL = "https://sandbox.shurjopayment.com/api/get_token";
    private String CHECK_OUT_URL = "https://www.sandbox.shurjopayment.com/api/secret-pay";
    private String VERIFICATION_URL = "https://www.sandbox.shurjopayment.com/api/verification";
    private String CALL_BACK_URL = "http://localhost:8080/api/v1/open/payment/ipn";
    private String CANCEL_URL = "https://www.facebook.com";

    Shurjopay() {
        init();
    }

    private void init() {
        this.shurjopayCredentials = new MerchantCredentials();
    }

    public ShurjopayToken authenticate() {
        RestTemplate restTemplate = new RestTemplate();

        // Prepare the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username", shurjopayCredentials.getUserName());
        requestBody.add("password", shurjopayCredentials.getUserPassword());
        log.info("Username:" + shurjopayCredentials.getUserName());
        log.info("Password:" + shurjopayCredentials.getUserPassword());

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create the HttpEntity with the request body and headers
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the API call;
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        log.info(responseEntity.toString());
        return parseResponse(responseEntity.getBody(), ShurjopayToken.class);
    }

    // parse the responseEntity body so that we can create the object
    private static <T> T parseResponse(String responseBody, Class<T> responseType) {
        // Use Jackson ObjectMapper to parse the JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Deserialize the JSON response into an object of the specified response type
            return objectMapper.readValue(responseBody, responseType);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return null;  // Handle the parsing error as needed
        }
    }

    public PaymentRes makePayment(PaymentReq payload) throws ShurjopayException {
        RestTemplate restTemplate = new RestTemplate();
        // Get ShurjopayAuthToken
        shurjopayToken = authenticate();

        payload.setStoreId(shurjopayToken.getStoreId());
        // Prepare the request body
        MultiValueMap paymentRequestBody = new LinkedMultiValueMap();
        paymentRequestBody.add("prefix", payload.getPrefix());
        paymentRequestBody.add("token", shurjopayToken.getToken());
        paymentRequestBody.add("client_ip", "127.0.0.0");
        paymentRequestBody.add("return_url", CALL_BACK_URL);
        paymentRequestBody.add("cancel_url", CANCEL_URL);
        paymentRequestBody.add("store_id", payload.getStoreId());
        paymentRequestBody.add("amount", payload.getAmount());
        paymentRequestBody.add("order_id", payload.getOrderId());
        paymentRequestBody.add("currency", payload.getCurrency());
        paymentRequestBody.add("customer_name", payload.getCustomerName());
        paymentRequestBody.add("customer_address", payload.getCustomerAddress());
        paymentRequestBody.add("customer_email", payload.getCustomerEmail());
        paymentRequestBody.add("customer_phone", payload.getCustomerPhone());
        paymentRequestBody.add("customer_city", payload.getCustomerCity());

        // Set the HTTP request Header
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(shurjopayToken.getToken());

        // Create the HttpEntity with the request body and headers
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(paymentRequestBody, headers);

        // Make the api call
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                CHECK_OUT_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        log.info(responseEntity.toString());
        PaymentRes paymentRes = parseResponse(responseEntity.getBody(), PaymentRes.class);
        if (!paymentRes.getCheckoutUrl().isBlank()) {
            log.info("Payment Url has been generated!");
            log.info(paymentRes.getCheckoutUrl());
            return paymentRes;
        } else {
            throw new ShurjopayException("One or more mandatory field(s) not provided in request payload.");
        }
    }

    public VerifiedPayment verifyPayment(String spTxnId) throws ShurjopayException {
        RestTemplate restTemplate = new RestTemplate();
        if (shurjopayToken == null) {
            log.info("Authentication required for payment verification!");
            shurjopayToken = authenticate();
        }
        // prepare the Request Body
        MultiValueMap<String, String> verificationRequestBody = new LinkedMultiValueMap();
        verificationRequestBody.add("order_id", spTxnId);

        // create the http headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(shurjopayToken.getToken());

        // create the HttpEntity with request body and headers
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(verificationRequestBody, headers);

        // Make the api call
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                VERIFICATION_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        log.info(responseEntity.toString());
        VerifiedPayment verifiedPayment = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            VerifiedPayment[] payments = objectMapper.readValue(responseEntity.getBody(), VerifiedPayment[].class);
            verifiedPayment = payments[0];
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return verifiedPayment;
    }
}
