package com.atguigu.springcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderSonsulController {

    public static final String INVOKE_URL = "http://cloud-providerconsul-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/consul")
    public String paymentInfo () {
        String template = restTemplate.getForObject(INVOKE_URL + "/payment/sonsul", String.class);
        return template;
    }


}
