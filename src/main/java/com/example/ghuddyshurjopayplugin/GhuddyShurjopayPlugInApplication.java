package com.example.ghuddyshurjopayplugin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class GhuddyShurjopayPlugInApplication {

    public static void main(String[] args) throws ShurjopayException {
        SpringApplication.run(GhuddyShurjopayPlugInApplication.class, args);

        Shurjopay shurjopay = new Shurjopay();

        PaymentReq paymentReq = new PaymentReq();

        paymentReq.setPrefix("GDL");
        paymentReq.setOrderId("v38493489");
        paymentReq.setCurrency("BDT");
        paymentReq.setAmount(1.00);
        paymentReq.setCustomerName("Nahin Sultana");
        paymentReq.setCustomerAddress("Dhaka");
        paymentReq.setCustomerEmail("nahin.sultana@gmail.com");
        paymentReq.setCustomerPhone("01783466888");
        paymentReq.setCustomerCity("Dhaka");

        PaymentRes paymentRes = shurjopay.makePayment(paymentReq);
        log.info(paymentRes.toString());

        log.info(paymentRes.getSpOrderId());

    }

}
