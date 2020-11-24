package com.it.springcloud.service;

import com.it.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/getId/{id}")
    CommonResult getId(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String timeout();

}
