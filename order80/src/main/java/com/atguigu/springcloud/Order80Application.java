package com.atguigu.springcloud;

import com.atguigu.myrule.SelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 主启动
 */
@SpringBootApplication
@EnableEurekaClient
// @RibbonClient(name = "cloud-payment-service", configuration = SelfRule.class)
public class Order80Application {

    public static void main(String[] args) {
        SpringApplication.run(Order80Application.class, args);
    }

}
