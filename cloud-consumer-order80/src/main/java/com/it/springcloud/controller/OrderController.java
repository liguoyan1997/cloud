package com.it.springcloud.controller;

import com.it.springcloud.entity.CommonResult;
import com.it.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    /*服务名*/
    private String PROVIDER_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PROVIDER_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getId/{id}")
    public CommonResult getId(@PathVariable("id") Long id){
        return restTemplate.getForObject(PROVIDER_URL+"/payment/getId/"+id,CommonResult.class);
    }
}
