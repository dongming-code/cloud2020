package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

    @Autowired(required = false)
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

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
        if (payment != null) {
            return new CommonResult(200, "查询订单成功！serverPort: " + serverPort, payment);
        } else {
            return new CommonResult(404, "查询订单没有对应的记录，查询ID：" + id + "serverPort: " + serverPort, null);
        }
    }



    @GetMapping(value = "/payment/discovery")
    public Object getDiscovery () {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("*****element:" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost()
                    + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLb() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeOut")
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort;
    }


}
