package com.it.springcloud.controller;

import com.it.springcloud.entity.CommonResult;
import com.it.springcloud.entity.Payment;
import com.it.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/getId/{id}")
    public CommonResult<Payment> getId(@PathVariable("id") Long id){
        return paymentFeignService.getId(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }
}
