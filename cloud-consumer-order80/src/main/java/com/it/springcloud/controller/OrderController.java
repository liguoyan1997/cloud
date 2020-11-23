package com.it.springcloud.controller;

import com.it.springcloud.entity.CommonResult;
import com.it.springcloud.entity.Payment;
import org.springframework.http.ResponseEntity;
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
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PROVIDER_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getId/{id}")
    public CommonResult<Payment> getId(@PathVariable("id") Long id){
        return restTemplate.getForObject(PROVIDER_URL+"/payment/getId/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getId2/{id}")
    public CommonResult getId2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PROVIDER_URL + "/payment/getId/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        }else{
            return new CommonResult(444,"查询失败");
        }

    }
}
