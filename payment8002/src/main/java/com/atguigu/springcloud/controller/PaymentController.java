package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired(required = false)
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create (@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        /*logger.info("******插入结果：" + result);*/
        if (result > 0) {
            return new CommonResult(200, "插入订单成功！serverPort: " + serverPort, result);
        } else {
            return new CommonResult(404, "插入订单失败！serverPort: " + serverPort, result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById (@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        /*logger.info("******查询结果：" + payment);*/
        System.out.println("******查询结果：" + payment + "O(∩_∩)O哈哈~");
        if (payment != null) {
            return new CommonResult(200, "查询订单成功！serverPort: " + serverPort, payment);
        } else {
            return new CommonResult(404, "查询订单没有对应的记录，查询ID：" + id + "serverPort: " + serverPort, null);
        }
    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLb() {
        return serverPort;
    }


}
