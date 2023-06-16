package com.example.ghuddyshurjopayplugin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1")
@Slf4j
public class ShurjoController {
    @GetMapping
    @RequestMapping(path = "/open/payment/ipn", method = RequestMethod.GET)
    public void callBackFromPaymentGateway(@RequestParam("order_id") String orderId) throws ShurjopayException {
        Shurjopay shurjopay = new Shurjopay();
        VerifiedPayment verifiedPayment = shurjopay.verifyPayment(orderId);
        log.info(verifiedPayment.toString());
    }
}
