package com.it.springcloud.controller;

import com.it.springcloud.config.Ifnull;
import com.it.springcloud.entity.CommonResult;
import com.it.springcloud.entity.Payment;
import com.it.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int num = paymentService.insert(payment);
        return Ifnull.ifzero(num);
    }

    @GetMapping("/payment/getId/{id}")
    public CommonResult getId(@PathVariable("id") Long id){
        Payment payment = paymentService.findById(id);
        return Ifnull.ifnull(payment,id);
    }

}
